package epi.linkedlist;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PivotList {

  public static ListNode<Integer> listPivoting(ListNode<Integer> l, int x) {

    if(l == null || l.next == null) return l;

    //create three dummy nodes for start of three lists(less,equal,greater)
    // and three pointers for pointing to current pointer in that list
    ListNode<Integer> lessDummyHead = new ListNode<>(0,null);
    ListNode<Integer> equalDummyHead = new ListNode<>(0,null);
    ListNode<Integer> greaterDummyHead = new ListNode<>(0,null);
    ListNode<Integer> currLess = lessDummyHead, currEqual = equalDummyHead, currGreater = greaterDummyHead;

    // iterate through the list and add the node to appropriate list
    ListNode<Integer> current = l;
    while (current != null){
      if(current.data.intValue() < x){
        currLess.next = current;
        currLess = currLess.next;
      }else if (current.data.intValue() > x){
        currGreater.next = current;
        currGreater = currGreater.next;
      }else {
        currEqual.next = current;
        currEqual = currEqual.next;
      }
      current = current.next;
    }

    // add three lists
    currGreater.next = null;
    currEqual.next = greaterDummyHead.next;
    currLess.next = equalDummyHead.next;

    return lessDummyHead.next;
  }
  public static List<Integer> linkedToList(ListNode<Integer> l) {
    List<Integer> v = new ArrayList<>();
    while (l != null) {
      v.add(l.data);
      l = l.next;
    }
    return v;
  }

  @EpiTest(testDataFile = "pivot_list.tsv")
  public static void listPivotingWrapper(TimedExecutor executor,
                                         ListNode<Integer> l, int x)
      throws Exception {
    List<Integer> original = linkedToList(l);

    final ListNode<Integer> finalL = l;
    l = executor.run(() -> listPivoting(finalL, x));

    List<Integer> pivoted = linkedToList(l);

    int mode = -1;
    for (Integer i : pivoted) {
      switch (mode) {
      case -1:
        if (i == x) {
          mode = 0;
        } else if (i > x) {
          mode = 1;
        }
        break;
      case 0:
        if (i < x) {
          throw new TestFailure("List is not pivoted");
        } else if (i > x) {
          mode = 1;
        }
        break;
      case 1:
        if (i <= x) {
          throw new TestFailure("List is not pivoted");
        }
      }
    }

    Collections.sort(original);
    Collections.sort(pivoted);
    if (!original.equals(pivoted))
      throw new TestFailure("Result list contains different values");
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PivotList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

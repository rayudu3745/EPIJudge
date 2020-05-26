package epi.linkedlist;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class EvenOddListMerge {
  @EpiTest(testDataFile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {

    if (L == null || L.next == null) return L;

    // create two dummy nodes one for start of even and other for start of odd
    ListNode<Integer> dummyEven = new ListNode<>(0, null);
    ListNode<Integer> currEven = dummyEven;
    ListNode<Integer> dummyOdd = new ListNode<>(0, null);
    ListNode<Integer> currOdd = dummyOdd;

    // traverse the list and add alternate elements one to even
    // and other to odd starting with even
    boolean even = true;
    ListNode<Integer> current = L;
    while (current != null){
      if(even){
        currEven.next = current;
        currEven = current;
        even = false;
      }else {
        currOdd.next = current;
        currOdd = current;
        even = true;
      }
      current = current.next;
    }

    // make end of odd list point to null
    // because end of original list can be even and it will loop at end point(** made mistake here**)
    currOdd.next = null;

    // attach odd list to end of even list
    currEven.next = dummyOdd.next;
    return dummyEven.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

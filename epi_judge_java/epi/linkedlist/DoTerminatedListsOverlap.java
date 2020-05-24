package epi.linkedlist;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class DoTerminatedListsOverlap {

  public static ListNode<Integer>
  overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {

    // find sizes of lists
    int s0 = sizeOf(l0);
    int s1 = sizeOf(l1);

    // find smaller and bigger lists
    ListNode<Integer> smaller;
    ListNode<Integer> bigger;
    if(s0 > s1){
      bigger = l0;
      smaller = l1;
    }else {
      bigger = l1;
      smaller = l0;
    }

    // move the bigger list |s1 - s2| forward
    int diff = Math.abs(s0 - s1);
    while (diff > 0){
      bigger = bigger.next;
      diff--;
    }

    // move small and big simultaneously,
    // they will meet at common point if they have common point
    while ((smaller != bigger) && smaller != null && bigger != null){
          smaller = smaller.next;
          bigger = bigger.next;
    }

    if(smaller == bigger && smaller != null){
      return smaller;
    }
    return null;
  }

  public static int sizeOf(ListNode<Integer> l){
    int size = 0;
    ListNode<Integer> curr = l;
    while(curr != null){
      size++;
      curr = curr.next;
    }
    return size;
  }
  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

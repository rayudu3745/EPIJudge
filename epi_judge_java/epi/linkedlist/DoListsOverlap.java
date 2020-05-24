package epi.linkedlist;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashSet;
import java.util.Set;
public class DoListsOverlap {

  public static ListNode<Integer> overlappingLists(ListNode<Integer> l0,
                                                   ListNode<Integer> l1) {

    // check if both lists have cycles
    ListNode<Integer> startOfCycleOne = IsListCyclic.hasCycle(l0);
    ListNode<Integer> startOfCycleTwo = IsListCyclic.hasCycle(l1);

    // if both don't have cycles use the lengths method
    if(startOfCycleOne == null && startOfCycleTwo == null){
      return DoTerminatedListsOverlap.overlappingNoCycleLists(l0,l1);
    }

    // if both lists have cycles
    if(startOfCycleOne != null && startOfCycleTwo != null){

      // check if both have same cycle, if not lists don't overlap
      // loop through one cycle and try to find the root of other cycle
      // if not found they are different cycles
      if (startOfCycleOne != startOfCycleTwo) {
        ListNode<Integer> node1 = startOfCycleOne;
        do{
            node1 = node1.next;
        }while (node1 != startOfCycleTwo && node1 != startOfCycleOne);
        if(node1 != startOfCycleTwo){
          return null;
        }
      }

      //consider one start of the cycle as end and use the lengths method
      ListNode<Integer> endNode = startOfCycleOne;

      // get sizes
      int s0 = sizeEndingWithNode(l0,endNode);
      int s1 = sizeEndingWithNode(l1,endNode);

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
      while (smaller != bigger){
        smaller = smaller.next;
        bigger = bigger.next;
      }

      if(smaller == bigger){
        return smaller;
      }

    }

    // if only list has cycle then they don't overlap
    return null;
  }

  public static int sizeEndingWithNode(ListNode<Integer> start, ListNode<Integer> end){
    int size = 0;
    while(start != end){
      size++;
      start = start.next;
    }
    return size;
  }

  @EpiTest(testDataFile = "do_lists_overlap.tsv")
  public static void
  overlappingListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                          ListNode<Integer> l1, ListNode<Integer> common,
                          int cycle0, int cycle1) throws Exception {
    if (common != null) {
      if (l0 == null) {
        l0 = common;
      } else {
        ListNode<Integer> it = l0;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }

      if (l1 == null) {
        l1 = common;
      } else {
        ListNode<Integer> it = l1;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }
    }

    if (cycle0 != -1 && l0 != null) {
      ListNode<Integer> last = l0;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l0;
      while (cycle0-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    if (cycle1 != -1 && l1 != null) {
      ListNode<Integer> last = l1;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l1;
      while (cycle1-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    Set<Integer> commonNodes = new HashSet<>();
    ListNode<Integer> it = common;
    while (it != null && !commonNodes.contains(it.data)) {
      commonNodes.add(it.data);
      it = it.next;
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingLists(finalL0, finalL1));

    if (!((commonNodes.isEmpty() && result == null) ||
          (result != null && commonNodes.contains(result.data)))) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

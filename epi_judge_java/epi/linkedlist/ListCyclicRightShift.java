package epi.linkedlist;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ListCyclicRightShift {
  @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")

  public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L,
                                                           int k) {

    if (L == null) return null;

    // loop and find end node and size of the list
    ListNode<Integer> startNode = L, endNode = L;
    int size = 1;
    while (endNode.next != null){
      endNode = endNode.next;
      size++;
    }

    // make endNode point to startNode
    endNode.next = startNode;

    // new head after shifting k nodes right will be size - k + 1 th node from endNode
    // cut the link after traversing size - k nodes from endNode and return the nextNode
    k = Math.floorMod(k,size);
    int diff = size - k;

    // find the new end
    ListNode<Integer> newEnd = endNode;
    while (diff > 0){
      newEnd = newEnd.next;
      diff--;
    }

    // cut the loop and return new head
    ListNode<Integer> newHead = newEnd.next;
    newEnd.next = null;

    return newHead;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ListCyclicRightShift.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

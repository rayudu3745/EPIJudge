package epi.linkedlist;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {

    // solution is without calculating the length of the list

    // start two pointers one at the beginning and advance other to k nodes forward
    ListNode<Integer> startNode = L, advancedNode = L;
    while(k > 0){
      advancedNode = advancedNode.next;
      k--;
    }

    // condition when there are exactly k nodes in the list
    if(advancedNode == null) return startNode.next;

    // move both the start and advance node simultaneously until advance node reaches final node
    while(advancedNode.next != null){
      startNode = startNode.next;
      advancedNode = advancedNode.next;
    }

    // now start node will be k+1 distance from end
    // remove next node of startnode
    startNode.next = startNode.next.next;

    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

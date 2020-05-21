package epi.linkedlist;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
    ListNode<Integer> dummyNode = new ListNode<>(0,null);
    ListNode<Integer> current = dummyNode;
    while(L1 != null && L2 != null){
      if(L1.data <= L2.data){
        current.next = L1;
        L1 = L1.next;
      }else {
        current.next = L2;
        L2 = L2.next;
      }
      current = current.next;
    }
    current.next = L1 == null ? L2 : L1;
    return dummyNode.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

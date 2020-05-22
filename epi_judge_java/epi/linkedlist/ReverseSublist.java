package epi.linkedlist;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    if(L == null) return null;
    if(start == finish) return L;

    ListNode<Integer> nodeBeforeSubList = null;
    ListNode<Integer> current = L,head = L;
    for(int i = 0; i < start-1; i++){
      nodeBeforeSubList = current;
      current = current.next;
    }
    ListNode<Integer> headOfSublist = current;
    ListNode<Integer> prev = null;
    for(int i=0; i< finish - start + 1; i++){
      ListNode<Integer> next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }
    if(nodeBeforeSubList != null){
      nodeBeforeSubList.next = prev;
    }else {
      head = prev;
    }
    headOfSublist.next = current;
    return head;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

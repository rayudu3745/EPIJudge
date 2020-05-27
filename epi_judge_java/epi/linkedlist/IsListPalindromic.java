package epi.linkedlist;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsListPalindromic {
  @EpiTest(testDataFile = "is_list_palindromic.tsv")

  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {

    // find size of the list
    int size = Primer.size(L);

    if(size < 2) return true;

    // reverse the second half of the list
    int mid = size / 2 ;
    ListNode<Integer> curr = L;
    while (--mid > 0){
      curr = curr.next;
    }

    ListNode<Integer> second = Primer.reverse(curr.next);
    curr.next = null; // cutting first half
    ListNode<Integer> first = L;

    // now iter simultaneously first and second half and check each node
    while (first != null){
      if(first.data.intValue() != second.data.intValue()) return false;
      first = first.next;
      second = second.next;
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

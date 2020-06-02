package epi.linkedlist;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntAsListAdd {
  @EpiTest(testDataFile = "int_as_list_add.tsv")

  public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1,
                                                ListNode<Integer> L2) {

    // dummy node for new List
    ListNode<Integer> dummy = new ListNode<>(0,null);
    ListNode<Integer> current = dummy;

    // iterate both lists simultaneously and add them + carry of previous addition
    // until one list is complete
    int carry = 0;

    while (L1 != null && L2 != null){
      int sum = L1.data + L2.data + carry;
      carry = sum / 10;
      current.next = new ListNode<>(sum % 10,null);
      current = current.next;
      L1 = L1.next;
      L2 = L2.next;
    }

    // remaining part of the big list and carry from previous addition are left now
    ListNode<Integer> remaining = L1 == null ? L2 : L1;

    // carry the same process as above for the remaining part with carry
    while (remaining != null){
      int sum = remaining.data + carry;
      carry = sum / 10;
      current.next = new ListNode<>(sum % 10,null);
      current = current.next;
      remaining = remaining.next;
    }

    // if carry if not 0 add that extra node
    if(carry > 0){
      current.next = new ListNode<>(carry,null);
    }

    return dummy.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsListAdd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

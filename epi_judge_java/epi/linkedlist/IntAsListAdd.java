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
    ListNode<Integer> current1 = L1;
    ListNode<Integer> current2 = L2;

    while (current1 != null && current2 != null){
      int sum = current1.data.intValue() + current2.data.intValue() + carry;
      carry = sum / 10;
      ListNode<Integer> newNode = new ListNode<>(Math.floorMod(sum,10),null);
      current.next = newNode;
      current = current.next;
      current1 = current1.next;
      current2 = current2.next;
    }

    // remaining part of the big list and carry from previous addition are left now
    ListNode<Integer> remaining = current1 == null ? current2 : current1;

    // carry the same process as above for the remaining part with carry
    while (remaining != null){
      int sum = remaining.data.intValue() + carry;
      carry = sum / 10;
      ListNode<Integer> newNode = new ListNode<>(Math.floorMod(sum,10),null);
      current.next = newNode;
      current = current.next;
      remaining = remaining.next;
    }

    // if carry if not 0 add that extra node
    if(carry > 0){
      ListNode<Integer> newNode = new ListNode<>(carry,null);
      current.next = newNode;
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

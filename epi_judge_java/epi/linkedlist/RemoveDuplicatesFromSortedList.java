package epi.linkedlist;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RemoveDuplicatesFromSortedList {
  @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")

  public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {

    if(L == null) return L;

    // maintain two pointers
    // point to one to the current node in modified list
    // other to find the next non duplicate node
    ListNode<Integer> currentModified = L, currentMoving = L;

    // loop until the currentModified is end of the list
    while(currentModified != null){

        // find next non duplicate node or end of list
        while (currentMoving != null && currentMoving.data.equals(currentModified.data)){
          currentMoving = currentMoving.next;
        }

        // point currentModified to next non duplicate node
        currentModified.next = currentMoving;

        // place both pointers again at updated node
        currentModified = currentMoving;
        currentMoving = currentModified;

    }
    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

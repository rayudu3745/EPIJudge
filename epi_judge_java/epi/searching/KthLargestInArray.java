package epi.searching;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KthLargestInArray {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(1, A) returns 3, findKthLargest(2, A) returns 2,
  // findKthLargest(3, A) returns 1, and findKthLargest(4, A) returns -1.
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  public static int findKthLargest(int k, List<Integer> A) {

    // kth largest will be at n - k index in sorted order
    int indexToFound = A.size() - k;

    int left = 0, right = A.size() - 1;
    Random random = new Random(0);

    while (left <= right){

      // randomly select an index between left and right[left, right]
      int randomPivot = random.nextInt(right - left + 1) + left;

      // get the index of the pivotValue after partitioning
      int newPivotIndex = partitionAroundPivot(left, right, A, randomPivot);

      if(newPivotIndex == indexToFound){
        return A.get(newPivotIndex);
      }else if(newPivotIndex > indexToFound){
        right = newPivotIndex - 1;
      }else {
        left = newPivotIndex + 1;
      }

    }

    return 0;
  }

  //returns new pivotindex
  //all numbers less than pivot will be left of new pivotindex
  private static int partitionAroundPivot(int left, int right, List<Integer> A, int pivotIndex){

    int pivotValue = A.get(pivotIndex);

    // swap pivot and right
    Collections.swap(A, pivotIndex, right);

    int newPivotIndex = left;

    // move all elements less than pivotValue to left side
    for (int i = left; i < right; i++){
      if(A.get(i) < pivotValue){
        Collections.swap(A, i , newPivotIndex);
        newPivotIndex++;
      }
    }

    // now newPivotIndex will be where the pivotValue should be present
    Collections.swap(A, newPivotIndex, right);
    return newPivotIndex;
  }

  public static void main(String[] args) {
    //findKthLargest(1, Arrays.asList(3,1,-1,2));
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

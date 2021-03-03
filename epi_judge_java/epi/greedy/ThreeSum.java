package epi.greedy;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class ThreeSum {
  @EpiTest(testDataFile = "three_sum.tsv")

  public static boolean hasThreeSum(List<Integer> A, int t) {

    // sort the list and use twosum
    Collections.sort(A);
    for (int i = 0 ; i < A.size() ; i++){
      int cur = A.get(i);
      if (TwoSum.hasTwoSum(A, t - cur)){
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ThreeSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi.greedy;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class MaxTrappedWater {
  @EpiTest(testDataFile = "max_trapped_water.tsv")

  public static int getMaxTrappedWater(List<Integer> heights) {

    int curMax = 0 , i = 0 , j = heights.size() - 1 ;

    // shrink from ends based on heights of the i and j
    while (i < j){
      int length = j - i;
      if (heights.get(i) < heights.get(j)){
        curMax = Math.max(curMax, heights.get(i) * length);
        i++;
      }else if (heights.get(i) > heights.get(j)){
        curMax = Math.max(curMax, heights.get(j) * length);
        j--;
      }else {
        curMax = Math.max(curMax, heights.get(i) * length);
        i++;
        j--;
      }
    }
    return curMax;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxTrappedWater.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

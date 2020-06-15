package epi.searching;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchShiftedSortedArray {
  @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

  public static int searchSmallest(List<Integer> A) {

    int L = 0, U = A.size() - 1;

    while (L < U){

      int M = L + (U - L) / 2;

      if(A.get(M) > A.get(U)){
        // minimum must lie between M and U
        L = M + 1;
      } else {
        // minimum must lie in L and M inclusive
        U = M;
      }
    }
    //loop end
    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

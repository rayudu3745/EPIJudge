package epi.searching;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")

  public static int searchFirstOfK(List<Integer> A, int k) {

    int L = 0 , U = A.size() - 1, result = -1;

    while (L <= U){

      int M = L + (U - L) / 2;

      if(A.get(M).equals(k)){
        // update the index
        result = M;
        // first occurrence can still happen in left half
        U = M - 1;
      }else if(A.get(M) > k){
        U = M - 1;
      }else {
        L = M + 1;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

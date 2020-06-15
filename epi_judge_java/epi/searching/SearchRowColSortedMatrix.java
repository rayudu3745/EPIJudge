package epi.searching;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchRowColSortedMatrix {
  @EpiTest(testDataFile = "search_row_col_sorted_matrix.tsv")

  public static boolean matrixSearch(List<List<Integer>> A, int x) {

    int row = 0, col = A.get(0).size() - 1;

    // compare x with top right corner number

    while (row < A.size() && col >=0){
      if(A.get(row).get(col) > x){
      // it cant be present in that col
        col--;
      }else if(A.get(row).get(col) < x){
        // it can't be present in that row
        row++;
      }else {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchRowColSortedMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

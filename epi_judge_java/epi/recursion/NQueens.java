package epi.recursion;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
public class NQueens {
  @EpiTest(testDataFile = "n_queens.tsv")

  public static List<List<Integer>> nQueens(int n) {
    List<List<Integer>> result = new ArrayList<>();
    solveNqueens(n, 0, new ArrayList<>(), result);
    return result;
  }

  // solve for placing placing a queen on a given row
  private static void solveNqueens(int n , int row, List<Integer> colPlacement, List<List<Integer>> result){
    if (row == n){
      // all queens are placed good
      result.add(new ArrayList<>(colPlacement));
    }else {
      // try all col on that row
      for (int col = 0 ; col < n ; col++){
        colPlacement.add(col);
        if (isValid(colPlacement)){
          solveNqueens(n,row + 1 , colPlacement , result);
        }
        colPlacement.remove(colPlacement.size() - 1);
      }
    }
  }

  // test if a newly placed queen will confilict with previous rows
  private static boolean isValid(List<Integer> colPlacement){
    int currentRow = colPlacement.size() - 1;
    for (int i = 0 ; i < currentRow ; i++){
      int diff = Math.abs(colPlacement.get(i) - colPlacement.get(currentRow));
      if (diff == 0 || diff == currentRow - i){
        return false;
      }
    }
    return true;
  }

  @EpiTestComparator
  public static boolean comp(List<List<Integer>> expected,
                             List<List<Integer>> result) {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NQueens.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

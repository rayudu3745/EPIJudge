package epi.searching;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int k) {

    long L = 0, U = k;
    while (L <= U){

      long M = L + (U - L) / 2;

      long square = M * M;

      if(square <= k){
        L = M + 1;
      }else {
        U = M - 1;
      }

    }

    return (int)L - 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

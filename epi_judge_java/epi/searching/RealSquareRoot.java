package epi.searching;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import javax.swing.*;

public class RealSquareRoot {
  @EpiTest(testDataFile = "real_square_root.tsv")

  public static double squareRoot(double x) {

    //decide search range
    double low,high;
    if(x < 1.0){
      low = x;
      high = 1;
    }else {
      low = 1.0;
      high = x;
    }

    while (compare(low , high) == ORDER.S){
      double mid = low + 0.5 * (high - low);
      double midSquare = mid * mid;

      if(compare(midSquare,x) == ORDER.E){
        return mid;
      }else if (compare(midSquare,x) == ORDER.L){
        high = mid;
      }else {
        low = mid;
      }
    }

    return low;
  }

  private enum ORDER {S,E,L}


  private static ORDER compare(double x, double y){
    final double EPSILON = 0.000001;

    //normalization
    double diff = (x - y) / y ;

    if(diff < -EPSILON){
      // left is less than right with in tolerance
      return ORDER.S;
    }else {
      if(diff > EPSILON){
        return ORDER.L;
      }else {
        return ORDER.E;
      }
    }

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RealSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

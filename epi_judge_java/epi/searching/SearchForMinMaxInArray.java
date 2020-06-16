package epi.searching;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchForMinMaxInArray {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class MinMax {
    public Integer smallest;
    public Integer largest;

    public MinMax(Integer smallest, Integer largest) {
      this.smallest = smallest;
      this.largest = largest;
    }

    private static MinMax minMax(Integer a, Integer b) {
      return Integer.compare(b, a) < 0 ? new MinMax(b, a) : new MinMax(a, b);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      MinMax minMax = (MinMax)o;

      if (!smallest.equals(minMax.smallest)) {
        return false;
      }
      return largest.equals(minMax.largest);
    }

    @Override
    public String toString() {
      return "min: " + smallest + ", max: " + largest;
    }
  }

  @EpiTest(testDataFile = "search_for_min_max_in_array.tsv")

  public static MinMax findMinMax(List<Integer> A) {

    if(A == null || A.isEmpty()) return null;

    if(A.size() == 1) return new MinMax(A.get(0),A.get(0));

    // find min and max between pairs
    MinMax globalMinMax = MinMax.minMax(A.get(0), A.get(1));

    for (int i = 2; i + 1 < A.size(); i++){
      MinMax localTopair = MinMax.minMax(A.get(i), A.get(i + 1));
      globalMinMax = new MinMax(Math.min(globalMinMax.smallest, localTopair.smallest),
              Math.max(globalMinMax.largest, localTopair.largest));
    }

    // if there are odd number of elements
    // one will be left out of pair
    if(A.size() % 2 != 0){
      globalMinMax = new MinMax(Math.min(globalMinMax.smallest, A.get(A.size() - 1)),
              Math.max(globalMinMax.largest, A.get(A.size() - 1)));
    }

    return globalMinMax;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchForMinMaxInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

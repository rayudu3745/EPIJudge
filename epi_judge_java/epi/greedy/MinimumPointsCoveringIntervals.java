package epi.greedy;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class MinimumPointsCoveringIntervals {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Interval {
    public int left, right;

    public Interval(int l, int r) {
      this.left = l;
      this.right = r;
    }
  }

  @EpiTest(testDataFile = "minimum_points_covering_intervals.tsv")

  public static Integer findMinimumVisits(List<Interval> intervals) {

    if (intervals == null || intervals.isEmpty()) return 0;
    // Sort the intervals using the interval endpoints
    // and greedyly pick the right end point of the first interval and continue to the
    // next interval which is not containing that point

    intervals.sort(Comparator.comparingInt(x -> x.right));
    int currentPoint = intervals.get(0).right;
    int minVisits = 1;
    for (int i = 1 ; i < intervals.size() ; i++){
      // if the current interval is contained by the selected points
      if ((intervals.get(i).left < currentPoint && intervals.get(i).right > currentPoint) || intervals.get(i).left == currentPoint ||
      intervals.get(i).right == currentPoint){
      }else {
        // select right point from the interval
        currentPoint = intervals.get(i).right;
        minVisits += 1;
      }
    }
    return minVisits;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumPointsCoveringIntervals.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi.greedy;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.List;
import java.util.stream.Collectors;

public class RefuelingSchedule {
  private static final int MPG = 20;

  // gallons[i] is the amount of gas in city i, and distances[i] is the distance
  // city i to the next city.
  public static int findAmpleCity(List<Integer> gallons,
                                  List<Integer> distances) {

    List<Integer> distanceFuel = gallons.stream().map(x -> x * MPG).collect(Collectors.toList());

    // assume ample city starting from first
    for (int i = 0; i < gallons.size(); i++){
      int startPoint = i , curPoint = i;
      int fuelAvaialbleforDistance = distanceFuel.get(i);
      while (fuelAvaialbleforDistance >= distances.get(curPoint)){
        // move to next stop
        int nextPoint = (curPoint + 1) % gallons.size();
        fuelAvaialbleforDistance = fuelAvaialbleforDistance - distances.get(curPoint) + distanceFuel.get(nextPoint);
        curPoint = nextPoint;
        if (curPoint == startPoint) return startPoint;
      }
      // dont need to consider the other points
      i = curPoint;
    }
    return -1;
  }
  @EpiTest(testDataFile = "refueling_schedule.tsv")
  public static void findAmpleCityWrapper(TimedExecutor executor,
                                          List<Integer> gallons,
                                          List<Integer> distances)
      throws Exception {
    int result = executor.run(() -> findAmpleCity(gallons, distances));
    final int numCities = gallons.size();
    int tank = 0;
    for (int i = 0; i < numCities; ++i) {
      int city = (result + i) % numCities;
      tank += gallons.get(city) * MPG - distances.get(city);
      if (tank < 0) {
        throw new TestFailure(String.format("Out of gas on city %d", city));
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RefuelingSchedule.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

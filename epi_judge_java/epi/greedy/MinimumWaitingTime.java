package epi.greedy;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class MinimumWaitingTime {
  @EpiTest(testDataFile = "minimum_waiting_time.tsv")

  public static int minimumTotalWaitingTime(List<Integer> serviceTimes) {

    // greedy select the min service time
    // correctness proof use exchange argument

    int waitingTimeTotal = 0;
    int waitingTime = 0;
    Collections.sort(serviceTimes);
    for (int i = 1 ; i < serviceTimes.size(); i++){
      waitingTime = waitingTime + serviceTimes.get(i - 1);
      waitingTimeTotal += waitingTime;
    }
    return waitingTimeTotal;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumWaitingTime.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi.hashtables;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestRepeatedEntries {
  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")

  public static int findNearestRepetition(List<String> paragraph) {

    int minDistance = Integer.MAX_VALUE;

    // hashtable to store String with its last visited index
    Map<String, Integer> indexMap = new HashMap<>();

    for(int i = 0; i < paragraph.size(); i++){

      if (indexMap.containsKey(paragraph.get(i))){

        // check the distance between current and the previous one and update the minDistance
        int dist = i - indexMap.get(paragraph.get(i));
        minDistance = Math.min(dist, minDistance);
      }

      // update the indexMap
      indexMap.put(paragraph.get(i), i);

    }

    return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

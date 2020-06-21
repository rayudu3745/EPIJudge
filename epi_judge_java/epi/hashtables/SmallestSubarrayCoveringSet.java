package epi.hashtables;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class SmallestSubarrayCoveringSet {

  // Represent subarray by starting and ending indices, inclusive.
  private static class Subarray {
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph,
                                                         Set<String> keywords) {

    Subarray result = new Subarray(-1,-1);

    Map<String, Integer> keyWordFreq = new HashMap<>();

    int numKeywords = keywords.size();

    // slide the window to contain the all the keywords
    // starting with [0,0]
    for (int left = 0, right = 0 ; right < paragraph.size() ; right++){

      // expand right to contain all the keywords
      String currRight = paragraph.get(right);
      if (keywords.contains(currRight)){
        keyWordFreq.put(currRight, keyWordFreq.get(currRight) == null ? 1 : keyWordFreq.get(currRight) + 1);
      }

      // loop enters when all the keywords are present int the current
      // contract left until the all the keywords are still present
      while (keyWordFreq.size() == numKeywords){
        String currLeft = paragraph.get(left);

        if (keywords.contains(currLeft)){
          // remove from the map if its the last available so that loop exists
          if (keyWordFreq.get(currLeft).equals(1)){
            // now check if current sub array is the smallest
            if((result.start == -1 && result.end == -1) || (right - left) < (result.end - result.start)){
              result = new Subarray(left, right);
            }
            keyWordFreq.remove(currLeft);
          }else {
            // else reduce the frequcency
            keyWordFreq.put(currLeft, keyWordFreq.get(currLeft) - 1);
          }
        }
        left++;
      }

    }

    return result;
  }

  @EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
  public static int findSmallestSubarrayCoveringSetWrapper(
      TimedExecutor executor, List<String> paragraph, Set<String> keywords)
      throws Exception {
    Set<String> copy = new HashSet<>(keywords);

    Subarray result = executor.run(
        () -> findSmallestSubarrayCoveringSet(paragraph, keywords));

    if (result.start < 0 || result.start >= paragraph.size() ||
        result.end < 0 || result.end >= paragraph.size() ||
        result.start > result.end)
      throw new TestFailure("Index out of range");

    for (int i = result.start; i <= result.end; i++) {
      copy.remove(paragraph.get(i));
    }

    if (!copy.isEmpty()) {
      throw new TestFailure("Not all keywords are in the range");
    }
    return result.end - result.start + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SmallestSubarrayCoveringSet.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

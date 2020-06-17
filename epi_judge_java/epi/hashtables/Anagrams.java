package epi.hashtables;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class Anagrams {
  @EpiTest(testDataFile = "anagrams.tsv")

  public static List<List<String>> findAnagrams(List<String> dictionary) {

    // group strings by their sorted order ;
    Map<String, List<String>> anagrams = dictionary.stream()
            .collect(Collectors.groupingBy((s) -> {
                              char[] chars = s.toCharArray();
                              Arrays.sort(chars);
                              return new String(chars);
    }));
    return anagrams.values().stream().filter(list -> list.size() > 1).collect(Collectors.toList());
  }
  @EpiTestComparator
  public static boolean comp(List<List<String>> expected,
                             List<List<String>> result) {
    if (result == null) {
      return false;
    }
    for (List<String> l : expected) {
      Collections.sort(l);
    }
    expected.sort(new LexicographicalListComparator<>());
    for (List<String> l : result) {
      Collections.sort(l);
    }
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Anagrams.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

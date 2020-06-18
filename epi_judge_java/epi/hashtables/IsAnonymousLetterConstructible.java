package epi.hashtables;
import epi.SearchMaze;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IsAnonymousLetterConstructible {
  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

  public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                          String magazineText) {

    // char to frequency map for magazine
    Map<Integer,Long> availableChars = magazineText.chars().boxed()
            .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

    // for every char in in letterText
    for(char c : letterText.toCharArray()){

      if(!availableChars.containsKey((int) c)){
        // if character not present in magazine
        return false;
      }else if(availableChars.get((int) c) < 1){
        // if not enough chars  present in magazine
        return false;
      }else {
        // reduce frequency by 1
        availableChars.put((int) c,availableChars.get((int) c) - 1);
      }

    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

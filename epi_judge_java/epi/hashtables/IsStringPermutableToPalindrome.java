package epi.hashtables;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class IsStringPermutableToPalindrome {
  @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

  public static boolean canFormPalindrome(String s) {

    //every char must appear even number of times in even length string
    // one char can appear once is odd legth

    char[] chars = s.toCharArray();

    Set<Character> characterSet = new HashSet<>();

    for(Character c : chars){
      if(characterSet.contains(c)){
        characterSet.remove(c);
      }else {
        characterSet.add(c);
      }
    }

    return characterSet.size() == 0 || (s.length() % 2 == 1 && characterSet.size() == 1);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

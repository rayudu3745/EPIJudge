package epi.stackandqueue;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;

public class IsValidParenthesization {
  @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

  public static boolean isWellFormed(String s) {

    boolean isWellformed = true;

    // split into characters
    char[] tokens = s.toCharArray();

    // valid open Brackets
    Set<Character> openBracket = Set.of('(', '[', '{');

    // stack for evaluation
    Deque<Character> stack = new LinkedList<>();

    // loop through the tokens and for every token add to stack if its open
    // if its closed pop a token from stack and match with respt to the current bracket
    for(char token : tokens){
      if(openBracket.contains(token)){
        stack.push(token);
      }else {
        if(!stack.isEmpty()){
          char c = stack.pop();
          // encountered wrong closing bracket
          if(!isMatchingPair(c,token)) return false;
        }else {
          // encountered close bracket with out an open bracket
          return false;
        }
      }
    }
    // if stack is not empty then some open bracet is left out
    return stack.isEmpty();
  }

  public static boolean isMatchingPair(char open, char close){
    return (open == '{' && close == '}') ||
            (open == '[' && close == ']') ||
            (open == '(' && close == ')');
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

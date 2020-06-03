package epi.stackandqueue;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class EvaluateRpn {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
    // split into individual words
    String[] words = expression.split(",");

    // operators
    List<String> ops = Arrays.asList("+", "-", "*", "/");

    // stack to evaluate the expression
    Deque<String> stack = new LinkedList<>();

    // loop through the array
    // when an ops is encountered pop two elements from stack and operate them with that ops
    // and push the result back on to the stack
    for(String word : words){
      if(ops.contains(word)){
        int x = Integer.parseInt(stack.pop());
        int y = Integer.parseInt(stack.pop());
        int result = calc(y,x,word);
        stack.push(String.valueOf(result));
      }else {
        stack.push(word);
      }
    }
    return Integer.parseInt(stack.pop());
  }

  public static int calc(int A , int B, String op){
    switch (op){
      case "+" : return A + B;
      case "-" : return A - B;
      case "*" : return A * B;
      case "/" : return A / B;
    }
    throw new IllegalArgumentException(op + " not valid");
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

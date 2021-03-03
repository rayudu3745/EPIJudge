package epi.greedy;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class LargestRectangleUnderSkyline {
  @EpiTest(testDataFile = "largest_rectangle_under_skyline.tsv")

  public static int calculateLargestRectangle(List<Integer> heights) {

    // use a stack to find out index of min elem to left and right of the current elem
    // area possible with index i is A[i] * (k - j - 1) k and j are right and left index of elem that is first less than A[i]
    int max = 0;
    Deque<Integer> stack = new LinkedList<>();
    for (int i = 0 ; i < heights.size() ; i++){
      if (stack.isEmpty() || heights.get(i) >= stack.peek()){
        stack.push(i);
      }else {
        while (stack.peek() > heights.get(i)){
          int indexOfElem = stack.pop();
          int len = stack.isEmpty() ? i : i - stack.peek() - 1;
          max = Math.max(max , heights.get(indexOfElem) * len);
        }
        stack.push(i);
      }
    }
    int i = heights.size();

    // remaining stack now will sorted building
    while (!stack.isEmpty()){
      int indexOfElem = stack.pop();
      int len = stack.isEmpty() ? i : i - stack.peek() - 1;
      max = Math.max(max , heights.get(indexOfElem) * len);
    }
    return max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LargestRectangleUnderSkyline.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

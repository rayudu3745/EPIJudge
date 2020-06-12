package epi.heaps;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class SortIncreasingDecreasingArray {
  @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")

  public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {

    if(A == null) return Collections.emptyList();

    // make k sorted lists from the given list using increasing decreasing property
    List<List<Integer>> sortedLists = new ArrayList<>();

    boolean increasing = true;

    // start index of the current sublist
    int startIdx = 0;

    while (startIdx < A.size()){

      int currentIndex = startIdx + 1;

      if(increasing){
        // find the decreasing element index
        while (currentIndex < A.size() && A.get(currentIndex) > A.get(currentIndex - 1)){
          currentIndex++;
        }

        // add that sublist the sorted lists
        sortedLists.add(A.subList(startIdx,currentIndex));

        // make start index to new sublist start
        startIdx = currentIndex;
        increasing = false;

      }else {

        // find the increased element index
        while (currentIndex < A.size() && A.get(currentIndex) < A.get(currentIndex - 1)){
          currentIndex++;
        }

        // add the reverse of that sublist to the sorted lists
        List<Integer> sublist = A.subList(startIdx,currentIndex);
        Collections.reverse(sublist);
        sortedLists.add(sublist);

        startIdx = currentIndex;
        increasing = true;
      }
    }

    // use k-sorted lists merge ago now
    return SortedArraysMerge.mergeSortedArrays(sortedLists);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortIncreasingDecreasingArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

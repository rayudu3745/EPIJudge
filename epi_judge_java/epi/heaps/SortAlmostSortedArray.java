package epi.heaps;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class SortAlmostSortedArray {

  public static List<Integer>
  sortApproximatelySortedData(Iterator<Integer> sequence, int k) {

    List<Integer> sorted = new ArrayList<>();

    // each element in sequence is at-most k-distance from the sorted order location

    // use a min heap store k + 1 elements
    // heap will always have current min available in sequence
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    // add the first k + 1 elements
    while (sequence.hasNext() && k > -1){
      minHeap.add(sequence.next());
      k--;
    }

    // get current min from heap and add the next element from the sequence to heap
    while (!minHeap.isEmpty()){
      sorted.add(minHeap.poll());
      if(sequence.hasNext()) minHeap.add(sequence.next());
    }

    return sorted;
  }

  @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
  public static List<Integer>
  sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
    return sortApproximatelySortedData(sequence.iterator(), k);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortAlmostSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi.heaps;
import epi.SearchMaze;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.BiPredicate;
public class KLargestInHeap {
  @EpiTest(testDataFile = "k_largest_in_heap.tsv")

  public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {

    if(A == null || A.isEmpty() || k <= 0) return Collections.emptyList();

    List<Integer> kLargest = new ArrayList<>();

    // use the heap property : node element > child elements

    // use max heap to store currently possible max elements from the list
    PriorityQueue<ValueWithId> maxHeap = new PriorityQueue<>(Comparator.comparingInt(ValueWithId::getValue).reversed());

    int currentIdx = 0;

    maxHeap.add(new ValueWithId(currentIdx, A.get(currentIdx)));

    // process k elements
    for(int i = 0; i < k ; i++){

      ValueWithId currMax = maxHeap.poll();
      kLargest.add(currMax.value);

      currentIdx = currMax.id;

      // now add the current max childs to the heap
      // add left child
      int left = 2 * currentIdx + 1;
      if(left < A.size()) maxHeap.add(new ValueWithId(left, A.get(left)));

      // add right child
      int right = 2 * currentIdx + 2;
      if(right < A.size()) maxHeap.add(new ValueWithId(right, A.get(right)));

    }

    return kLargest;
  }

  private static class ValueWithId {

    public int id;
    public int value;

    public ValueWithId(int id, int value){
      this.id = id;
      this.value = value;
    }

    public int getValue(){
      return value;
    }
  }

  @EpiTestComparator
  public static boolean comp(List<Integer> expected, List<Integer> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestInHeap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi.heaps;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class OnlineMedian {
  public static List<Double> onlineMedian(Iterator<Integer> sequence) {

    List<Double> medians = new ArrayList<>();

    // use two heaps to store two halves of the processed elements

    // maxHeap will store first half of the elements
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    // minHeap will store second half of the elements
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    while (sequence.hasNext()){
      int x = sequence.next();
      if(minHeap.isEmpty()){
        minHeap.add(x);
      }else {
        if(x >= minHeap.peek()){
          minHeap.add(x);
        }else {
          maxHeap.add(x);
        }
      }

      if(minHeap.size() > maxHeap.size() + 1){
        maxHeap.add(minHeap.remove());
      }else if(maxHeap.size() > minHeap.size()){
        minHeap.add(maxHeap.remove());
      }

      double median = minHeap.size() == maxHeap.size() ? 0.5 * (minHeap.peek() + maxHeap.peek()) : minHeap.peek();
      medians.add(median);
    }

    return medians;
  }

  @EpiTest(testDataFile = "online_median.tsv")
  public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
    return onlineMedian(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "OnlineMedian.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

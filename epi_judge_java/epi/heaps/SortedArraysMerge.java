package epi.heaps;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SortedArraysMerge {
  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")

  public static List<Integer>
  mergeSortedArrays(List<List<Integer>> sortedArrays) {

    List<Integer> sorted = new ArrayList<>();

    // indexArray[i] = current non processed index in ith list of the sortedArrays
    int[] indexArray = new int[sortedArrays.size()];

    // minHeap to find the current min element of the each individual min in each list
    PriorityQueue<ValueWithId> minHeap = new PriorityQueue<>(Comparator.comparingInt(ValueWithId::getValue));

    // add all the first elements from the list
    for(int i = 0 ; i < indexArray.length; i++){

      // add the cur element from the list it list is not empty
      if(indexArray[i] < sortedArrays.get(i).size()){
        minHeap.add(new ValueWithId(i, sortedArrays.get(i).get(indexArray[i])));
      }
    }


    while (!minHeap.isEmpty()){

      // pop the current min
      ValueWithId min = minHeap.poll();

      // add this to the result
      sorted.add(min.value);

      // if the min is from ith list then point index of that to the next on
      indexArray[min.id] = indexArray[min.id] + 1;

      // add an element from the current min removed list if its not empty
      if(indexArray[min.id] < sortedArrays.get(min.id).size()){
        minHeap.add(new ValueWithId(min.id, sortedArrays.get(min.id).get(indexArray[min.id])));
      }

    }

    return sorted;
  }

  static class ValueWithId{
    public int id;
    public int value;

    ValueWithId(int id, int value){
      this.id = id;
      this.value = value;
    }

    public int getValue(){
      return value;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

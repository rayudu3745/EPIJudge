package epi.stackandqueue;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SunsetView {
  public static List<Integer>
  examineBuildingsWithSunset(Iterator<Integer> sequence) {

    // goint from west to east
    int id = 0;

    // stack to maintain current building which can view sunset
    Deque<HeightWithId> viewableBuilding = new LinkedList<>();

    // loop through the sequence
    while (sequence.hasNext()){
      int curBuildingHeight = sequence.next();

      // pop the buildings from the stack which can be blocked by the current building
      while (!viewableBuilding.isEmpty() && viewableBuilding.peekFirst().height <= curBuildingHeight){
        viewableBuilding.removeFirst();
      }

      // add the current building to viewable Buildings
      viewableBuilding.addFirst(new HeightWithId(id,curBuildingHeight));
      id++;
    }

    List<Integer> result = new ArrayList<>();
    while (!viewableBuilding.isEmpty()){
      result.add(viewableBuilding.removeFirst().id);
    }

    return result;
  }

  static class HeightWithId{
    public int id;
    public int height;

    public HeightWithId(int id, int height){
      this.id = id;
      this.height = height;
    }
  }
  @EpiTest(testDataFile = "sunset_view.tsv")
  public static List<Integer>
  examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
    return examineBuildingsWithSunset(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SunsetView.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

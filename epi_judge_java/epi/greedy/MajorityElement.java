package epi.greedy;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Iterator;
import java.util.List;
public class MajorityElement {

  public static String majoritySearch(Iterator<String> stream) {

    // maintain current majority element along with counter
    // decrease the counter when different element is encountered and change majority element when counter is 0
    int counter = 0;
    String majority = null;
    for (Iterator<String> it = stream; it.hasNext(); ) {
      String current = it.next();
      if (counter == 0){
        majority = current;
        counter++;
        continue;
      }
      if (majority.equals(current)){
        counter++;
      }else {
        counter--;
      }
    }
    return majority;
  }
  @EpiTest(testDataFile = "majority_element.tsv")
  public static String majoritySearchWrapper(List<String> stream) {
    return majoritySearch(stream.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MajorityElement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

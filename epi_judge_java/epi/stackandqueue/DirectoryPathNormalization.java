package epi.stackandqueue;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class DirectoryPathNormalization {
  @EpiTest(testDataFile = "directory_path_normalization.tsv")

  public static String shortestEquivalentPath(String path) {

    // split into tokens
    String[] tokens = path.split("/");

    // doubly linked list to evaluate
    Deque<String> deque = new LinkedList<>();

    // loop through the tokens
    for(int i = 0; i < tokens.length; i++){
      // remove all empty except first one which indicates its abs path
      if(tokens[i].equals(".") || (tokens[i].equals("") && i > 0)){
        // ignore
      }else if(tokens[i].equals("..")){
        if(deque.isEmpty() || deque.peekFirst().equals("..")){
          // is empty add this
          deque.addFirst(tokens[i]);
        }else if(deque.peekFirst().equals("")){
          // going back of root is invalid
          throw new IllegalStateException("not valid path");
        }else {
          // remove the first element
          deque.removeFirst();
        }
      }else {
        // add the current folder
        deque.addFirst(tokens[i]);
      }
    }

    // now use the deque as a queue and construct the new normalized path
    List<String> list  = new ArrayList<>();
    while (!deque.isEmpty()){
      list.add(deque.removeLast());
    }

    if(list.isEmpty() || (list.size() == 1 && list.get(0).equals(""))){
      return "/";
    }

    return String.join("/", list);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

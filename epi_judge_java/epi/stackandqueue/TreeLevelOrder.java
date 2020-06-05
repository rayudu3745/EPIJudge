package epi.stackandqueue;
import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.stream.Collectors;

public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>>
  binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {

    if(tree == null) return Collections.emptyList();

    List<List<Integer>> result = new ArrayList<>();

    // use two queues
    // one for the current level and other for the next level
    Queue<BinaryTreeNode<Integer>> curLevel = new LinkedList<>();
    curLevel.add(tree);

    while (!curLevel.isEmpty()){
      Queue<BinaryTreeNode<Integer>> nextLevel = new LinkedList<>();
      List<Integer> elements = new ArrayList<>();
      while (!curLevel.isEmpty()){
        BinaryTreeNode<Integer> curNode = curLevel.poll();
        elements.add(curNode.data);
        // add its childs to next level
        if(curNode.left != null) nextLevel.add(curNode.left);
        if(curNode.right != null) nextLevel.add(curNode.right);
      }

      // when current level is completed make add its elements to result list
      // and make next level as current level
      result.add(elements);
      curLevel = nextLevel;
    }

    return result;
  }

  static class NodeWithLevel{
    BinaryTreeNode<Integer> element;
    int level;

    public NodeWithLevel(BinaryTreeNode<Integer> element, int level){
      this.element = element;
      this.level = level;
    }

    public int getLevel(){
      return level;
    }

    public int getInt(){
      return element.data;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

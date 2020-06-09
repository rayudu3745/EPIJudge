package epi.binarytree;
import epi.BinaryTree;
import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class TreeInorder {

  private static class NodeAndState {
    public BinaryTreeNode<Integer> node;
    public Boolean leftSubtreeTraversed;

    public NodeAndState(BinaryTreeNode<Integer> node,
                        Boolean leftSubtreeTraversed) {
      this.node = node;
      this.leftSubtreeTraversed = leftSubtreeTraversed;
    }
  }

  @EpiTest(testDataFile = "tree_inorder.tsv")
  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {

    List<Integer> result = new ArrayList<>();

    // use an explicit stack
    Deque<BinaryTreeNode<Integer>> stack = new LinkedList<>();
    BinaryTreeNode<Integer> curr = tree;

    while (!stack.isEmpty() || curr != null){
      // if there a left to the curr add that to the stack
      if(curr != null){
        stack.push(curr);
        curr = curr.left;
      }else {
        // all the left nodes are completed until this curr
        curr = stack.pop();
        result.add(curr.data);
        // move to right
        curr = curr.right;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi.binarytree;
import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class TreeExterior {

  public static List<BinaryTreeNode<Integer>>
  exteriorBinaryTree(BinaryTreeNode<Integer> tree) {

    List<BinaryTreeNode<Integer>> result = new ArrayList<>();

    if(tree == null) return result;

    // exterior =
    // 1) all nodes from root to leftmost leaf
    // 2) all leaves from left to right
    // 3) all the nodes from rightmost leaf to root
    result.add(tree);
    result.addAll(leftBoundaryAndLeftLeaves(tree.left,true));
    result.addAll(rightBoundaryAndRightLeaves(tree.right,true));

    return result;
  }

  private static List<BinaryTreeNode<Integer>> leftBoundaryAndLeftLeaves(BinaryTreeNode<Integer> node, boolean isBoundary){
    List<BinaryTreeNode<Integer>> result = new ArrayList<>();

    // base case
    if(node == null) return result;

    // if current node is in boundary or leaf
    if(isLeaf(node) || isBoundary){
      result.add(node);
    }

    // move to the subtrees and add eligible nodes from those

    // moving left
    result.addAll(leftBoundaryAndLeftLeaves(node.left, isBoundary));

    // right subree is boundary when left nodes are present
    result.addAll(leftBoundaryAndLeftLeaves(node.right, isBoundary && node.left == null));

    return result;
  }

  private static List<BinaryTreeNode<Integer>> rightBoundaryAndRightLeaves(BinaryTreeNode<Integer> node, boolean isBoundary){
    List<BinaryTreeNode<Integer>> result = new ArrayList<>();

    // base case
    if(node == null) return result;

    // move to the subtrees and add eligible nodes from those

    // left subtree is boundary when right nodes are present
    result.addAll(rightBoundaryAndRightLeaves(node.left, isBoundary && node.right == null));

    // moving right
    result.addAll(rightBoundaryAndRightLeaves(node.right, isBoundary));

    // if current node is in boundary or leaf
    if(isLeaf(node) || isBoundary){
      result.add(node);
    }

    return result;
  }

  private static boolean isLeaf(BinaryTreeNode<Integer> node){
    return node.left == null && node.right == null;
  }
  private static List<Integer> createOutputList(List<BinaryTreeNode<Integer>> L)
      throws TestFailure {
    if (L.contains(null)) {
      throw new TestFailure("Resulting list contains null");
    }
    List<Integer> output = new ArrayList<>();
    for (BinaryTreeNode<Integer> l : L) {
      output.add(l.data);
    }
    return output;
  }

  @EpiTest(testDataFile = "tree_exterior.tsv")
  public static List<Integer>
  exteriorBinaryTreeWrapper(TimedExecutor executor,
                            BinaryTreeNode<Integer> tree) throws Exception {
    List<BinaryTreeNode<Integer>> result =
        executor.run(() -> exteriorBinaryTree(tree));

    return createOutputList(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeExterior.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

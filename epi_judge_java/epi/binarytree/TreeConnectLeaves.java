package epi.binarytree;
import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class TreeConnectLeaves {

  private static List<BinaryTreeNode<Integer>> leaves;

  public static List<BinaryTreeNode<Integer>>
  createListOfLeaves(BinaryTreeNode<Integer> tree) {
    leaves = new ArrayList<>();
    inorderRecurse(tree);
    return leaves;
  }

  private static void inorderRecurse(BinaryTreeNode<Integer> node){

    // base case
    if(node == null) return;

    // if the current node is leaf then add it to the leaves
    if(node.left == null && node.right == null) leaves.add(node);

    inorderRecurse(node.left);
    inorderRecurse(node.right);
  }
  @EpiTest(testDataFile = "tree_connect_leaves.tsv")
  public static List<Integer>
  createListOfLeavesWrapper(TimedExecutor executor,
                            BinaryTreeNode<Integer> tree) throws Exception {
    List<BinaryTreeNode<Integer>> result =
        executor.run(() -> createListOfLeaves(tree));

    if (result.stream().anyMatch(x -> x == null || x.data == null)) {
      throw new TestFailure("Result can't contain null");
    }

    List<Integer> extractedRes = new ArrayList<>();
    for (BinaryTreeNode<Integer> x : result) {
      extractedRes.add(x.data);
    }
    return extractedRes;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeConnectLeaves.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

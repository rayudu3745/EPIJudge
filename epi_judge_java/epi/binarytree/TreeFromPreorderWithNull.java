package epi.binarytree;
import epi.BinaryTree;
import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class TreeFromPreorderWithNull {

  // variable to track current root index for the recursive part
  private static Integer currentRootIdx;

  public static BinaryTreeNode<Integer>
  reconstructPreorder(List<Integer> preorder) {

    if (preorder == null || preorder.isEmpty()) return null;

    // just try to assign a number to each node starting with 0 from root
    // elements in the left subtree have numbers before the right subtree
    currentRootIdx = 0;

    return findRoot(preorder);
  }

  private static BinaryTreeNode<Integer> findRoot(List<Integer> preorder){

    Integer currentRoot = preorder.get(currentRootIdx);

    // move one index forward
    currentRootIdx++;

    // base case
    if(currentRoot == null) return null;

    // now the index will point to left subtree root inside this recursion
    BinaryTreeNode<Integer> left = findRoot(preorder);

    // left recursion will be completely done and all the left elements are processed
    // now the index will point to right subtree root inside this recursion
    BinaryTreeNode<Integer> right = findRoot(preorder);

    return new BinaryTreeNode<>(currentRoot,left,right);

  }
  @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
  public static BinaryTreeNode<Integer>
  reconstructPreorderWrapper(TimedExecutor executor, List<String> strings)
      throws Exception {
    List<Integer> ints = new ArrayList<>();
    for (String s : strings) {
      if (s.equals("null")) {
        ints.add(null);
      } else {
        ints.add(Integer.parseInt(s));
      }
    }

    return executor.run(() -> reconstructPreorder(ints));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderWithNull.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

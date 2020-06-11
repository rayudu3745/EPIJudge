package epi.binarytree;
import epi.BinaryTree;
import epi.TreeLike;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
public class TreeRightSibling {
  public static class BinaryTreeNode<T> extends TreeLike<T, BinaryTreeNode<T>> {
    public T data;
    public BinaryTreeNode<T> left, right;
    public BinaryTreeNode<T> next = null; // Populates this field.

    public BinaryTreeNode(T data) { this.data = data; }

    @Override
    public T getData() {
      return data;
    }

    @Override
    public BinaryTreeNode<T> getLeft() {
      return left;
    }

    @Override
    public BinaryTreeNode<T> getRight() {
      return right;
    }
  }

  public static void constructRightSibling(BinaryTreeNode<Integer> tree) {

    if(tree == null) return;

    // from the current node set the right siblings of the childs
    if(tree.left == null && tree.right == null) return;

    // right sibling of a left child is right child of the node
    tree.left.next = tree.right;

    // right sibling of the right child is left node of the roots right sibling
    if(tree.next != null) tree.right.next = tree.next.left;

    // recurse in order
    constructRightSibling(tree.left);
    constructRightSibling(tree.right);
  }

  private static BinaryTreeNode<Integer>
  cloneTree(BinaryTree<Integer> original) {
    if (original == null) {
      return null;
    }
    BinaryTreeNode<Integer> cloned = new BinaryTreeNode<>(original.data);
    cloned.left = cloneTree(original.left);
    cloned.right = cloneTree(original.right);
    return cloned;
  }

  @EpiTest(testDataFile = "tree_right_sibling.tsv")
  public static List<List<Integer>>
  constructRightSiblingWrapper(TimedExecutor executor, BinaryTree<Integer> tree)
      throws Exception {
    BinaryTreeNode<Integer> cloned = cloneTree(tree);

    executor.run(() -> constructRightSibling(cloned));

    List<List<Integer>> result = new ArrayList<>();
    BinaryTreeNode<Integer> levelStart = cloned;
    while (levelStart != null) {
      List<Integer> level = new ArrayList<>();
      BinaryTreeNode<Integer> levelIt = levelStart;
      while (levelIt != null) {
        level.add(levelIt.data);
        levelIt = levelIt.next;
      }
      result.add(level);
      levelStart = levelStart.left;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeRightSibling.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

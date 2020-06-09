package epi.binarytree;
import epi.BinaryTree;
import epi.TreeLike;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class KthNodeInTree {
  public static class BinaryTreeNode<T> extends TreeLike<T, BinaryTreeNode<T>> {
    public T data;
    public BinaryTreeNode<T> left, right;
    public int size;

    public BinaryTreeNode(T data, BinaryTreeNode<T> left,
                          BinaryTreeNode<T> right, int size) {
      this.data = data;
      this.left = left;
      this.right = right;
      this.size = size;
    }

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

  public static BinaryTreeNode<Integer>
  findKthNodeBinaryTree(BinaryTreeNode<Integer> tree, int k) {

    if(tree == null) return null;

    int size = tree.size;

    // check if kth node in left subtree or right subtree
    int rightSubtreeSize = tree.right != null ? tree.right.size : 0;

    int leftSubtreeSizeIncludingRoot = size - rightSubtreeSize;

    // if the current node is at kth position
    if(leftSubtreeSizeIncludingRoot == k) return tree;

    if(leftSubtreeSizeIncludingRoot > k) {
      // number of nodes in left subtree are more than k
      // so kth node will be found in the tree
      return findKthNodeBinaryTree(tree.left,k);
    }else {
      // nth node in right subtree will be leftSubtreeSizeIncludingRoot + nth node in tree
      return findKthNodeBinaryTree(tree.right, k - leftSubtreeSizeIncludingRoot);
    }

  }
  public static BinaryTreeNode<Integer>
  convertToTreeWithSize(BinaryTree<Integer> original) {
    if (original == null)
      return null;
    BinaryTreeNode<Integer> left = convertToTreeWithSize(original.left);
    BinaryTreeNode<Integer> right = convertToTreeWithSize(original.right);
    int lSize = left == null ? 0 : left.size;
    int rSize = right == null ? 0 : right.size;
    return new BinaryTreeNode<>(original.data, left, right, 1 + lSize + rSize);
  }

  @EpiTest(testDataFile = "kth_node_in_tree.tsv")
  public static int findKthNodeBinaryTreeWrapper(TimedExecutor executor,
                                                 BinaryTree<Integer> tree,
                                                 int k) throws Exception {
    BinaryTreeNode<Integer> converted = convertToTreeWithSize(tree);

    BinaryTreeNode<Integer> result =
        executor.run(() -> findKthNodeBinaryTree(converted, k));

    if (result == null) {
      throw new TestFailure("Result can't be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthNodeInTree.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

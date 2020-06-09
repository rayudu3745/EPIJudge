package epi.binarytree;
import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PathSum {
  @EpiTest(testDataFile = "path_sum.tsv")

  public static boolean hasPathSum(BinaryTreeNode<Integer> tree,
                                   int remainingWeight) {
    if(tree == null) return false;
    return recursive(tree,remainingWeight);
  }

  private static boolean recursive(BinaryTreeNode<Integer> node, int remainingWeight){

    // if the current node is leaf
    // current remaining weight should equal the leaf data
    if(node.left == null && node.right == null) {
      return node.data.equals(remainingWeight);
    }

    // move towards the subtree after removing current weight from remaining weight
    boolean left = node.left != null && recursive(node.left, remainingWeight - node.data);
    boolean right = node.right != null && recursive(node.right, remainingWeight - node.data);

    return left || right;

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PathSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

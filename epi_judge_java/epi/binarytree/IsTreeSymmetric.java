package epi.binarytree;
import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeSymmetric {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    if(tree == null) return true;

    // check if left subtree and right subtree are mirror images of each other
    return checkSymmetry(tree.left,tree.right);
  }

  private static boolean checkSymmetry(BinaryTreeNode<Integer> root1, BinaryTreeNode<Integer> root2){

    // recursive case for two tree to be mirror images of each other
    if(root1 != null && root2 != null){
      return root1.data.intValue() == root2.data.intValue() &&
              checkSymmetry(root1.left,root2.right) &&
              checkSymmetry(root1.right,root2.left);
    }

    // base base (if only one is null return false)
    return root1 == null && root2 == null;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

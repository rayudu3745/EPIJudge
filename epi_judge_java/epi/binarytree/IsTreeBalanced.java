package epi.binarytree;
import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    // call recursive function
    return isBalancedWithHeight(tree).isBalanced;
  }

  private static BalancedStatusWithHeight isBalancedWithHeight(BinaryTreeNode<Integer> root){
    // base case of null subtree
    if(root == null) return new BalancedStatusWithHeight(true,-1);

    // recurse on left and right subtree
    BalancedStatusWithHeight left = isBalancedWithHeight(root.left);
    BalancedStatusWithHeight right = isBalancedWithHeight(root.right);

    // check for balanced status
    boolean isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) <=1;
    int height = Math.max(left.height,right.height) + 1;

    return new BalancedStatusWithHeight(isBalanced, height);
  }

  private static class BalancedStatusWithHeight{
    public boolean isBalanced;
    public int height;

    public BalancedStatusWithHeight(boolean isBalanced, int height){
      this.height = height;
      this.isBalanced = isBalanced;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi.binarytree;
import epi.BinaryTree;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class TreeWithParentInorder {
  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {

    List<Integer> result = new ArrayList<>();
    if(tree == null) return result;

    // find the first node
    BinaryTree<Integer> curr = tree;
    while (curr.left != null){
      curr = curr.left;
    }

    // keep finding the successor of the current
    while (curr != null){
      result.add(curr.data);
      curr = SuccessorInTree.findSuccessor(curr);
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

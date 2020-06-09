package epi.binarytree;
import epi.BinaryTree;
import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class TreeFromPreorderInorder {
  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")

  public static BinaryTreeNode<Integer>
  binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {

    // recursively find the root of given preorder and inorder sequence
    return findRoot(preorder,inorder);
  }

  // needs to be optimized
  private static BinaryTreeNode<Integer> findRoot(List<Integer> preorder, List<Integer> inorder){

    // base case
    if(inorder.isEmpty()) return null;

    // find the first element in preorder which is part of current inorder
    int minIndex = preorder.size();
    for(int node : inorder){
      minIndex = Math.min(minIndex,preorder.indexOf(node));
    }

    int rootNode = preorder.get(minIndex);

    // now we know in inorder all the nodes before rootNode will lie in left subtree
    // and all nodes after rootNode will be in right subree
    int rootIndex = inorder.indexOf(rootNode);
    List<Integer> leftNodes = inorder.subList(0,rootIndex);
    BinaryTreeNode<Integer> left = findRoot(preorder, leftNodes);

    List<Integer> rightNodes;
    if(rootIndex == inorder.size()-1){
      rightNodes = Collections.emptyList();
    }else {
      rightNodes = inorder.subList(rootIndex + 1,inorder.size());
    }
    BinaryTreeNode<Integer> right = findRoot(preorder,rightNodes);

    return new BinaryTreeNode<>(rootNode,left,right);

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

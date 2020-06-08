package epi.binarytree;
import epi.BinaryTree;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import javax.management.InstanceNotFoundException;

public class LowestCommonAncestorWithParent {

  public static BinaryTree<Integer> lca(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {

    if(node0 == node1) return node0;

    // find height of both nodes
    int h0 = height(node0);
    int h1 = height(node1);

    // bring both nodes to same level
    BinaryTree<Integer> deepestNode = h0 > h1 ? node0 : node1;
    BinaryTree<Integer> shallowNode = deepestNode == node0 ? node1 : node0;
    int diff = Math.abs(h0 - h1);
    while (diff > 0){
      deepestNode = deepestNode.parent;
      diff--;
    }

    // now simultaneously move towards root until they intersect at lca
    while (shallowNode != null && shallowNode != deepestNode){
      shallowNode = shallowNode.parent;
      deepestNode = deepestNode.parent;
    }
    return shallowNode;
  }

  public static int height(BinaryTree<Integer> node){
    BinaryTree<Integer> curr = node;
    int height  = 0;
    while (curr != null){
      curr = curr.parent;
      height++;
    }
    return height;
  }

  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> lca(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

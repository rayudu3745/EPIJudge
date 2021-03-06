package epi.hashtables;
import epi.BinaryTree;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorCloseAncestor {

  public static BinaryTree<Integer> lca(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {

    if(node0 == node1) return node0;
    Set<BinaryTree<Integer>> visited = new HashSet<>();

    // move each node alternatively towards the root and
    // check if it is already visited by the other path

    while (node0 != null || node1 != null){

      // move each node in tandem and check if it is already vistied by the other
      if(node0 != null){
        if(visited.contains(node0)) return node0;
        visited.add(node0);
        node0 = node0.parent;
      }

      if(node1 != null){
        if(visited.contains(node1)) return node1;
        visited.add(node1);
        node1 = node1.parent;
      }

    }

    return null;
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
            .runFromAnnotations(args, "LowestCommonAncestorCloseAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

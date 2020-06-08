package epi.binarytree;
import epi.BinaryTree;
import epi.BinaryTreeNode;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestor {
  public static BinaryTreeNode<Integer> lca(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {

    // abnormal cases
    if(tree == null) return null;
    if(node0 == node1) return node0;

    return lcaRecursive(tree,node0,node1).lca;
  }

  private static NodesFoundStatusWithLCA lcaRecursive(BinaryTreeNode<Integer> currentNode, BinaryTreeNode<Integer> node0,
                                                      BinaryTreeNode<Integer> node1){
    // base case
    if(currentNode == null) return new NodesFoundStatusWithLCA(null,false,false);

    // recursion
    NodesFoundStatusWithLCA left = lcaRecursive(currentNode.left,node0,node1);
    NodesFoundStatusWithLCA right = lcaRecursive(currentNode.right,node0,node1);

    // if one of the subtree has lca return lca from that
    if(left.lca != null) return left;
    if(right.lca != null) return right;

    // check if current node is lca
    boolean node0Found = left.node0Found || right.node0Found || currentNode == node0;
    boolean node1Found = left.node1Found || right.node1Found || currentNode == node1;
    if(node0Found && node1Found) return new NodesFoundStatusWithLCA(currentNode,true,true);

    // return current found status of both nodes
    return new NodesFoundStatusWithLCA(null,node0Found,node1Found);

  }


  private static class NodesFoundStatusWithLCA{
    boolean node0Found;
    boolean node1Found;
    BinaryTreeNode<Integer> lca;

    public NodesFoundStatusWithLCA(BinaryTreeNode<Integer> lca, boolean node0Found, boolean node1Found){
      this.lca = lca;
      this.node0Found = node0Found;
      this.node1Found = node1Found;
    }
  }


  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> lca(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

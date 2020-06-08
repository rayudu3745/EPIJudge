package epi.binarytree;
import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class SumRootToLeaf {
  @EpiTest(testDataFile = "sum_root_to_leaf.tsv")

  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {

    if(tree == null) return 0;

    // list to store the numbers
    List<String> binaryNumbers = new ArrayList<>();

    String s = "";
    // construct the numbers
    addDigit(s,tree,binaryNumbers);

    int sum = 0;
    for(String binaryNum : binaryNumbers){
      sum += Integer.parseInt(binaryNum,2);
    }

    return sum;
  }

  private static void addDigit(String s, BinaryTreeNode<Integer> node, List<String> numbers){

    // add the current digit
    s = s + node.data.toString();

    // base case leaf
    // if current node is leaf then add it to the numbers and return
    if(node.left == null && node.right == null){
      numbers.add(s);
      return;
    }

    // move towards the leaf after adding current digit
    if(node.left != null) addDigit(s,node.left,numbers);
    if(node.right != null) addDigit(s,node.right,numbers);

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SumRootToLeaf.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

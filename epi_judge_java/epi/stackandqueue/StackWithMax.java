package epi.stackandqueue;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
public class StackWithMax {

  //tuple with the element and max
  public static class ElementWithCachedMax{
    public Integer element;
    public Integer max;

    public ElementWithCachedMax(Integer element, Integer max){
      this.element = element;
      this.max = max;
    }
  }

  //at every node along with the element
  // we will also store the max value until that point in stack from bottom
  public static class Stack {
    private final Deque<ElementWithCachedMax> elementWithCachedMax = new LinkedList<>();
    public boolean empty() {
      return elementWithCachedMax.isEmpty();
    }
    public Integer max() {
      if(empty()){
        throw new IllegalStateException("max(): empty stack");
      }
      return elementWithCachedMax.peek().max;
    }
    public Integer pop() {
      if(empty()){
        throw new IllegalStateException("pop(): empty stack");
      }
      return elementWithCachedMax.removeFirst().element;
    }
    public void push(Integer x) {
      int max = empty() ? x : Math.max(x,max());
      elementWithCachedMax.addFirst(new ElementWithCachedMax(x, max));
    }
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class StackOp {
    public String op;
    public int arg;

    public StackOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testDataFile = "stack_with_max.tsv")
  public static void stackTester(List<StackOp> ops) throws TestFailure {
    try {
      Stack s = new Stack();
      int result;
      for (StackOp op : ops) {
        switch (op.op) {
        case "Stack":
          s = new Stack();
          break;
        case "push":
          s.push(op.arg);
          break;
        case "pop":
          result = s.pop();
          if (result != op.arg) {
            throw new TestFailure("Pop: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "max":
          result = s.max();
          if (result != op.arg) {
            throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "empty":
          result = s.empty() ? 1 : 0;
          if (result != op.arg) {
            throw new TestFailure("Empty: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(s));
          }
          break;
        default:
          throw new RuntimeException("Unsupported stack operation: " + op.op);
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StackWithMax.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

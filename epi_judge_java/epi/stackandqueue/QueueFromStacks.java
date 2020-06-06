package epi.stackandqueue;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class QueueFromStacks {

  public static class Queue {

    // use one stack for enqueueing elements
    Stack<Integer> enque = new Stack<>();
    // one for dequeueing
    Stack<Integer> deque = new Stack<>();

    public void enqueue(Integer x) {
      enque.push(x);
    }

    public Integer dequeue() {

      // if both stacks are empty
      if(deque.empty() && enque.empty()) throw new IllegalStateException("queue is empty");

      // if deque is empty them pop all the elements from enque stack and populate deque stack
      // now the deque stack will pop elements in FIFO order
      if(deque.empty()){
        while (!enque.empty()){
          deque.push(enque.pop());
        }
      }

      return deque.pop();
    }
  }

  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testDataFile = "queue_from_stacks.tsv")
  public static void queueTester(List<QueueOp> ops) throws TestFailure {
    try {
      Queue q = new Queue();

      for (QueueOp op : ops) {
        switch (op.op) {
        case "QueueWithMax":
          q = new Queue();
          break;
        case "enqueue":
          q.enqueue(op.arg);
          break;
        case "dequeue":
          int result = q.dequeue();
          if (result != op.arg) {
            throw new TestFailure("Dequeue: expected " +
                                  String.valueOf(op.arg) + ", got " +
                                  String.valueOf(result));
          }
          break;
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "QueueFromStacks.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

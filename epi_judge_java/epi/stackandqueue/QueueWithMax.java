package epi.stackandqueue;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

public class QueueWithMax {

  // queue to store queue elements
  private Queue<Integer> queue = new LinkedList<>();

  // doubly linked list to store max elements
  // current maximum will be at start of the list
  // deque will contain elems which can be max in desc order
  private Deque<Integer> deque = new LinkedList<>();

  public void enqueue(Integer x) {

    // add to the queue
    queue.add(x);

    // remove all the elements less than this from the back of the deque
    // because they can never become max as current elem will be there in the queue
    // until those elems are removed
    while (!deque.isEmpty() &&  deque.peekLast() < x){
      deque.removeLast();
    }

    // add current element to end of deque
    deque.addLast(x);

  }

  public Integer dequeue() {

    if(queue.isEmpty()) throw new IllegalStateException("queue is empty");

    int elem = queue.poll();

    // if max is removed then remove max from head of deque
    if(deque.peekFirst().equals(elem)){
      deque.removeFirst();
    }
    return elem;
  }

  public Integer max() {

    if(queue.isEmpty()) throw new IllegalStateException("queue is empty");

    // head in the deque is the current max element
    return deque.peekFirst();
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

  @EpiTest(testDataFile = "queue_with_max.tsv")
  public static void queueTester(List<QueueOp> ops) throws TestFailure {
    try {
      QueueWithMax q = new QueueWithMax();

      for (QueueOp op : ops) {
        switch (op.op) {
        case "QueueWithMax":
          q = new QueueWithMax();
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
        case "max":
          int s = q.max();
          if (s != op.arg) {
            throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(s));
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
            .runFromAnnotations(args, "QueueWithMax.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

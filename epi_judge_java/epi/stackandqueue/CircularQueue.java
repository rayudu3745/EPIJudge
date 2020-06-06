package epi.stackandqueue;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class CircularQueue {

  public static class Queue {

    // array to store the queue elements
    private int[] elements;

    // head is index of front element of array
    private int head;

    // tail is current empty spot in the array
    private int tail;

    // size of the queue(no. of elements in the queue)
    private int size;

    public Queue(int capacity) {
      elements = new int[capacity];
      head = 0;
      tail = 0;
      size = 0;
    }

    public void enqueue(Integer x) {

      // if size == capacity then resize the array
      if(size == elements.length){

        int[] newArray = new int[2 * size];
        // circulary copy the contents of old array to new array
        // making again head pointing to 0
        // and tail to size
        System.arraycopy(elements,head,newArray,0,size - head);
        System.arraycopy(elements,0,newArray,size - head, head);
        head = 0;
        tail = elements.length;
        elements = newArray;

      }else if (tail == elements.length){
        // array is not full but tail is out of range
        // so make tail point to 0 index
        tail = 0;
      }

      // add element at tail and increment tail
      elements[tail] = x;
      tail++;
      size++;

    }

    public Integer dequeue() {
      if(size < 1) throw new IllegalStateException("queue is empty");

      // element to remove
      int elem = elements[head];

      // point head to next element int the queue
      head++;
      // if head goes out of range means point to 0
      if(head == elements.length) head = 0;
      size--;
      return elem;
    }

    public int size() {
      return size;
    }

    @Override
    public String toString() {
      // not implemented
      return super.toString();
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

    @Override
    public String toString() {
      return op;
    }
  }

  @EpiTest(testDataFile = "circular_queue.tsv")
  public static void queueTester(List<QueueOp> ops) throws TestFailure {
    Queue q = new Queue(1);
    int opIdx = 0;
    for (QueueOp op : ops) {
      switch (op.op) {
      case "Queue":
        q = new Queue(op.arg);
        break;
      case "enqueue":
        q.enqueue(op.arg);
        break;
      case "dequeue":
        int result = q.dequeue();
        if (result != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, result);
        }
        break;
      case "size":
        int s = q.size();
        if (s != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, s);
        }
        break;
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CircularQueue.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

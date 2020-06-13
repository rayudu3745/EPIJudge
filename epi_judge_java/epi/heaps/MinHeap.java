package epi.heaps;

import java.util.Comparator;

public class MinHeap<T> {

    private T[] keys;

    private Comparator<T> comparator;

    // current last
    private int idx;


    public MinHeap(int capacity, Comparator<T> comparator){
        keys = (T[]) new Object[capacity + 1];
        this.comparator = comparator;
    }

    public void add(T key){
        if(idx == keys.length - 1) throw new IllegalStateException("heap is full");
        keys[++idx] = key;
        swim(idx);
    }

    public T poll(){
        if(idx == 0) throw  new IllegalStateException("heap is empty");
        T max = keys[1];
        swap(1,idx--);
        sink(1);
        keys[idx + 1] = null;
        return max;
    }

    public T peek(){
        if(idx == 0) throw  new IllegalStateException("heap is empty");
        return keys[1];
    }


    // node at kth index becomes smaller than of root
    private void swim(int k){
        while (k > 1 && comparator.compare(keys[k/2],keys[k]) > 0){
            swap(k/2,k);
            k = k/2;
        }
    }

    // node at kth index becomes bigger that one or both of its childs
    private void sink(int k){
        while (2 * k < keys.length){
            int j = 2 * k;
            // compare left child and right child
            if(j < idx && comparator.compare(keys[j+1],keys[j]) < 0) j++;
            if(comparator.compare(keys[j],keys[k]) >= 0) break;
            swap(k,j);
            k = j;
        }

    }

    private void swap(int i, int j){
        T temp = keys[i];
        keys[i] = keys[j];
        keys[j] = temp;
    }


    public static void main(String... args){
        MinHeap<Integer> minHeap = new MinHeap<>(3,Comparator.comparingInt(Integer::intValue));
        minHeap.add(3);
        minHeap.add(5);
        minHeap.add(1);
        System.out.println(minHeap.peek());
        System.out.println(minHeap.poll());
        minHeap.add(-100);
        System.out.println(minHeap.poll());
        System.out.println(minHeap.peek());
    }
}

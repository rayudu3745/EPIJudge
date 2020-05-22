package epi.linkedlist;

import epi.ListNode;

public class Primer {

    public static <T> ListNode<T> search(ListNode<T> head, T key){
        return null;
    }

    public static <T> void printList(ListNode<T> head){
        ListNode<T> current = head;
        while(current != null){
            System.out.print(current.data + "->");
            current = current.next;
        }
        System.out.print("null");
    }

    public static <T> ListNode<T> reverse(ListNode<T> head){
        ListNode<T> current = head;
        ListNode<T> prev = null;
        while(current != null){
            ListNode<T> next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public static ListNode<Integer> sampleList(){
        ListNode<Integer> node1 = new ListNode(11,null);
        ListNode<Integer> node2 = new ListNode(3,null);
        ListNode<Integer> node3 = new ListNode(5,null);
        ListNode<Integer> node4 = new ListNode(7,null);
        ListNode<Integer> node5 = new ListNode(2,null);
        node1.next = node2; node2.next = node3; node3.next = node4; node4.next = node5;
        return node1;
    }

    public static void main(String... args){

    }
}

package org.duo.common;

/**
 * 队列：先进先出，进入的顺序为：a->b->c->d；出去的顺序也是：a->b->c->d
 * 栈：先进后出，进入的顺序为：a->b->c->d；出去的顺序也是：d->c->b->a
 */
public class ReverseList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            value = data;
        }
    }

    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {

        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        System.out.println(n1.value + " " + n1.next);
        reverseLinkedList(n1);
        System.out.println(n1.value + " " + n1.next);
    }
}

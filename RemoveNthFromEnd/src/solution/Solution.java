package solution;

import sun.java2d.pipe.SpanIterator;

import java.awt.*;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);

        listNode.addList(2);
        listNode.addList(3);
        listNode.addList(4);
        listNode.print();

        removeNthFromEnd3(listNode, 2).print();

//        LinkedList head = new LinkedList();
//        head.add(1);
//        head.add(2);
//        head.add(3);
//        head.add(4);
//        head.add(5);
//        System.out.println(head);
    }

    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        if (fast == null) {
            return null;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return head;
    }

    public static ListNode removeNthFromEnd3(ListNode head, int n) {
        int len = lengthList(head, n);

        if (len == n) {
            return head.next;
        }

        return  head;
    }

    public static int lengthList(ListNode node, int n) {
        if (node == null) {
            return 0;
        }

        int len =  lengthList(node.next, n) + 1;

        if (len == n + 1) {
            node.next = node.next.next;
        }

        return len;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {

        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public void addList(int newval) {
            ListNode listNode = new ListNode(newval);
            if (this.next == null) {
                this.next = listNode;
            } else {
                this.next.addList(newval);
            }
        }

        public void print() {
            System.out.print(this.val);
            if (this.next != null) {
                System.out.print(" ");
                this.next.print();
            }
        }

    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = head;
        int last = length(head) - n;
        if (last == 0) {
            return head.next;
        }

        for (int i = 0; i < last - 1; i++) {
            pre = pre.next;
        }

        pre.next = pre.next.next;

        return head;
    }

    private static int length(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }

        return len;
    }

    private static int length1(ListNode head) {
        if (head == null) {
            return 0;
        }

        return length1(head.next) + 1;
    }
}

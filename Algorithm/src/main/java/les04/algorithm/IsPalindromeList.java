package les04.algorithm;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;
import sun.util.resources.cldr.ga.TimeZoneNames_ga;

import java.util.Stack;

/**
 * @author ether
 */
public class IsPalindromeList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {

    }


    // 栈
    public static boolean isPalindromeList1(Node head) {
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;

        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }

            head = head.next;
        }

        return true;
    }

    // 快慢指针
    public static boolean isPalindromeList2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node right = head.next;
        Node cur = head;

        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }

        Stack<Node> stack = new Stack<Node>();

        while (right != null) {
            stack.push(right);
            right = right.next;
        }

        while (!stack.isEmpty()) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

    // 反转后链表
    public static boolean isPalindromeList3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }

        n2 = n1.next;
        n1.next = null;
        while (n2 != null) {
            Node temp = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = temp;
        }
        // 此时n1成为右边链表的头结点，用n3记录该头结点
        Node n3 = n1;
        n2 = head;  // n2为左半部分
        boolean res = true;
        while (n1 != null && n2 != null) {
            if (n1.value!= n2.value) {
                res = false;
                break;
            }

            n1 = n1.next;
            n2 = n2.next;
        }

        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }

        return true;
    }

}

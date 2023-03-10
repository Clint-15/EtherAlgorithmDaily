package les04.algorithm;

import les08.algorithm.greedy.IPO;

/**
 * @author ether
 */
public class FindFirstIntersectNode {
    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }


    // 判断一个单链表是否有环，有则返回入环结点
    public static Node getloopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node n1 = head.next;    // slow
        Node n2 = head.next.next;    // fast
        while (n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
                return null;
            }

            n2 = n2.next.next;
            n1 = n1.next;
        }

        n2 = head;    // fast 回到头结点
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }

        return n1;
    }


    // 判断两个无环单链表是否相交，是则返回相交结点
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        Node cur1 = head1;
        Node cur2 = head2;

        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }

        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }

        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;

        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }

        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }


        return cur1;
    }

    // 两个有环单链表是否相交
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;

        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }

            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;

            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }

            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }

            return cur1;
        } else {
            cur1 = loop1.next;

            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;   // 返回loop1 和 loop2 都可以
                }

                cur1 = cur1.next;
            }

            return null;
        }
    }

    // 主函数
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        Node loop1 = getloopNode(head1);
        Node loop2 = getloopNode(head2);

        // 两个单链表都无环
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }

        // 两个单链表都有环
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }

        // 不可能一个有环一个无环
        return  null;
    }
}

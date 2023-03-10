package les04.algorithm;

import java.util.HashMap;

/**
 * @author ether
 */
public class CopyListWithRandom {
    public static class Node {
        public int value;
        public Node rand;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node copyListWithRandom1(Node head) {
        Node cur = head;
        HashMap<Node, Node> map = new HashMap<>();

        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }

        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }

        return map.get(head);
    }

    public static Node copyListWithRandom2(Node head) {
        if (head == null) {
            return null;
        }

        Node cur = head;
        Node nextNode = null;

        while (cur != null) {
            nextNode = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = nextNode;
            cur = nextNode;
        }

        cur = head;
        Node curCopy = null;

        while (cur != null) {
            nextNode = cur.next.next;
            curCopy = cur.next;

            if (cur.rand != null) {
                curCopy.rand = cur.rand.next;
            } else {
                curCopy.rand = null;
            }

            cur = nextNode;
        }

        Node res = head.next;
        cur = head;
        while (cur != null) {
            nextNode = cur.next.next;
            curCopy = cur.next;
            cur.next = nextNode;
            if (nextNode != null) {
                curCopy.next = nextNode.next;
            } else {
                curCopy.next = null;
            }

            cur = nextNode;
        }

        return res;
    }
}

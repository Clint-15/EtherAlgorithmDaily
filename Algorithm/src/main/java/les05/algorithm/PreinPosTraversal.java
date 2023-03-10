package les05.algorithm;

import java.sql.SQLOutput;
import java.util.Stack;

public class PreinPosTraversal {
    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 递归
    public static void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public static void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.println(head.value + " ");
        inOrderRecur(head.right);
    }

    public static void posOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.println(head.value + " ");
    }

    // 非递归

    // 先序遍历
    public static void preOrderUnrecur(Node head) {
        System.out.println("Pre-Order: ");
        if(head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head.value + " ");

                // 右结点不为空，先压右
                if (head.right != null) {
                    stack.push(head.right);
                }

                // 左结点不为空，再压左
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
         }
        System.out.println();
    }

    // 中序遍历
    public static void inOrderUnRecur(Node head) {
        System.out.println("In-Order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || head != null) {
                // 不停地把左边界依次进栈
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    // 弹出最左节点，打印最左节点，head走到右结点
                    head = stack.pop();
                    System.out.println(head.value + " ");
                    head = head.right;
                }
            }
        }

        System.out.println();
    }


    // 后续遍历
    public static void posOrderUnRecur1(Node head) {
        System.out.println("Pos-Order: ");
        if (head != null) {
            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
            s1.push(head);

            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);

                if (head.left != null) {
                    s1.push(head.left);
                }

                if (head.right != null) {
                    s1.push(head.right);
                }
            }

            while (!s2.isEmpty()) {
                System.out.println(s2.pop().value + " ");
            }
        }

        System.out.println();
    }
}

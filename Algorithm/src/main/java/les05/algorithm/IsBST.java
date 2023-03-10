package les05.algorithm;

/**
 * @author ether
 */
public class IsBST {
    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static class ReturnData {
        public int min;
        public int max;
        public boolean isBST;

        public ReturnData(int min, int max, boolean isBST) {
            this.min = min;
            this.max = max;
            this.isBST = isBST;
        }
    }

    public static boolean isBST(Node head) {
        return process(head).isBST;
    }

    public static ReturnData process(Node x) {
        if (x == null) {
            return null;
        }

        ReturnData leftData = process(x.left);
        ReturnData rightData = process(x.right);
        int min = x.value;
        int max = x.value;
        if (leftData != null) {
            min = Math.min(leftData.min, min);
        }

        if (rightData != null) {
            max = Math.max(rightData.max, max);
        }

        boolean isBST = true;
        if (leftData != null && (leftData.max >= x.value || !leftData.isBST)) {
            isBST = false;
        }

        if (rightData != null && (rightData.min <= x.value || !rightData.isBST)) {
            isBST = false;
        }

        return new ReturnData(min, max, isBST);
    }
}

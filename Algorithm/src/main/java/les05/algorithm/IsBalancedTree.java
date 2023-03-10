package les05.algorithm;

/**
 * @author ether
 */
public class IsBalancedTree {
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

    public static class ReturnType {
        public boolean isBalanced;
        public int height;
        public ReturnType(boolean isBal, int hei) {
            this.isBalanced = isBal;
            this.height = hei;
        }
    }

    public static boolean isBalanced(Node head) {
        return process(head).isBalanced;
    }

    public static ReturnType process(Node x) {
        if (x == null) {
            return new ReturnType(true, 0);
        }

        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);
        int height = Math.max(leftData.height, rightData.height);
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced && Math.abs(leftData.height - rightData.height) < 2;

        return new ReturnType(isBalanced, height);
    }
}

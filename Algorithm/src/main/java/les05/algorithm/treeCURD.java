package les05.algorithm;

public class treeCURD {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node (int data) {
            this.value = data;
        }
    }

    public static void main(String[] args) {
        PreinPosTraversal.Node head = new PreinPosTraversal.Node(5);
        head.left = new PreinPosTraversal.Node(3);
    }
}

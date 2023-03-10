package les08.algorithm.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ether
 */
public class IPO {
    public static class Node{
        // 利润
        public int p;

        // 花费
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }

    public static class MaxProfitComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }

    public static int findMaximizedCapital(int k, int w, int[] Profits, int [] Capital) {
        // 花费的小根堆（被锁池）
        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());

        // 利润的大根堆
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());

        // 将所有项目放入被锁池中，构成小根堆
        for (int i = 0; i < Profits.length; i++) {
            minCostQ.add(new Node(Profits[i], Capital[i]));
        }


        for (int i = 0; i < k; i++) {
            // 被锁池中能被解锁的项目进行解锁，解锁后放入利润的大根堆中（heap.peek(); 检索或获取第一个或栈顶元素，被检索的元素不会被删除）
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= w) {
                maxProfitQ.add(minCostQ.poll());
            }

            // 小根堆的根就不满足，那么所有的项目都不满足
            if (maxProfitQ.isEmpty()) {
                return w;
            }

            // 一次只能做一个项目，所以每次只需要读取大根堆中的根元素；
            w += maxProfitQ.poll().p;
        }

        return w;
    }
}

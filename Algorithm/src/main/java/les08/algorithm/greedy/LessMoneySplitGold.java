package les08.algorithm.greedy;

import java.util.PriorityQueue;

/**
 * @author ether
 */
public class LessMoneySplitGold {
    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int i = 0; i < arr.length; i++) {
            heap.add(arr[i]);
        }

        int sum = 0;    // 总代价
        int cur = 0;    // 结合小根堆中弹出的两个数字
        while (heap.size() > 1) {
            cur = heap.poll() + heap.poll();
            sum += cur;
            heap.add(cur);
        }

        return sum;
    }
}

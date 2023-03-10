package les03.sortArrayDistanceLessK;

import java.util.PriorityQueue;

public class SortArrayDistanceLessK {

    public static void sortArrayDistanceLessK(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index <= Math.min(arr.length - 1, k); index++) {
            heap.add(index);
        }

        int i = 0;
        for (; index < arr.length; index++) {
            heap.add(arr[index]);
            arr[i++] = heap.poll();
        }

        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }
}

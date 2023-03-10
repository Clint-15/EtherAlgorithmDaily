package les03.systemSolution;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapTest {
    public static void main(String[] args) {
        heapTest();
    }

    public static class AComp implements Comparator<Integer>{
        /**
         * 如果返回正数，第一个数比第二个数大，放在堆的下面；
         * 如果返回负数，第一个数比第二个数小，放在堆的上面；、
         * 如果为0，两数一样大。
         *
         * @param a the first object to be compared.
         * @param b the second object to be compared.
         * @return
         */
        public int compare(Integer a, Integer b) {
            return b - a;
        }
    }

    public static void heapTest() {
        PriorityQueue<Integer> heap = new PriorityQueue<>(new AComp());

        heap.add(4);
        heap.add(7);
        heap.add(2);
        heap.add(5);
        heap.add(4);
        heap.add(3);

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
    }
}

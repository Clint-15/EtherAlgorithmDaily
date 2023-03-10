package algorithm.sort;

/**
 * @author ether
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 9, 2, 5, 1, 4, 8 , 3, 7, 3};
        radixSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    /**
     * @param arr
     * @return res
     * @class maxbits 最大值有多少位
     */
    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }

        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }

        return res;
    }

    public static void radixSort(int[] arr, int l, int r, int digit) {
        final int radix = 10;
        int i = 0;
        int j = 0;
        int[] bucket = new int[r - l + 1];

        for (int d = 1; d <= digit; d++) {
            int[] count = new int[radix];

            for (i = l; i <= r; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }

            for (i = 1; i < radix; i++) {
                count[i] += count[i - 1];
            }

            for (i = r; i >= l; i--) {
                j = getDigit(arr[i], d);
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }

            for (i = l, j = 0; i <= r; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }

    public static int getDigit(int x, int d) {
        // Math.pow(a, b); 返回 a ^ b;
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }
}

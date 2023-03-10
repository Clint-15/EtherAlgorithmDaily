package algorithm.sort;


import java.util.Arrays;

/**
 * @author ether
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 15, 6, 3, 2, 7, 9, 5, 8, 20, 9, 10};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            int[] p = partition(arr, l, r);

            quickSort(arr, l, p[0] - 1);
            quickSort(arr, p[1] + 1, r);
        }
    }

    public static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;   // <区域右边界, arr[less + 1] == arr[r]
        int more = r;       // >区域左边界, arr[more] == arr[r]

        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }

        swap(arr, more, r);
        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr, int l, int r) {
        if (arr[l] != arr[r]) {
            arr[l] = arr[l] ^ arr[r];
            arr[r] = arr[l] ^ arr[r];
            arr[l] = arr[l] ^ arr[r];
        }
    }
}

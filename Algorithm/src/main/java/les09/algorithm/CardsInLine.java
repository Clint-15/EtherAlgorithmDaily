package les09.algorithm;

public class CardsInLine {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    public static int f(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }

        return Math.max(arr[l] + s(arr, l + 1, r), arr[r] + s(arr, l, r - 1));
    }

    public static int s(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }

        return Math.min(f(arr, l + 1, r), f(arr, l, r - 1));
    }
}

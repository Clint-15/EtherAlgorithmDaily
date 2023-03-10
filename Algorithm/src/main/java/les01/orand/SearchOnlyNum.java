package les01.orand;

public class SearchOnlyNum {
    public static void main(String[] args) {

    }

    public static void searchOnlyNum01(int[] nums) {
        int num = 0;

        for (int j : nums) {
            num ^= j;
        }

        System.out.println(num);
    }

    public static void searchOnlyNum02(int[] nums) {
        int num = 0;

        for (int j : nums) {
            num ^= j;
        }

        int rightOne = num & (~num + 1);    // 提取出二进制中最右边的1
        int oneOnly = 0;

        for (int j : nums) {
            if ((j & rightOne) == 0) {
                oneOnly ^= j;
            }
        }

        System.out.println(oneOnly + " " + (oneOnly ^ num));
    }
}


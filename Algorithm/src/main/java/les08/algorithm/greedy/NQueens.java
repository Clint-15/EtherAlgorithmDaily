package les08.algorithm.greedy;

/**
 * @author 樊金亮
 */
public class NQueens {
    public static void main(String[] args) {
        System.out.println(num1(8));
    }


    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }

        // record[i]: 第i行的皇后放在了第几列;
        int[] record = new int[n];
        return process1(0, record, n);
    }

    /**
     *
     * @param i 目前来到了第i行
     * @param record record[0 ... i-1]表示之前的行放置的皇后
     * @param n 整体共有多少行
     * @return 总共多少种合法的摆法
     */
    public static int process1(int i, int[] record, int n) {
        if (i == n) {   // 终止行：n-1行下面的一行
            return 1;
        }

        int res = 0;
        for (int j = 0; j < n; j++) {   // 当前在第i行，尝试第i行所有的列j（i从0开始）
            if (isValid(record, i, j)) {    // 判断当前第i个皇后能否放在第i行第j列（i从0开始）
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }

        return res;
    }

    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {   //遍历0到i-1行所有的皇后;
            // Math.abs(a): a的绝对值;
            if (j == record[k] || Math.abs(j - record[k]) == Math.abs(i - k)) {
                return false;
            }
        }

        return true;
    }
}

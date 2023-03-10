package les09.algorithm;

/**
 * @author ether
 */
public class ConverToLetterString {


    public static int process(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }

        if (str[i] == '0') {
            return 0;
        }

        if (str[i] == '1') {
            // i自己作为单独的部分，计算第i + 1开始有多少种方法
            int res = process(str, i + 1);

            if (i + 1 < str.length) {
                // i和i + 1两位作为单独的部分，计算第i + 2开始有多少种方法
                res += process(str, i + 2);
            }

            return res;
        }

        if (str[i] == '2') {
            int res = process(str, i + 1);

            if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                res += process(str, i + 2);
            }
        }

        // 字符是3-9的时候：
        return process(str, i + 1);
    }
}

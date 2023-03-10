package les08.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ether
 */
public class LowestLexicography {
    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);    // compareTO(); 返回字典序；前一个数字典序低，返回负数，后一个数字典序低，返回正数。
        }
    }

    public static String lowestLexicography(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new MyComparator());
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            res += strs[i];
        }

        return res;
    }
}

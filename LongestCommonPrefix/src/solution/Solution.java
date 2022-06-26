package solution;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;

/**
 * @author ether
 */
public class Solution {
    public static void main(String[] args) {

    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String res = strs[0];
        int i = 1;

        while (i < strs.length) {
            while (strs[i].indexOf(res) != 0) {
                res = res.substring(0, res.length() - 1);
            }

            i++;
        }

        return res;
    }
}

package solution;

/**
 * @author ether
 */
public class Solution {
    /**
     * 使用双指针：
     */
    public static boolean isPalindrome01(String s) {
        if (s.length() == 0) {
            return true;
        }

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // Character.isLetterOrDigit(s.charAt(left)) :是字符、数字，返回 true
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // Character.toLowerCase(s.charAt(left)) :将指定的字符转换为小写
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    /**
     * 使用正则匹配：
     */
    public static boolean isPalindrome02(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();

        // 先创建 actual 的对象，之后调用 StringBuffer(actual).reverse() ，将 actual 对象尽心反转；
        // 调用 toSring（） ：将对象以文本方式进行输出；
        String rev = new StringBuffer(actual).reverse().toString();

        return actual.equals(rev);
    }

    /**
     * 递归：
     */
    public static boolean isPalindrome03(String s) {
        return isPalindromeHelper(s, 0, s.length() - 1);
    }

    public static boolean isPalindromeHelper(String s, int left, int right) {
        if (left >= right) {
            return true;
        }

        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
            left++;
        }

        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
            right--;
        }

        return Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right))
                && isPalindromeHelper(s, ++left, --right);
    }
}

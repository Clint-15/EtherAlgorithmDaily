package solution;

/**
 * @author ether
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }

    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String s1 = countAndSay(n - 1);
        StringBuilder result = new StringBuilder();
        char local = s1.charAt(0);
        int count = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == local) {
                count++;
            } else {
                result.append(count);
                result.append(local);
                count = 1;
                local = s1.charAt(i);
            }
        }

        result.append(count);
        result.append(local);

        return result.toString();
    }
}

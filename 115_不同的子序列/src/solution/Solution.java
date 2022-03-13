package solution;

/**
 * @author ether
 */
public class Solution {
    public int numDistinct(String s, String t) {
        int sLength = s.length() + 1;
        int tLength = t.length() + 1;

        if (sLength < tLength) {
            return 0;
        }

        int[][] dome = new int[tLength][sLength];

        for (int j = 0; j < sLength; j++) {
            dome[0][j] = 1;
        }

        for (int i = 1; i <= tLength; i++) {
            for (int j = 1; j <= sLength; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dome[i][j] = dome[i - 1][j - 1] + dome[i][j - 1];
                } else {
                    dome[i][j] = dome [i][j - 1];
                }
            }
        }

        return dome[tLength - 1][sLength - 1];
    }
}

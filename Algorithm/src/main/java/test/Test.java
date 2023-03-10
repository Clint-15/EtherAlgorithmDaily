package test;

import java.util.PriorityQueue;

public class Test {
    public static void main(String[] args) {
        int[] n = new int[] {6,6,9};
        System.out.println(longestWPI(n));
    }

    public static int longestWPI(int[] hours) {
        int ans = 0;

        for (int right = 0, left = 0; right < hours.length; right++) {
            int con = 0;
            int houMax = 0;
            int houMin = 0;
            while (left <= right) {
                if (hours[left] > 8) {
                    houMax++;
                } else {
                    houMin++;
                }

                if (houMax > houMin) {
                    con++;
                }
                ans = Math.max(ans, con);
                left++;
            }
        }

        return ans;
    }

}

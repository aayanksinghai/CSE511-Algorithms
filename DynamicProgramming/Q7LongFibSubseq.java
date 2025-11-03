
// AAYANK SINGHAI MT2025001
import java.util.*;

class Solution {
    public int lenLongestFibSubseq(int[] a) {
        int n = a.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(a[i], i);
        }

        int[][] dp = new int[n][n];
        int max = 0;

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                int prev = a[j] - a[i];
                int k = map.getOrDefault(prev, -1);

                if (k >= 0 && k < i) {
                    dp[i][j] = dp[k][i] + 1;
                } else {
                    dp[i][j] = 2;
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        return max > 2 ? max : 0;
    }
}
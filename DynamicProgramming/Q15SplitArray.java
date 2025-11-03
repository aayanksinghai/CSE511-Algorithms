
// AAYANK SINGHAI MT2025001
import java.util.Arrays;

class Solution {
    public int splitArray(int[] a, int k) {
        int n = a.length;

        long[] pre = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + a[i];
        }

        long[][] dp = new long[n + 1][k + 1];
        for (long[] row : dp) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        dp[0][0] = 0;

        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                for (int p = 0; p < i; p++) {
                    long s = pre[i] - pre[p];
                    long val = Math.max(dp[p][j - 1], s);
                    dp[i][j] = Math.min(dp[i][j], val);
                }
            }
        }

        return (int) dp[n][k];
    }
}
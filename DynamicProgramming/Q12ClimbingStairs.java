
// AAYANK SINGHAI MT2025001
import java.util.*;

class Solution {
    public int climbStairs(int n, int[] costs) {

        // DP Soln
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 3; j++) {
                int p = i - j;

                if (p >= 0) {
                    long jc = (long) costs[i - 1] + (long) j * j;
                    long total = dp[p] + jc;
                    dp[i] = Math.min(dp[i], total);
                }
            }
        }

        return (int) dp[n];
    }
}
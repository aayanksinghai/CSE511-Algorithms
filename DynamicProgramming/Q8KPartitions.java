// AAYANK SINGHAI MT2025001
class Solution {
    public int maxSumAfterPartitioning(int[] a, int k) {
        int n = a.length;
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int cMax = 0;

            for (int j = 1; j <= k && i - j >= 0; j++) {
                cMax = Math.max(cMax, a[i - j]);
                dp[i] = Math.max(dp[i], dp[i - j] + cMax * j);
            }
        }

        return dp[n];
    }
}
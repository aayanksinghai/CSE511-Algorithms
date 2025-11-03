// AAYANK SINGHAI MT2025001
class Solution {
    public int minSteps(int n) {
        int[] dp = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    dp[i] = Math.min(dp[j] + i / j, dp[i / j] + j);
                    break;
                }
            }
        }
        return dp[n];
    }
}
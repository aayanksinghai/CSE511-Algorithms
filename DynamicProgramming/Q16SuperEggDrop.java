// AAYANK SINGHAI MT2025001
class Solution {
    public int superEggDrop(int k, int n) {
        int[] dp = new int[k + 1];
        int m = 0;

        while (dp[k] < n) {
            m++;
            for (int j = k; j >= 1; j--) {
                dp[j] = dp[j] + dp[j - 1] + 1;
            }
        }
        return m;
    }
}
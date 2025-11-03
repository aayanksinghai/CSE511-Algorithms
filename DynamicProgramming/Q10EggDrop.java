// AAYANK SINGHAI MT2025001
class Solution {
    public int twoEggDrop(int n) {
        int[] dp = new int[n + 1];
        int m = 0;

        while (dp[m] < n) {
            m++;
            if (m > n)
                break;

            dp[m] = dp[m - 1] + m;
        }
        return m;

    }
}
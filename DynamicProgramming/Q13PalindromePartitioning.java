// AAYANK SINGHAI MT2025001
class Solution {
    public int minCut(String s) {
        int n = s.length();
        if (n == 0)
            return 0;

        boolean[][] p = new boolean[n][n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = i;
            for (int j = 0; j <= i; j++) {

                if (s.charAt(i) == s.charAt(j) && (i - j < 2 || p[j + 1][i - 1])) {
                    p[j][i] = true;

                    if (j == 0) {
                        dp[i] = 0;
                    } else {
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }
        return dp[n - 1];
    }
}
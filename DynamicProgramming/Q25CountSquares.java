// AAYANK SINGHAI MT2025001
class Q25CountSquares {
    public int countSquares(int[][] a) {
        int m = a.length;
        int n = a[0].length;

        int[] dp = new int[n];
        int sum = 0;

        for (int j = 0; j < n; j++) {
            if (a[0][j] == 1) {
                dp[j] = 1;
                sum++;
            }
        }

        for (int i = 1; i < m; i++) {
            int diag_up = dp[0];

            if (a[i][0] == 1) {
                dp[0] = 1;
                sum++;
            } else {
                dp[0] = 0;
            }

            for (int j = 1; j < n; j++) {
                int temp = dp[j];

                if (a[i][j] == 1) {
                    dp[j] = 1 + Math.min(diag_up, Math.min(dp[j], dp[j - 1]));
                    sum += dp[j];
                } else {
                    dp[j] = 0;
                }

                diag_up = temp;
            }
        }
        return sum;
    }
}
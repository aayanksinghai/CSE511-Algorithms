// AAYANK SINGHAI MT2025001
class Solution {
    int m, n;
    Integer[][] dp;
    int[] b;

    public int maxStudents(char[][] s) {
        m = s.length;
        n = s[0].length;
        dp = new Integer[m][1 << n];
        b = new int[m];

        for (int i = 0; i < m; i++) {
            int mask = 0;
            for (int j = 0; j < n; j++) {
                if (s[i][j] == '#') {
                    mask |= (1 << j);
                }
            }
            b[i] = mask;
        }

        return solve(0, 0);
    }

    int solve(int r, int p) {
        if (r == m) {
            return 0;
        }
        if (dp[r][p] != null) {
            return dp[r][p];
        }

        int max = 0;
        for (int c = 0; c < (1 << n); c++) {

            if ((c & b[r]) != 0)
                continue;
            if ((c & (c >> 1)) != 0)
                continue;
            if ((c & (p >> 1)) != 0)
                continue;
            if ((c & (p << 1)) != 0)
                continue;

            max = Math.max(max, Integer.bitCount(c) + solve(r + 1, c));
        }

        return dp[r][p] = max;
    }
}
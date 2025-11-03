
// AAYANK SINGHAI MT2025001
import java.util.*;

class Solution {
    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        int[] pre = new int[n];
        for (int[] r : relations) {
            pre[r[1] - 1] |= (1 << (r[0] - 1));
        }

        int[] dp = new int[1 << n];
        Arrays.fill(dp, n + 1);
        dp[0] = 0;

        for (int mask = 0; mask < (1 << n); mask++) {
            if (dp[mask] == n + 1)
                continue;

            int avail = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) {
                    if ((mask & pre[i]) == pre[i]) {
                        avail |= (1 << i);
                    }
                }
            }

            if (Integer.bitCount(avail) <= k) {
                int next = mask | avail;
                dp[next] = Math.min(dp[next], dp[mask] + 1);
            } else {
                for (int sub = avail; sub > 0; sub = (sub - 1) & avail) {
                    if (Integer.bitCount(sub) == k) {
                        int next = mask | sub;
                        dp[next] = Math.min(dp[next], dp[mask] + 1);
                    }
                }
            }
        }

        return dp[(1 << n) - 1];
    }
}
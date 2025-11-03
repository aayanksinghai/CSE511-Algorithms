class ClimbingStairs {
    public int climbStairs(int n, int[] costs) {
        long[] dp = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            long min = (long) 1e10;

            for (int start = i + 1; start <= Math.min(i + 3, costs.length); start++) {
                long d = start - i;
                long val = (long) costs[start - 1] + d * d;
                min = Math.min(min, val + dp[start]);
            }
            dp[i] =  min;
        }
        return (int) dp[0];
    }

    long solve(int i, int[] cost) {
        if (i == cost.length)
            return 0;
        long min = (long) 1e10;

        for (int start = i + 1; start <= Math.min(i + 3, cost.length); start++) {
            long d = start - i;
            long val = (long) cost[start - 1] + d * d;
            min = Math.min(min, val + solve(start, cost));
        }
        return min;
    }
}

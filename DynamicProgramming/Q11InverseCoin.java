
// AAYANK SINGHAI MT2025001
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public List<Integer> findCoins(int[] numWays) {
        List<Integer> coins = new ArrayList<>();
        int n = numWays.length;

        for (int i = 0; i < n; i++) {
            int amount = i + 1;
            int[][] dp = new int[coins.size() + 1][amount + 1];
            for (int[] row : dp) {
                Arrays.fill(row, -1);
            }

            int waysWithout = solve(0, amount, coins, dp);

            int targetWays = numWays[i];

            if (targetWays == waysWithout + 1) {
                coins.add(amount);
            } else if (targetWays == waysWithout) {
            } else {
                return new ArrayList<>();
            }
        }
        return coins;
    }

    int solve(int idx, int amt, List<Integer> coins, int[][] dp) {
        if (amt == 0) {
            return 1;
        }
        if (idx == coins.size() || amt < 0) {
            return 0;
        }

        if (dp[idx][amt] != -1) {
            return dp[idx][amt];
        }
        int take = solve(idx, amt - coins.get(idx), coins, dp);
        int nottake = solve(idx + 1, amt, coins, dp);

        return dp[idx][amt] = take + nottake;
    }
}
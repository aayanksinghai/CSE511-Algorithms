
// AAYANK SINGHAI MT2025001
import java.util.HashMap;

class Solution {
    HashMap<Integer, Boolean> memo;
    int max;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        this.max = maxChoosableInteger;

        int sum = (max * (max + 1)) / 2;
        if (sum < desiredTotal) {
            return false;
        }

        if (desiredTotal <= 0) {
            return true;
        }

        this.memo = new HashMap<>();

        return solve(0, desiredTotal);
    }

    private boolean solve(int mask, int remaining) {
        if (remaining <= 0) {
            return false;
        }

        int key = (remaining << 20) | mask;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        for (int i = 1; i <= this.max; i++) {
            int bit = 1 << (i - 1);

            if ((mask & bit) == 0) {
                if (i >= remaining) {
                    memo.put(key, true);
                    return true;
                }

                if (!solve(mask | bit, remaining - i)) {
                    memo.put(key, true);
                    return true;
                }
            }
        }

        memo.put(key, false);
        return false;
    }
}
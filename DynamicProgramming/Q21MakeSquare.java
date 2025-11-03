
// AAYANK SINGHAI MT2025001
import java.util.Arrays;
import java.util.Collections;

class Solution {
    public boolean makesquare(int[] a) {
        int n = a.length;
        if (n < 4)
            return false;

        int sum = 0;
        for (int x : a)
            sum += x;
        if (sum % 4 != 0)
            return false;

        int t = sum / 4;

        Integer[] sticks = new Integer[n];
        for (int i = 0; i < n; i++)
            sticks[i] = a[i];
        Arrays.sort(sticks, Collections.reverseOrder());

        int[] sides = new int[4];
        return solve(0, sticks, sides, t);
    }

    private boolean solve(int i, Integer[] a, int[] sides, int t) {
        if (i == a.length) {
            return true;
        }

        for (int j = 0; j < 4; j++) {
            if (sides[j] + a[i] <= t) {
                sides[j] += a[i];
                if (solve(i + 1, a, sides, t)) {
                    return true;
                }
                sides[j] -= a[i];
            }
        }

        return false;
    }
}
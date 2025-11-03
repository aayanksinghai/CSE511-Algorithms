
// AAYANK SINGHAI MT2025001
import java.util.*;

class Solution {
    public int longestMountain(int[] a) {
        int n = a.length;
        if (n < 3)
            return 0;

        int[] up = new int[n];
        Arrays.fill(up, 1);
        for (int i = 1; i < n; i++) {
            if (a[i] > a[i - 1]) {
                up[i] = 1 + up[i - 1];
            }
        }

        int[] down = new int[n];
        Arrays.fill(down, 1);
        for (int i = n - 2; i >= 0; i--) {
            if (a[i] > a[i + 1]) {
                down[i] = 1 + down[i + 1];
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            if (up[i] > 1 && down[i] > 1) {
                max = Math.max(max, up[i] + down[i] - 1);
            }
        }
        return max;
    }
}
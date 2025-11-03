
// AAYANK SINGHAI MT2025001
import java.util.*;

class Solution {
    public boolean canCross(int[] a) {
        int n = a.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int x : a) {
            map.put(x, new HashSet<>());
        }

        map.get(a[0]).add(0);

        for (int i = 0; i < n; i++) {
            int s = a[i];
            Set<Integer> jumps = map.get(s);

            for (int k : jumps) {
                for (int j = k - 1; j <= k + 1; j++) {
                    if (j > 0 && map.containsKey(s + j)) {
                        map.get(s + j).add(j);
                    }
                }
            }
        }

        return !map.get(a[n - 1]).isEmpty();
    }
}

// AAYANK SINGHAI MT2025001
import java.util.*;
import java.util.stream.*;

class Solution {
    public boolean canPartitionKSubsets(int[] a, int k) {
        int sum = IntStream.of(a).sum();
        if (sum % k != 0) {
            return false;
        }

        int t = sum / k;

        a = Arrays.stream(a)
                .boxed()
                .sorted((x, y) -> Integer.compare(y, x))
                .mapToInt(Integer::intValue)
                .toArray();

        if (a[0] > t) {
            return false;
        }

        int[] buckets = new int[k];
        return solve(0, a, buckets, t);
    }

    private boolean solve(int i, int[] a, int[] buckets, int t) {
        if (i == a.length) {
            for (int b : buckets) {
                if (b != t)
                    return false;
            }
            return true;
        }

        for (int j = 0; j < buckets.length; j++) {
            if (buckets[j] + a[i] <= t) {
                buckets[j] += a[i];
                if (solve(i + 1, a, buckets, t)) {
                    return true;
                }
                buckets[j] -= a[i];
            }
            if (buckets[j] == 0)
                break;
        }

        return false;
    }
}
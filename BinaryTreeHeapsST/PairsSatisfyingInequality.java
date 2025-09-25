/*
 * You are given two 0-indexed integer arrays nums1 and nums2, each of size n, and an integer diff. Find the number of pairs (i, j) such that:

0 <= i < j <= n - 1 and
nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
Return the number of pairs that satisfy the conditions.
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * Link: https://leetcode.com/problems/number-of-pairs-satisfying-inequality/description/?envType=problem-list-v2&envId=binary-indexed-tree 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class PairsSatisfyingInequality {
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        int n = nums1.length;
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = nums1[i] - nums2[i];
        }

        // Corrdinate compression
        Set<Long> valueSet = new HashSet<>();
        for (int val : d) {
            valueSet.add((long) val);
            valueSet.add((long) val + diff);
        }

        List<Long> sortedUniqueValues = new ArrayList<>(valueSet);
        Collections.sort(sortedUniqueValues);

        Map<Long, Integer> rankMap = new HashMap<>();
        for (int i = 0; i < sortedUniqueValues.size(); i++) {
            rankMap.put(sortedUniqueValues.get(i), i);
        }

        BIT bit = new BIT(sortedUniqueValues.size());
        long pairCount = 0;

        for (int j = 0; j < n; j++) {
            long targetValue = (long) d[j] + diff;
            int targetRank = findRank(sortedUniqueValues, targetValue);

            if (targetRank != -1) {
                pairCount += bit.query(targetRank);
            }

            int currentRank = rankMap.get((long) d[j]);
            bit.update(currentRank, 1);
        }

        return pairCount;
    }

    private int findRank(List<Long> sortedValues, long target) {
        int low = 0;
        int high = sortedValues.size() - 1;
        int rank = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sortedValues.get(mid) <= target) {
                rank = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return rank;
    }
}

class BIT {
    private int[] tree;
    private int size;

    public BIT(int size) {
        this.size = size;
        this.tree = new int[size + 1];
    }

    public void update(int index, int delta) {
        index++;
        while (index <= size) {
            tree[index] += delta;
            index += index & (-index);
        }
    }

    public int query(int index) {
        index++;
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= index & (-index);
        }
        return sum;
    }
}

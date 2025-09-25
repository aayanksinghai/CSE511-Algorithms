/*
 * Given an integer array nums and two integers lower and upper, return the number of range sums that lie in [lower, upper] inclusive.

Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j inclusive, where i <= j.
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * Link: https://leetcode.com/problems/count-of-range-sum/description/?envType=problem-list-v2&envId=segment-tree
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountOfRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // prefix sum array
        long[] prefixSums = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }

        // Cordinate compression
        Set<Long> valueSet = new HashSet<>();
        for (long p : prefixSums) {
            valueSet.add(p);
            valueSet.add(p - lower);
            valueSet.add(p - upper);
        }

        long[] sortedValues = new long[valueSet.size()];
        int idx = 0;
        for (long val : valueSet) {
            sortedValues[idx++] = val;
        }
        Arrays.sort(sortedValues);

        Map<Long, Integer> rankMap = new HashMap<>();
        for (int i = 0; i < sortedValues.length; i++) {
            rankMap.put(sortedValues[i], i);
        }

        // using BIT for count valid range
        int count = 0;
        BIT bit = new BIT(sortedValues.length);

        for (long p : prefixSums) {
            int rankLowerBound = rankMap.get(p - lower);
            int rankUpperBound = rankMap.get(p - upper);
            count += bit.query(rankLowerBound + 1) - bit.query(rankUpperBound);
            bit.update(rankMap.get(p) + 1, 1);
        }

        return count;
    }
}

class BIT {
    private int[] tree;
    private int size;

    public BIT(int n) {
        this.size = n;
        this.tree = new int[n + 1]; // Use 1-based indexing
    }

    public void update(int i, int delta) {
        while (i <= size) {
            tree[i] += delta;
            i += i & (-i);
        }
    }

    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= i & (-i);
        }
        return sum;
    }
}
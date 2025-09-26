/*
 * Given an integer array nums, return the number of reverse pairs in the array.

A reverse pair is a pair (i, j) where:

0 <= i < j < nums.length and
nums[i] > 2 * nums[j].
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * Link: https://leetcode.com/problems/reverse-pairs/description/?envType=problem-list-v2&envId=binary-indexed-tree
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ReversePairs {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        Set<Long> uniqueValuesSet = new HashSet<>();
        for (int num : nums) {
            uniqueValuesSet.add((long) num);
            uniqueValuesSet.add(2L * num);
        }

        Long[] sortedUniqueValues = uniqueValuesSet.toArray(new Long[0]);
        Arrays.sort(sortedUniqueValues);

        Map<Long, Integer> rankMap = new HashMap<>();
        for (int i = 0; i < sortedUniqueValues.length; i++) {
            rankMap.put(sortedUniqueValues[i], i + 1);
        }

        BIT bit = new BIT(rankMap.size());
        int count = 0;
        int n = nums.length;

        for (int j = 0; j < n; j++) {
            long target = 2L * nums[j];
            int targetRank = rankMap.get(target);
            long countLessEqual = bit.query(targetRank);
            count += (j - countLessEqual);

            int currentRank = rankMap.get((long) nums[j]);
            bit.update(currentRank, 1);
        }

        return count;
    }
}

class BIT {
    private int[] tree;
    private int size;

    public BIT(int n) {
        this.size = n + 1;
        this.tree = new int[this.size];
    }

    public void update(int i, int delta) {
        while (i < size) {
            tree[i] += delta;
            i += i & (-i);
        }
    }

    public long query(int i) {
        long sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= i & (-i);
        }
        return sum;
    }
}

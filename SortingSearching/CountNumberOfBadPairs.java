/*
 * You are given a 0-indexed integer array nums. A pair of indices (i, j) is a bad pair if i < j and j - i != nums[j] - nums[i].

Return the total number of bad pairs in nums.
 * 
 * Link: https://leetcode.com/problems/count-number-of-bad-pairs/description/?envType=problem-list-v2&envId=hash-table
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 */

import java.util.HashMap;

public class CountNumberOfBadPairs {
    public long countBadPairs(int[] nums) {
        // Brute Force approach TC: O(N^2)
        /*
         * int n = nums.length;
         * long noOfBadPairs = 0;
         * for(int i = 0; i < nums.length; i++){
         * for(int j = i+1; j < nums.length; j++){
         * if((j-i) != (nums[j] - nums[i])){
         * noOfBadPairs++;
         * }
         * }
         * }
         * return noOfBadPairs;
         */

        // Optimal Approach TC: O(N)
        int n = nums.length;
        long totalPairs = (long) n * (n - 1) / 2;

        long goodPairs = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int diff = i - nums[i];
            goodPairs += map.getOrDefault(diff, 0);
            map.put(diff, map.getOrDefault(diff, 0) + 1);
        }

        return totalPairs - goodPairs;

    }
}

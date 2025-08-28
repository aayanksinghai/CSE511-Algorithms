
/*
 * Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.

You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
 * Link: https://leetcode.com/problems/first-missing-positive/description/?envType=problem-list-v2&envId=hash-table
 * Author: AAYANK SINGHAI (MT2025001)
 * 
 */

public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        // Brute Force Approach with additional DS
        // HashMap<Integer, Integer> map = new HashMap<>(); //17ms
        /*
         * HashSet<Integer> hashSet = new HashSet<>(); //13ms
         * for(int i = 0; i < nums.length; i++){
         * //map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
         * hashSet.add(nums[i]);
         * 
         * 
         * int i = 1;
         * while(true){
         * if(!hashSet.contains(i)) break;
         * i++;
         * }
         * 
         * return i;
         */

        // Optimal Approach using TC: O(N) SC: O(N)
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            if (nums[i] > len || nums[i] <= 0) {
                nums[i] = len + 1;
            }
        }

        for (int j = 0; j < len; j++) {
            int element = Math.abs(nums[j]);

            if (element <= len) {
                int indexValue = element - 1;
                if (indexValue >= 0) {
                    nums[indexValue] = -Math.abs(nums[indexValue]);
                }
            }
        }

        for (int j = 0; j < len; j++) {
            if (nums[j] > 0) {
                return j + 1;
            }
        }
        return len + 1;
    }
}

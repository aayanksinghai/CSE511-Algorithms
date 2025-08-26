/*
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.

Link: https://leetcode.com/problems/single-element-in-a-sorted-array/description/?envType=problem-list-v2&envId=binary-search

Author: AAYANK SINGHAI (MT2025001)
 */


public class SingleElementInSortedArray {
    public int singleNonDuplicate(int[] nums) {
        // Brute Force  TC: O(N)
        /*
        int len = nums.length;
        if(len == 1) return nums[0];
        
        for(int i = 0; i < len; i++){
            if(i == 0) //edge case 1 first element
            {
                if(nums[i] != nums[i+1]) return nums[i];
            }else if(i == (len-1)){
                if(nums[i] != nums[i-1]) return nums[i];
            }else{
                if(nums[i] != nums[i+1] && (nums[i] != nums[i-1])) return nums[i];
            }
        }
        return -1;
        */


        // Optimal Approach using Binary Search 
        int n = nums.length; // Size of the array.

        // Edge cases:
        if (n == 1) return nums[0];
        if (nums[0] != nums[1]) return nums[0];
        if (nums[n - 1] != nums[n - 2]) return nums[n - 1];

        int low = 1, high = n - 2;
        while (low <= high) {
            int mid = (low + high) / 2;

            // If nums[mid] is the single element:
            if (nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1]) {
                return nums[mid];
            }

            //in the left part:
            if ((mid % 2 == 1 && nums[mid] == nums[mid - 1])
                || (mid % 2 == 0 && nums[mid] == nums[mid + 1])) {
                // Eliminate the left half:
                low = mid + 1;
            }
            // right part:
            else {
                // Eliminate the right half:
                high = mid - 1;
            }
        }

        // Dummy return statement:
        return -1;  
    }
}

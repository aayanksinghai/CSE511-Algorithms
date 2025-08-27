/*
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.

Return the sum of the three integers.

You may assume that each input would have exactly one solution.

Link: https://leetcode.com/problems/3sum-closest/description/?envType=problem-list-v2&envId=two-pointers

Author: AAYANK SINGHAI (MT2025001)
 */

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;

        int closestSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n; i++) {
            int left = i + 1;
            int right = n - 1;

            while(left < right){
                int currentSum = nums[i] + nums[left] + nums[right];

                if(Math.abs(currentSum - target) < Math.abs(closestSum - target)){
                    closestSum = currentSum;
                }

                if(currentSum < target) left++;
                else if(currentSum > target) right--;
                else{
                    return target; //exact element found
                }
            }
        }
        return closestSum;
    }
}
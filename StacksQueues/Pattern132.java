/*
 * Given an array of n integers nums, a 132 pattern is a subsequence of 
 * three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
 * Return true if there is a 132 pattern in nums, otherwise, return false.
 * 
 * Link: https://leetcode.com/problems/132-pattern/description/?envType=problem-list-v2&envId=stack
 * Author: AAYANK SINGHAI (MT2025001)
 * 
 */

import java.util.Stack;

public class Pattern132 {
    public boolean find132pattern(int[] nums) {
        // Brute Force Approach O(N^2)
        /*        int length = nums.length;

        int minElement = nums[0]; //Initialising and assuming this as "1"
        for(int i = 1; i < length; i++){  //This is our "3"
            for(int k = i+1; k < length; k++){
                if((nums[i] > nums[k]) && (nums[k] > minElement)){
                    return true;
                }
            }
            minElement = Math.min(minElement, nums[i]);
        }

        return false;
         */

        //  Optimal Approach
        int length = nums.length;
        if(length < 3) return false;
        Stack<Integer> st = new Stack<>();
        int minElement = Integer.MIN_VALUE;

        for(int k = length-1; k >= 0; k--){
            if(nums[k] < minElement){
                return true;
            }

            while(!st.isEmpty() && nums[k] > st.peek()){
                minElement = st.pop();
            }
            st.push(nums[k]);
        }

        return false;
    }    
}

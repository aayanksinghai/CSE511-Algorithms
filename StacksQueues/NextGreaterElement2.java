/*
 * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), 
 * return the next greater number for every element in nums.

The next greater number of a number x is the first greater number to its traversing-order next in the array, 
which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
 * 
 * Link: https://leetcode.com/problems/next-greater-element-ii/description/?envType=problem-list-v2&envId=stack
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 */


import java.util.Stack;

class Solution {
    public int[] nextGreaterElements(int[] arr) {
        // Brute Force Approach 
        /*
        int[] ans = new int[arr.length];
        if(arr.length == 1) {
            ans[0] = -1; 
            return ans;
        }

        for(int i = 0; i < arr.length; i++){
            for(int j = i+1; j < 2*arr.length; j++){
                if(j == arr.length){
                    j = 0;
                }
                if(j == i){
                    break;
                }
                if(arr[j] > arr[i]){
                    ans[i] = arr[j];
                    break;
                }
                ans[i] = -1;
            }
        }

        return ans;
        */

        // Optimal Approach Using Stack
        Stack<Integer> st = new Stack<>();        
        int len = arr.length;
        int[] ans = new int[len];

        for(int i = 2*len-1; i >= 0; i--){
            while(!st.isEmpty() && st.peek() <= arr[i%len]){
                st.pop();
            }
            if(i < len){
                if(st.isEmpty()){
                    ans[i] = -1;
                }else{
                    ans[i] = st.peek();
                }
            }
            st.push(arr[i%len]);
        }
        return ans;    
    }
}
/*
You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
You can only see the k numbers in the window. Each time the sliding window moves right by one position.
Return the max sliding window.

Link: https://leetcode.com/problems/sliding-window-maximum/description/?envType=problem-list-v2&envId=queue
 *  Author: AAYANK SINGHAI (MT2025001)
 */

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // Brute Force
        /* 
        int size = nums.length-k+1;
        int[] ans = new int[size];

        for(int i = 0; i < size; i++){
            int maxAns = Integer.MIN_VALUE;
            for(int j = i; j < i+k; j++){
                if(maxAns < nums[j]){
                    maxAns = nums[j];
                }
            }
            ans[i] = maxAns;
        }

        return ans;
        */

        // Optimal Approach
        int n = nums.length;

        int[] ans = new int[n-k+1];
        int ansIndex = 0;

        Deque<Integer> dq = new LinkedList<>();

        for(int i = 0; i < n; i++){
            if(!dq.isEmpty() && dq.peekFirst() <= (i-k)){ //updating deque to maintain window k
                dq.pollFirst();
            }

            while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]){ //maintaning monotonic order
                dq.pollLast();
            }

            dq.offerLast(i); //adding current element index to deque

            if(i >= (k-1)){
                ans[ansIndex++] = nums[dq.peekFirst()]; //max element from first window possible
            }
        }
        return ans;   
    }
}
/*
 * You are given an integer array nums and two integers minK and maxK.
A fixed-bound subarray of nums is a subarray that satisfies the following conditions:
The minimum value in the subarray is equal to minK.
The maximum value in the subarray is equal to maxK.
Return the number of fixed-bound subarrays.
A subarray is a contiguous part of an array.
 * 
 * Link: https://leetcode.com/problems/count-subarrays-with-fixed-bounds/description/?envType=problem-list-v2&envId=queue
 * Author: AAYANK SINGHAI (MT2025001)
 */

public class CountSubArraysWithFixedBounds {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long subArrayCnt = 0;
        int minKPos = -1;
        int maxKPos = -1;
        int leftBound = -1;

        for (int i = 0; i < nums.length; i++) {
            int currentNo = nums[i];
            if (currentNo < minK || currentNo > maxK) {
                leftBound = i;
                minKPos = -1;
                maxKPos = -1;
                continue;
            }

            if (currentNo == minK) {
                minKPos = i;
            }
            if (currentNo == maxK) {
                maxKPos = i;
            }

            if (minKPos != -1 && maxKPos != -1) {
                int validStart = Math.min(minKPos, maxKPos);
                subArrayCnt += (validStart - leftBound);
            }
        }

        return subArrayCnt;
    }
}
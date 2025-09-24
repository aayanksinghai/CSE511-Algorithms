/*
 * You are given a 0-indexed 2D integer array nums. Initially, your score is 0. Perform the following operations until the matrix becomes empty:

From each row in the matrix, select the largest number and remove it. In the case of a tie, it does not matter which number is chosen.
Identify the highest number amongst all those removed in step 1. Add that number to your score.
Return the final score.
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * Link: https://leetcode.com/problems/sum-in-a-matrix/description/?envType=problem-list-v2&envId=heap-priority-queue
 */

import java.util.Arrays;

public class SumInAMatrix {
    public int matrixSum(int[][] nums) {

        int n = nums.length; // rows
        int m = nums[0].length; // column

        for (int i = 0; i < n; i++) {
            Arrays.sort(nums[i]);
        }

        int sum = 0;
        for (int i = 0; i < m; i++) {
            int maxEle = Integer.MIN_VALUE;
            for (int k = 0; k < n; k++) {
                maxEle = Math.max(maxEle, nums[k][i]);
            }
            sum += maxEle;
        }

        return sum;
    }
}

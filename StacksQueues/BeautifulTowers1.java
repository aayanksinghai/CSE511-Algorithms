/*
 * You are given an array heights of n integers representing the number of bricks in n consecutive towers. 
 * Your task is to remove some bricks to form a mountain-shaped tower arrangement. In this arrangement, 
 * the tower heights are non-decreasing, reaching a maximum peak value with one or multiple consecutive towers and then non-increasing.
Return the maximum possible sum of heights of a mountain-shaped tower arrangement.
 * 
 * Link: https://leetcode.com/problems/beautiful-towers-i/description/?envType=problem-list-v2&envId=stack
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 */

public class BeautifulTowers1 {
     public long maximumSumOfHeights(int[] maxHeights) {
        int noOfTowers = maxHeights.length; 
        long overallMaxSum = 0;

        // Iterating array to find peak towers
        for (int peakIndex = 0; peakIndex < noOfTowers; peakIndex++) {

            long[] currentTHeights = new long[noOfTowers];

            // The peak tower is set to its maximum possible value.
            currentTHeights[peakIndex] = maxHeights[peakIndex]; 

            // Build the left slope (non-decreasing part).
            long prevHeightOnSlope = currentTHeights[peakIndex];
            for (int i = peakIndex - 1; i >= 0; i--) {
                long newHeight = Math.min(maxHeights[i], prevHeightOnSlope);
                currentTHeights[i] = newHeight;
                prevHeightOnSlope = newHeight;
            }

            // Build the right slope (non-increasing part).
            prevHeightOnSlope = currentTHeights[peakIndex];
            for (int i = peakIndex + 1; i < noOfTowers; i++) {
                long newHeight = Math.min(maxHeights[i], prevHeightOnSlope);
                currentTHeights[i] = newHeight;
                prevHeightOnSlope = newHeight;
            }
            
            // Calculate the sum for this configuration.
            long currentSum = 0;
            for (long height : currentTHeights) {
                currentSum += height;
            }
            
            // Update the overall maximum sum found so far.
            overallMaxSum = Math.max(overallMaxSum, currentSum);
        }

        return overallMaxSum;
    }    
}

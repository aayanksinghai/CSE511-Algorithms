/*
 * Given a sequence of n numbers design a linear time algorithm to compute
the length of the maximum sum sub array.
 */

public class MaxSumSubArray {

    /**
     * Computes the length of the subarray with the maximum sum in linear time O(n).
     *
     * @param nums The input array of numbers.
     * @return The length of the maximum sum subarray.
     */
    public static int findMaxSumSubarrayLength(int[] nums) {
        // Handle edge cases: if the array is null or empty, there's no subarray.
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Initialize variables
        int maxSoFar = Integer.MIN_VALUE; // Stores the maximum sum found so far (global max)
        int currentMax = 0; // Stores the sum of the current subarray

        int start = 0; // Start index of the current subarray
        int bestStart = 0; // Start index of the best subarray found so far
        int bestEnd = 0; // End index of the best subarray found so far

        // Loop through the array once
        for (int i = 0; i < nums.length; i++) {
            // Add the current element to the sum of the current subarray
            currentMax += nums[i];

            // If the current sum is better than the global max, we have a new best subarray
            if (currentMax > maxSoFar) {
                maxSoFar = currentMax;
                bestStart = start;
                bestEnd = i;
            }

            // If currentMax is negative, it won't help any future subarray.
            // So, reset it and start a new subarray from the next element.
            if (currentMax < 0) {
                currentMax = 0;
                start = i + 1;
            }
        }

        // The length is the difference in indices plus one
        return bestEnd - bestStart + 1;
    }

    public static void main(String[] args) {
        int[] sequence1 = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        // The max sum subarray is [4, -1, 2, 1] with a sum of 6. Its length is 4.
        System.out.println("For sequence [-2, 1, -3, 4, -1, 2, 1, -5, 4]:");
        System.out.println("Length of max sum subarray: " + findMaxSumSubarrayLength(sequence1)); // Expected: 4

        System.out.println("---");

        int[] sequence2 = { 5, 4, -1, 7, 8 };
        // The max sum subarray is the entire array with a sum of 23. Its length is 5.
        System.out.println("For sequence [5, 4, -1, 7, 8]:");
        System.out.println("Length of max sum subarray: " + findMaxSumSubarrayLength(sequence2)); // Expected: 5

        System.out.println("---");

        int[] sequence3 = { -5, -1, -3 };
        // The max sum subarray is [-1] with a sum of -1. Its length is 1.
        System.out.println("For sequence [-5, -1, -3]:");
        System.out.println("Length of max sum subarray: " + findMaxSumSubarrayLength(sequence3)); // Expected: 1
    }
}
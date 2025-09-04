/*
 * 
 * Given a sequence of n numbers and an integer k, design a linear time
algorithm to compute the length of the maximum sum sub array , whos
length is exactly k.
 */

public class MaxSumFixedSubarray {

    /**
     * Computes the maximum sum of a subarray of a fixed length k.
     * This is a classic "Sliding Window" problem.
     *
     * @param nums The input array of numbers.
     * @param k    The exact length of the subarray.
     * @return The maximum sum of a subarray of length k.
     */
    public static int findMaxSumOfSubarray(int[] nums, int k) {
        // Handle edge cases:
        // 1. If the array is null or empty.
        // 2. If k is non-positive.
        // 3. If the array length is less than k.
        if (nums == null || nums.length < k || k <= 0) {
            // Or throw an IllegalArgumentException, depending on requirements.
            return 0;
        }

        // --- Step 1: Compute the sum of the first window ---
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }

        // Initialize maxSum with the sum of the first window
        int maxSum = windowSum;

        // --- Step 2: Slide the window from k to the end of the array ---
        for (int i = k; i < nums.length; i++) {
            // Update windowSum in O(1) time:
            // Add the new element entering the window (at index i)
            // Subtract the old element leaving the window (at index i-k)
            windowSum = windowSum + nums[i] - nums[i - k];

            // Update the overall maximum sum if the current window's sum is greater
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] sequence = { 1, 4, 2, 10, 23, 3, 1, 0, 20 };
        int k = 4;
        // Subarrays of length 4 and their sums:
        // [1, 4, 2, 10] -> 17
        // [4, 2, 10, 23] -> 39
        // [2, 10, 23, 3] -> 38
        // [10, 23, 3, 1] -> 37
        // [23, 3, 1, 0] -> 27
        // [3, 1, 0, 20] -> 24
        // The maximum sum is 39.

        int maxSum = findMaxSumOfSubarray(sequence, k);
        System.out.println("For sequence [1, 4, 2, 10, 23, 3, 1, 0, 20] and k=4:");
        System.out.println("The maximum sum is: " + maxSum); // Expected: 39

        System.out.println("---");
        System.out.println("The length of this subarray is, of course, " + k);
    }
}
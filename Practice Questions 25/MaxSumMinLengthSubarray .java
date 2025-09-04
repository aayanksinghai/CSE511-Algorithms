public class MaxSumMinLengthSubarray {

    /**
     * Computes the length of the maximum sum subarray with a minimum length of k.
     * The algorithm runs in O(n) time and O(1) space.
     *
     * @param nums The input array of numbers.
     * @param k    The minimum length of the subarray.
     * @return The length of the maximum sum subarray of length at least k.
     */
    public static int findMaxSumSubarrayMinLength(int[] nums, int k) {
        // Handle edge cases where a solution isn't possible.
        if (nums == null || nums.length < k || k <= 0) {
            return 0;
        }

        // --- Step 1: Initialize with the first k-sized window ---
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }

        int overallMaxSum = windowSum; // The best sum found so far, globally.
        int maxEndingHereSum = windowSum; // The best sum for a subarray ending at the current position.

        int currentLength = k; // Length of the subarray corresponding to maxEndingHereSum.
        int bestLength = k; // The length of the subarray with overallMaxSum.

        // --- Step 2: Iterate from the k-th element onwards ---
        for (int i = k; i < nums.length; i++) {
            // Update the k-sized window sum (our "Minimalist" choice)
            windowSum = windowSum + nums[i] - nums[i - k];

            // The "Extension" choice's sum
            int extensionSum = maxEndingHereSum + nums[i];

            // Decide between starting a new k-sized window or extending the previous best
            // one.
            if (extensionSum > windowSum) {
                // We choose to extend the previous best subarray.
                maxEndingHereSum = extensionSum;
                currentLength++; // The length grows by one.
            } else {
                // We choose to start a new k-sized subarray.
                maxEndingHereSum = windowSum;
                currentLength = k; // The length resets to k.
            }

            // Check if the best subarray ending here is the best we've seen overall.
            if (maxEndingHereSum > overallMaxSum) {
                overallMaxSum = maxEndingHereSum;
                bestLength = currentLength; // Record its length.
            }
        }

        return bestLength;
    }

    public static void main(String[] args) {
        int[] sequence = { 1, 2, -5, 8, -4, 10, -1, 5 };
        int k = 3;

        // Possible subarrays of length >= 3 and their sums:
        // [1, 2, -5] -> -2
        // [8, -4, 10] -> 14
        // [8, -4, 10, -1] -> 13
        // [8, -4, 10, -1, 5] -> 18 <-- Max sum
        // The max sum subarray is [8, -4, 10, -1, 5] with sum 18. Its length is 5.

        int length = findMaxSumSubarrayMinLength(sequence, k);
        System.out.println("For sequence [1, 2, -5, 8, -4, 10, -1, 5] and k=3:");
        System.out.println("Length of max sum subarray: " + length); // Expected: 5
    }
}
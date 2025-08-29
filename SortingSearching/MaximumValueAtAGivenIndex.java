/*
 * You are given three positive integers: n, index, and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:

nums.length == n
nums[i] is a positive integer where 0 <= i < n.
abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
The sum of all the elements of nums does not exceed maxSum.
nums[index] is maximized.
Return nums[index] of the constructed array.

Note that abs(x) equals x if x >= 0, and -x otherwise.

 * Link: https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-array/description/?envType=problem-list-v2&envId=binary-search
 * Author: AAYANK SINGHAI (MT2025001)
 */

public class MaximumValueAtAGivenIndex {
    public int maxValue(int n, int index, int maxSum) {
        long left = 1;
        long right = maxSum; // The lowest possible value is 1, the highest is maxSum.
        int result = 0;

        long leftSpace = index; // The number of elements on the left and right of the peak index.
        long rightSpace = n - 1 - index;

        while (left <= right) {
            long peakValue = left + (right - left) / 2;
            long leftSum = getSlopeSum(peakValue, leftSpace);
            long rightSum = getSlopeSum(peakValue, rightSpace);
            long totalSum = leftSum + peakValue + rightSum;

            if (totalSum <= maxSum) {
                result = (int) peakValue;
                left = peakValue + 1;
            } else {
                right = peakValue - 1;
            }
        }
        return result;
    }

    private long getSlopeSum(long peakValue, long space) {
        long sum = 0;
        long slopeLength = peakValue - 1;

        if (slopeLength >= space) {
            long sumOfFullSlope = slopeLength * (slopeLength + 1) / 2;
            long sumOfUnusedPart = (slopeLength - space) * (slopeLength - space + 1) / 2;
            sum = sumOfFullSlope - sumOfUnusedPart;
        } else {
            long sumOfSlopePart = slopeLength * (slopeLength + 1) / 2;
            long paddingCount = space - slopeLength;
            sum = sumOfSlopePart + paddingCount;
        }
        return sum;
    }
}

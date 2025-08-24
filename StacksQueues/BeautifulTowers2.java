/*
 * You are given a 0-indexed array maxHeights of n integers.

You are tasked with building n towers in the coordinate line. The ith tower is built at coordinate i and has a height of heights[i]
Return the maximum possible sum of heights of a beautiful configuration of towers.
 * 
 * Link: https://leetcode.com/problems/beautiful-towers-ii/description/?envType=problem-list-v2&envId=stack
 * Author: AAYANK SINGHAI (MT2025001)
 * 
 */


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class BeautifulTowers2 {
        public long maximumSumOfHeights(List<Integer> maxHeights) {
        int numTowers = maxHeights.size();

        int[] prevSmalIndx = findPreviousSmaller(maxHeights, numTowers);
        int[] nextSmallIndx = findNextSmaller(maxHeights, numTowers);

        long[] leftSumSlope = new long[numTowers];
        for (int i = 0; i < numTowers; i++) {
            int prevIndex = prevSmalIndx[i];
            long currentHeight = maxHeights.get(i);
            long towersInBlock = i - prevIndex;
            
            long sumBeforeBlock = (prevIndex == -1) ? 0 : leftSumSlope[prevIndex];
            leftSumSlope[i] = sumBeforeBlock + towersInBlock * currentHeight;
        }

        long[] rightSumSlope = new long[numTowers];
        for (int i = numTowers - 1; i >= 0; i--) {
            int nextIndex = nextSmallIndx[i];
            long currentHeight = maxHeights.get(i);
            long towersInBlock = nextIndex - i;

            long sumAfterBlock = (nextIndex == numTowers) ? 0 : rightSumSlope[nextIndex];
            rightSumSlope[i] = sumAfterBlock + towersInBlock * currentHeight;
        }

        long overallMaxSum = 0;
        for (int i = 0; i < numTowers; i++) {
            long mountainSum = leftSumSlope[i] + rightSumSlope[i] - maxHeights.get(i);
            overallMaxSum = Math.max(overallMaxSum, mountainSum);
        }

        return overallMaxSum;
    }

    private int[] findPreviousSmaller(List<Integer> heights, int n) {
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>(); // Using ArrayDeque as a stack

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights.get(stack.peek()) >= heights.get(i)) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return result;
    }

    private int[] findNextSmaller(List<Integer> heights, int n) {
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights.get(stack.peek()) >= heights.get(i)) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        return result;
    }
}

/*
 * You are given a 0-indexed integer array costs where costs[i] is the cost of hiring the ith worker.
 * Return the total cost to hire exactly k workers.
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * Link: https://leetcode.com/problems/total-cost-to-hire-k-workers/description/?envType=problem-list-v2&envId=vapxduwj
 */

import java.util.PriorityQueue;

public class TotalCostToHireKCandidates {
    public long totalCost(int[] costs, int k, int candidates) {
        // TC: O(K*log(candidates))
        long totalSum = 0;

        PriorityQueue<Integer> leftpq = new PriorityQueue<>();
        PriorityQueue<Integer> rightpq = new PriorityQueue<>();

        int costLen = costs.length;
        int left = 0;
        int right = costLen - 1;

        for (int i = 0; i < candidates; i++) {
            if (left <= right) {
                leftpq.add(costs[left++]);
            }
        }

        for (int j = 0; j < candidates; j++) {
            if (left <= right) {
                rightpq.add(costs[right--]);
            }
        }

        while (k > 0) {
            int leftCost = leftpq.isEmpty() ? Integer.MAX_VALUE : leftpq.peek();
            int rightCost = rightpq.isEmpty() ? Integer.MAX_VALUE : rightpq.peek();

            if (leftCost <= rightCost) {
                totalSum += leftpq.poll();
                if (left <= right) {
                    leftpq.add(costs[left++]);
                }
            } else {
                totalSum += rightpq.poll();
                if (left <= right) {
                    rightpq.add(costs[right--]);
                }
            }
            k--;
        }

        return totalSum;
    }
}

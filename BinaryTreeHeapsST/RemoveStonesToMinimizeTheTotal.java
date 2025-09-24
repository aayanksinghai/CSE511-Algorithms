/*
 * 
 * You are given a 0-indexed integer array piles, where piles[i] represents the number of stones in the ith pile, and an integer k. You should apply the following operation exactly k times:

Choose any piles[i] and remove floor(piles[i] / 2) stones from it.
Notice that you can apply the operation on the same pile more than once.

Return the minimum possible total number of stones remaining after applying the k operations.

floor(x) is the largest integer that is smaller than or equal to x (i.e., rounds x down).

Author: AAYANK SINGHAI

Link: https://leetcode.com/problems/remove-stones-to-minimize-the-total/description/?envType=problem-list-v2&envId=heap-priority-queue
 */

import java.util.Collections;
import java.util.PriorityQueue;

public class RemoveStonesToMinimizeTheTotal {

    public int minStoneSum(int[] piles, int k) {
        // TC: O(N+klogN)
        int minStones = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i : piles) {
            pq.add(i);
        }

        while (k > 0) {
            int popEle = pq.poll();
            // System.out.print("Element popeed: " + popEle);
            int op = (int) Math.ceil((double) popEle / 2.0);
            // System.out.println(" Element operation perform: " + op);
            pq.add(op);
            k--;
        }

        for (int i = 0; i < piles.length; i++) {
            // System.out.println("Element popping for final answer: " + pq.peek());
            minStones += pq.poll();
        }
        return minStones;
    }

}

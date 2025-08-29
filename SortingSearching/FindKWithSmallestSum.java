/*
 * You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.

Define a pair (u, v) which consists of one element from the first array and one element from the second array.

Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 * 
 * Link: https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/
 * Author: AAYANK SINGHAI (MT2025001)
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindKWithSmallestSum {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> resultPairs = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) { // edge cases
            return resultPairs;
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]); // minheap stornig pairs : sum,
                                                                                   // indx1, indx2

        for (int i = 0; i < nums1.length && i < k; i++) {
            minHeap.offer(new int[] { nums1[i] + nums2[0], i, 0 });
        }

        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] current = minHeap.poll(); // pair smallest sum from heap
            int nums1_idx = current[1];
            int nums2_idx = current[2];

            List<Integer> pair = new ArrayList<>();
            pair.add(nums1[nums1_idx]);
            pair.add(nums2[nums2_idx]);
            resultPairs.add(pair);

            if (nums2_idx + 1 < nums2.length) {
                minHeap.offer(new int[] { nums1[nums1_idx] + nums2[nums2_idx + 1], nums1_idx, nums2_idx + 1 });
            }
        }

        return resultPairs;

    }
}

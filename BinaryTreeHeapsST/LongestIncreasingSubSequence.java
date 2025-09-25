/*
 * Find the longest subsequence of nums that meets the following requirements:

The subsequence is strictly increasing and
The difference between adjacent elements in the subsequence is at most k.
Return the length of the longest subsequence that meets the requirements.
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * Link: https://leetcode.com/problems/longest-increasing-subsequence-ii/description/?envType=problem-list-v2&envId=segment-tree
 * 
 */

public class LongestIncreasingSubSequence {
    int[] tree;
    int maxVal = 0;

    public int lengthOfLIS(int[] nums, int k) {
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        tree = new int[4 * (maxVal + 1)];
        int overallMaxLen = 0;

        for (int num : nums) {
            int startRange = Math.max(0, num - k);
            int endRange = num - 1;
            int prevMaxLen = 0;
            if (endRange >= startRange) {
                prevMaxLen = query(1, 0, maxVal, startRange, endRange);
            }
            int newLen = 1 + prevMaxLen;
            update(1, 0, maxVal, num, newLen);
            overallMaxLen = Math.max(overallMaxLen, newLen);
        }
        return overallMaxLen;
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = Math.max(tree[node], val);
            return;
        }

        int mid = start + (end - start) / 2;
        if (start <= idx && idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }

        tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
    }

    private int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return 0;
        }

        if (l <= start && end <= r) {
            return tree[node];
        }

        int mid = start + (end - start) / 2;
        int p1 = query(2 * node, start, mid, l, r);
        int p2 = query(2 * node + 1, mid + 1, end, l, r);

        return Math.max(p1, p2);
    }
}

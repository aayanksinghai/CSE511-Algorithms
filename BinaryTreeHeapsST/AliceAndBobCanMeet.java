/*
 * Return an array ans where ans[i] is the index of the leftmost building where Alice and Bob can meet on the ith query. 
 * If Alice and Bob cannot move to a common building on query i, set ans[i] to -1.
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * Link: https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/description/?envType=problem-list-v2&envId=segment-tree
 */

public class AliceAndBobCanMeet {

    class SegmentTree {
        private long[] tree;
        private long[] values;
        private int size;

        private void build(int curr, int left, int right) {
            if (left == right) {
                tree[curr] = values[left];
                return;
            }
            int mid = (left + right) / 2;
            build(2 * curr + 1, left, mid);
            build(2 * curr + 2, mid + 1, right);
            tree[curr] = Math.max(tree[2 * curr + 1], tree[2 * curr + 2]);
        }

        SegmentTree(int[] arr) {
            this.size = arr.length;
            this.values = new long[size];
            for (int i = 0; i < size; i++) {
                values[i] = arr[i];
            }
            this.tree = new long[4 * size];
            build(0, 0, size - 1);
        }

        public long query(int curr, int left, int right, int queryLeft, int queryRight) {
            if (queryLeft > right || queryRight < left) {
                return Long.MIN_VALUE;
            }
            if (left >= queryLeft && right <= queryRight) {
                return tree[curr];
            }
            int mid = (left + right) / 2;
            long leftResult = query(2 * curr + 1, left, mid, queryLeft, queryRight);
            long rightResult = query(2 * curr + 2, mid + 1, right, queryLeft, queryRight);
            return Math.max(leftResult, rightResult);
        }
    }

    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length;
        SegmentTree segmentTree = new SegmentTree(heights);
        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0];
            int b = queries[i][1];

            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            int threshold = Math.max(heights[a], heights[b]);
            if (heights[a] < heights[b] || a == b) {
                result[i] = b;
                continue;
            }

            int left = b, right = n - 1;
            if (segmentTree.query(0, 0, n - 1, b, n - 1) <= threshold) {
                result[i] = -1;
                continue;
            }

            while (left <= right) {
                int mid = (left + right) / 2;
                long maxInRange = segmentTree.query(0, 0, n - 1, b, mid);
                if (maxInRange > threshold) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            result[i] = heights[left] <= threshold ? -1 : left;
        }

        return result;
    }
}

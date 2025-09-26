/*
 * 
 * You are given two 0-indexed integer arrays nums1 and nums2, each of length n, and a 1-indexed 2D array queries where queries[i] = [xi, yi].

For the ith query, find the maximum value of nums1[j] + nums2[j] among all indices j (0 <= j < n), where nums1[j] >= xi and nums2[j] >= yi, or -1 if there is no j satisfying the constraints.

Return an array answer where answer[i] is the answer to the ith query.

Author: AAYANK SINGHAI (MT2025001)

Link: https://leetcode.com/problems/maximum-sum-queries/description/?envType=problem-list-v2&envId=segment-tree
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class MaximumSumQueries {
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int q = queries.length;

        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
            points[i][0] = nums1[i];
            points[i][1] = nums2[i];
        }

        int[][] sortedQueries = new int[q][3];
        for (int i = 0; i < q; i++) {
            sortedQueries[i][0] = queries[i][0];
            sortedQueries[i][1] = queries[i][1];
            sortedQueries[i][2] = i;
        }

        Arrays.sort(points, (a, b) -> Integer.compare(b[0], a[0]));
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(b[0], a[0]));

        TreeSet<Integer> ySet = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            ySet.add(points[i][1]);
        }
        for (int i = 0; i < q; i++) {
            ySet.add(sortedQueries[i][1]);
        }

        List<Integer> yCoords = new ArrayList<>(ySet);
        Map<Integer, Integer> yMap = new HashMap<>();
        for (int i = 0; i < yCoords.size(); i++) {
            yMap.put(yCoords.get(i), i);
        }

        SegmentTree st = new SegmentTree(yCoords.size());
        int[] answer = new int[q];
        int pointIndex = 0;

        for (int i = 0; i < q; i++) {
            int xi = sortedQueries[i][0];
            int yi = sortedQueries[i][1];
            int originalIndex = sortedQueries[i][2];

            while (pointIndex < n && points[pointIndex][0] >= xi) {
                int px = points[pointIndex][0];
                int py = points[pointIndex][1];
                int yIdx = yMap.get(py);
                st.update(yIdx, px + py);
                pointIndex++;
            }

            int searchResult = Collections.binarySearch(yCoords, yi);
            int yQueryIdx = (searchResult >= 0) ? searchResult : -searchResult - 1;

            if (yQueryIdx < yCoords.size()) {
                answer[originalIndex] = st.query(yQueryIdx, yCoords.size() - 1);
            } else {
                answer[originalIndex] = -1;
            }
        }
        return answer;
    }
}

class SegmentTree {
    private int[] tree;
    private int n;

    public SegmentTree(int size) {
        this.n = size;
        this.tree = new int[4 * n];
        Arrays.fill(tree, -1);
    }

    public void update(int index, int value) {
        update(0, 0, n - 1, index, value);
    }

    private void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = Math.max(tree[node], value);
            return;
        }
        int mid = start + (end - start) / 2;
        if (index <= mid) {
            update(2 * node + 1, start, mid, index, value);
        } else {
            update(2 * node + 2, mid + 1, end, index, value);
        }
        tree[node] = Math.max(tree[2 * node + 1], tree[2 * node + 2]);
    }

    public int query(int queryStart, int queryEnd) {
        return query(0, 0, n - 1, queryStart, queryEnd);
    }

    private int query(int node, int start, int end, int queryStart, int queryEnd) {
        if (queryStart > end || queryEnd < start) {
            return -1; // Outside range
        }
        if (queryStart <= start && end <= queryEnd) {
            return tree[node]; // Total overlap
        }
        int mid = start + (end - start) / 2;
        int leftMax = query(2 * node + 1, start, mid, queryStart, queryEnd);
        int rightMax = query(2 * node + 2, mid + 1, end, queryStart, queryEnd);
        return Math.max(leftMax, rightMax);
    }
}
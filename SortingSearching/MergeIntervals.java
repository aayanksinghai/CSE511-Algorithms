/*
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the 
 * non-overlapping intervals that cover all the intervals in the input.
 * 
 * Link: https://leetcode.com/problems/merge-intervals/description/
 * Author: AAYANK SINGHAI (MT2025001)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    public static int comp(int[] val1, int[] val2) {
        return Integer.compare(val1[0], val2[0]);
    }

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][];
        }

        List<int[]> nonOverlappingList = new ArrayList<>();
        Arrays.sort(intervals, MergeIntervals::comp);

        int n = intervals.length;
        int initialEnd = intervals[0][1];
        int initialStartTime = intervals[0][0];
        for (int i = 1; i < n; i++) {
            if (initialEnd >= intervals[i][0]) {
                initialEnd = Math.max(initialEnd, intervals[i][1]);
                continue;
            }
            nonOverlappingList.add(new int[] { initialStartTime, initialEnd });
            initialStartTime = intervals[i][0];
            initialEnd = intervals[i][1];
        }

        nonOverlappingList.add(new int[] { initialStartTime, initialEnd });

        return nonOverlappingList.toArray(new int[nonOverlappingList.size()][]);
    }
}

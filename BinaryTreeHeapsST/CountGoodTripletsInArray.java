/*
 * You are given two 0-indexed arrays nums1 and nums2 of length n, both of which are permutations of [0, 1, ..., n - 1].

A good triplet is a set of 3 distinct values which are present in increasing order by position both in nums1 and nums2. In other words, if we consider pos1v as the index of the value v in nums1 and pos2v as the index of the value v in nums2, then a good triplet will be a set (x, y, z) where 0 <= x, y, z <= n - 1, such that pos1x < pos1y < pos1z and pos2x < pos2y < pos2z.

Return the total number of good triplets.
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * Link: https://leetcode.com/problems/count-good-triplets-in-an-array/description/?envType=problem-list-v2&envId=binary-indexed-tree
 */

class CountGoodTripletsInArray {
    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;

        int[] pos2 = new int[n];
        for (int i = 0; i < n; i++) {
            pos2[nums2[i]] = i;
        }

        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = pos2[nums1[i]];
        }

        long[] leftCounts = new long[n];
        BIT leftBit = new BIT(n);
        for (int i = 0; i < n; i++) {
            leftCounts[i] = leftBit.query(A[i]);
            leftBit.update(A[i] + 1, 1);
        }

        long[] rightCounts = new long[n];
        BIT rightBit = new BIT(n);
        for (int i = n - 1; i >= 0; i--) {
            long smallerOrEqual = rightBit.query(A[i] + 1);
            long totalSeen = (n - 1 - i);
            rightCounts[i] = totalSeen - smallerOrEqual;
            rightBit.update(A[i] + 1, 1);
        }

        long totalTriplets = 0;
        for (int i = 0; i < n; i++) {
            totalTriplets += leftCounts[i] * rightCounts[i];
        }

        return totalTriplets;
    }
}

class BIT {
    private int[] tree;
    private int size;

    public BIT(int n) {
        this.size = n + 1;
        this.tree = new int[this.size];
    }

    public void update(int i, int delta) {
        while (i < size) {
            tree[i] += delta;
            i += i & (-i);
        }
    }

    public long query(int i) {
        long sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= i & (-i);
        }
        return sum;
    }
}
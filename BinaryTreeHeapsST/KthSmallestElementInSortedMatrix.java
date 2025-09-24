/*
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

You must find a solution with a memory complexity better than O(n2).
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * 
 * Link: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
 */

public class KthSmallestElementInSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        // TLE TC: O(N^2(logN^2))
        /*
         * PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
         * int n = matrix.length;
         * 
         * for(int i = 0; i < n; i++){
         * for(int j = 0; j < n; j++){
         * pq.add(matrix[i][j]);
         * }
         * }
         * 
         * int kthSmallest = n*n - k;
         * 
         * while(kthSmallest > 0) pq.poll();
         * return pq.peek();
         */

        // Optimal Solution - Binary Search O(NlogD) D = diff(larg, small);
        int n = matrix.length;
        int low = matrix[0][0];
        int high = matrix[n - 1][n - 1];
        int ans = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            int count = countFunc(matrix, mid);
            if (count < k) {
                low = mid + 1;
            } else {
                ans = mid;
                high = mid - 1;
            }
        }

        return ans;
    }

    private static int countFunc(int[][] matrix, int x) {
        int n = matrix.length;
        int count = 0;

        // starting bottom left
        int row = n - 1;
        int col = 0;

        while (row >= 0 && col < n) {
            if (matrix[row][col] <= x) {
                count += (row + 1);
                col++; // Moving next col to find more valid ele.
            } else {
                row--;
            }
        }

        return count;
    }
}

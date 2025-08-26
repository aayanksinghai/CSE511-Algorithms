/*
 * 
 * You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

Link: https://leetcode.com/problems/search-a-2d-matrix/description/?envType=problem-list-v2&envId=binary-search

Author: AAYANK SINGHAI (MT2025001)
 */


public class SearchIn2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Optimal Approach TC: O(log(N*M))
        int row = matrix.length;
        int col = matrix[0].length;
        int low = 0, high = row*col-1;

        while(low <= high){
            int mid = (low+high)/2;
            int rowIndex = mid/col;
            int colIndex = mid%col;

            if(matrix[rowIndex][colIndex] == target){
                return true;
            }else if(matrix[rowIndex][colIndex] < target){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return false;
    }
}

/*
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * 
 * Link: https://leetcode.com/problems/maximal-rectangle/description/?envType=problem-list-v2&envId=stack
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 */

import java.util.Stack;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        //Size of matrix
       int n = matrix.length;
       int m = matrix[0].length;

       int[][] prefixSum = new int[n][m]; //storing different ground level heights

       for(int j = 0; j < m; j++){
        int sum = 0;

        for(int i = 0; i < n; i++){
            sum += Character.getNumericValue(matrix[i][j]);

            if(Character.getNumericValue(matrix[i][j]) == 0){
                prefixSum[i][j] = 0;
                sum = 0;
            }else{
                prefixSum[i][j] = sum;
            }
        }
       }

       int maxArea = 0;

       for(int i = 0; i < n; i++){
        int area  =  largestAreaHistogram(prefixSum[i]);
        maxArea = Math.max(area, maxArea);
       }

       return maxArea;
    }


    public static int largestAreaHistogram(int[] heights){
        int[] NSE = findNSE(heights);
        int[] PSE = findPSE(heights);

        int largestAreaOfRectangle = 0;

        for(int i = 0; i < heights.length; i++){
            largestAreaOfRectangle = Math.max(largestAreaOfRectangle, heights[i]*(NSE[i]-PSE[i]-1));
        }
        return largestAreaOfRectangle;
    }

    public static int[] findNSE(int[] arr){
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        
        for(int i = arr.length-1; i >= 0; i--){
            while(!st.isEmpty() && arr[st.peek()] >= arr[i]){
                st.pop(); //Keep popping until the top element is not smaller
            }

            if(!st.isEmpty()){
                ans[i] = st.peek();
            }else{
                ans[i] = arr.length;
            }
            st.push(i);
        }
        return ans;
    }

    public static int[] findPSE(int[] arr){
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];

        for(int i = 0; i < arr.length; i++){
            while(!st.isEmpty() && arr[st.peek()] >= arr[i]){
                st.pop();
            }

            if(!st.isEmpty()){
                ans[i] = st.peek();
            }else{
                ans[i] = -1;
            }
            st.push(i);
        }
        return ans;
    }
}

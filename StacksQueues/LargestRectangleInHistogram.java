/*
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, 
 * return the area of the largest rectangle in the histogram.
 * 
 * Link: https://leetcode.com/problems/largest-rectangle-in-histogram/description/?envType=problem-list-v2&envId=stack
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 */

import java.util.Stack;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
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

/*
 * Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.

The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going backward) 
for which the stock price was less than or equal to the price of that day.

Link: https://leetcode.com/problems/online-stock-span/description/?envType=problem-list-v2&envId=stack

Author: AAYANK SINGHAI MT2025001
 */

import java.util.Stack;

public class StockSpanner {
    Stack<int[]> st;
    
    public StockSpanner() {
         st = new Stack<>();   //initialising with pair of (price, span)  
    }
    
    public int next(int price) {
         int currentSpanValue = 1;
        while(!st.isEmpty() && st.peek()[0] <= price){
            currentSpanValue += st.pop()[1];
        }
        st.push(new int[]{price, currentSpanValue});
        return currentSpanValue;   
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */

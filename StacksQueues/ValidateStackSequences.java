/*
 * Given two integer arrays pushed and popped each with distinct values, return true 
 * if this could have been the result of a sequence of push and pop operations 
 * on an initially empty stack, or false otherwise.
 *  
 * Link: https://leetcode.com/problems/validate-stack-sequences/?envType=problem-list-v2
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 */

import java.util.Stack;

public class ValidateStackSequences {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;  //maintaining for poppedArray index

        for(int i = 0; i < pushed.length; i++){
            stack.push(pushed[i]);

            while(!stack.isEmpty() && stack.peek() == popped[j]){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty(); 
    } 
}
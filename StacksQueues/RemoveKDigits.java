/* Given string num representing a non-negative integer num, and an integer k, 
return the smallest possible integer after removing k digits from num. 

Link: https://leetcode.com/problems/remove-k-digits/description/?envType=problem-list-v2\&envId=stack

Author: AAYANK SINGHAI (MT2025001)

*/

package StacksQueues;

import java.util.Stack;

public class RemoveKDigits {
    public String removeKdigits(String num, int k) {

        Stack<Character> st = new Stack<>();

        for(int i = 0; i < num.length(); i++){
            while (!st.isEmpty() && k > 0 && (st.peek()-'0') > (num.charAt(i)-'0')) {
                k--;
                st.pop();
            }
            st.push(num.charAt(i));
        }

        while (!st.isEmpty() && k > 0) { //Edge Case 1 When string parsing terminates but K is still non zero i.e. element are still to remove
            k--;
            st.pop();
        }

        if(st.isEmpty()) return "0";  //Edge Case 2 When string parsing length and k value are equal 

        StringBuilder resultString = new StringBuilder();

        while (!st.isEmpty()) {
            resultString.append(st.pop());
        }

        int resultStringLength = resultString.length();
        while (resultStringLength > 0 && resultString.charAt(resultStringLength-1) == '0') {  //Edge Case 3 Handling 00100 strings leading zeros
            resultString.deleteCharAt(resultStringLength-1);  
        }

        resultString.reverse();
        if(resultString.length() == 0) return "0";

        return resultString.toString();        
    }
}

class Solution {
    public int[] nextGreaterElements(int[] arr) {
        // Brute Force Approach 
        /*
        int[] ans = new int[arr.length];
        if(arr.length == 1) {
            ans[0] = -1; 
            return ans;
        }

        for(int i = 0; i < arr.length; i++){
            for(int j = i+1; j < 2*arr.length; j++){
                if(j == arr.length){
                    j = 0;
                }
                if(j == i){
                    break;
                }
                if(arr[j] > arr[i]){
                    ans[i] = arr[j];
                    break;
                }
                ans[i] = -1;
            }
        }

        return ans;
        */

        // Optimal Approach Using Stack
        
        
    }
}
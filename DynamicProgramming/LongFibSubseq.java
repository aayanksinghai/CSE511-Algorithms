class LongFibSubseq {
    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer,Integer> index = new HashMap();
        for(int i=0 ;i<arr.length;i++){
            index.put(arr[i],i);
        }
        int[][] dp = new int[arr.length][arr.length];
        int max = 0;
        for(int i=0 ;i<arr.length-1;i++){
            for(int j=i+1;j<arr.length;j++){
               if(index.containsKey(arr[j]-arr[i])){
                    int k = index.get(arr[j]-arr[i]);
                    if(k< i)dp[i][j] = 1 + dp[k][i];
                    else dp[i][j] = 2;
               }else{
                    dp[i][j] = 2;
               }
                max = Math.max(dp[i][j],max);
            }
           
        }
        if(max > 2) return max;
        return 0;
        
    }
}
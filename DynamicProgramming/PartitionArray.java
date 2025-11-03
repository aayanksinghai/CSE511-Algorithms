class PartitionArray {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int[] dp = new int[arr.length + 1];
        Arrays.fill(dp,-1);
        return (int)solve(0,k,arr,dp);
    }
    long solve(int i ,int k , int[] arr,int[] dp){
        if(i==arr.length ) return 0;
        if(dp[i]!=-1) return dp[i];
        long max = 0 , maxElem = arr[i] ;
        for(int start = i ;start<Math.min(arr.length ,i+k);start++){
            maxElem = Math.max(maxElem ,arr[start]);
            max = Math.max(max , maxElem * (long)(start-i+1) + solve(start + 1,k,arr,dp));
        }
        return dp[i] = (int)max ;
    }
}
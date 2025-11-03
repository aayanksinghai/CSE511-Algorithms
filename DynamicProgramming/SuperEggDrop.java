class SuperEggDrop {
    public int superEggDrop(int k, int n) {
        int[][] dp = new int[n+1][k+1];
        for(int i=0 ;i<dp.length;i++) Arrays.fill(dp[i],-1);
        return solve(k,n,dp);
    }
    int solve(int k,int n,int[][] dp){
        if(k==1) return n ;
        if(n==0) return 0;
        int min = (int) 1e9 , lo=1 ,hi=n ;
        if(dp[n][k]!=-1) return dp[n][k];
        while(lo<=hi){
            int mid = (lo + hi)/2;
            int breakEgg = 1 + solve(k-1,mid-1 ,dp);
            int notbreak = 1 + solve(k , n-mid ,dp);
            if(breakEgg > notbreak){
                hi = mid -1;
            }else{
                lo = mid + 1;
            }
            min = Math.min(min , Math.max(breakEgg,notbreak));
        }
        return dp[n][k] = min ;
    }
}
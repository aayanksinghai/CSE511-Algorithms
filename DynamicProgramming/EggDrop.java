class EggDrop {
    public int twoEggDrop(int n) {
        int[][] dp = new int[n+1][3];
        for(int i=0 ;i<dp.length;i++) Arrays.fill(dp[i],-1);
        return solve(2,n,dp);
    }
    int solve(int k,int n,int[][] dp){
        if(k==1) return n ;
        if(n==0) return 0;
        int min = (int) 1e9 ;
        if(dp[n][k]!=-1) return dp[n][k];
        for(int j=1 ;j<=n;j++){
            int breakEgg = 1 + solve(k-1,j-1,dp);
            int notbreak = 1 + solve(k,n-j,dp);
            min = Math.min(min , Math.max(breakEgg,notbreak));
        }
        return dp[n][k] = min ;
    }
}
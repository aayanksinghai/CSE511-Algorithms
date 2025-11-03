class FrogJump {
    public boolean canCross(int[] stones) {
        if (stones[1] != 1)
            return false;
        int[][] dp = new int[stones.length + 1][stones.length + 2];
        for(int i=0 ;i<dp.length;i++) Arrays.fill(dp[i],-1);
        return solve(1, 1, stones,dp) == 1;
    }

    int solve(int i, int prevJump, int[] stones,int[][] dp) {
        if(i==stones.length-1) return 1;
        int ans = 0;
        if(dp[i][prevJump]!=-1) return dp[i][prevJump];
        for (int j = i + 1; j < stones.length; j++) {
            if (stones[j] > stones[i] + prevJump + 1)
                break;
           
            else if (  stones[j] == stones[i] + prevJump + 1) {
                ans = ans | solve(j, prevJump + 1, stones,dp);
            }
            else if(stones[j] == stones[i] + prevJump - 1){
                 ans = ans | solve(j, prevJump - 1, stones,dp);
            }else if(stones[j] == stones[i] + prevJump ){
                ans = ans | solve(j, prevJump , stones,dp);
            }
        }

        return dp[i][prevJump] = ans;
    }
}
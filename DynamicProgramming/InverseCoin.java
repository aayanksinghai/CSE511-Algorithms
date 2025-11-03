class InverseCoin {
    public List<Integer> findCoins(int[] numWays) {
        List<Integer> coins = new ArrayList();
        for(int i=0 ;i<numWays.length;i++){
            int[][] dp = new int[coins.size()+1][i+2];
            for(int j=0 ;j<dp.length;j++) Arrays.fill(dp[j],-1);
            int way = ways(0,i+1,coins,dp);
            if(numWays[i]==way+1){
                int val = i+1;
                coins.add(val);
            }else if(numWays[i] > way + 1 || numWays[i] < way){
                return new ArrayList();
            }
        }
        return coins;
    }
    int ways(int i , int amount ,List<Integer> coins,int[][] dp){
        if(amount < 0) return 0;
        if(i==coins.size()){
            if(amount ==0) return 1;
            else return 0;
        }
        if(dp[i][amount]!=-1) return dp[i][amount];
        int take = ways(i,amount - coins.get(i),coins,dp);
        int nottake = ways(i+1,amount , coins,dp);
        return dp[i][amount] = take + nottake ;
    }
}
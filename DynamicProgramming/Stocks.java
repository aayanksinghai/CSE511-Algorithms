class Stocks {
    public int maxProfit(int k, int[] prices) {
        // buy - 1 capable to buy
        // buy - 0 capable to sell
        int n = prices.length ;
        int[][][] dp = new int[n+1][k+1][2];
        
        for(int i=n-1;i>=0;i--){
            for(int j= 1 ;j<=k;j++){
                for(int buy =0 ;buy<2;buy++){
                    if(buy ==1){
                    dp[i][j][buy] = Math.max(-prices[i] + dp[i+1][j][0],dp[i+1][j][buy]); 
                }else{
                    dp[i][j][buy] = Math.max(prices[i] + dp[i+1][j-1][1],dp[i+1][j][buy]); 
                }
                }
                
            }
        }
        return dp[0][k][1];
    }

    int solve(int i, int k, int buy, int[] arr) {
        if (k == 0 || i == arr.length)
            return 0;
        if (buy == 1) {
            return Math.max(-arr[i] + solve(i + 1, k, 0, arr), solve(i + 1, k, buy, arr));
        } else {
            return Math.max(arr[i] + solve(i + 1, k - 1, 1, arr), solve(i + 1, k, buy, arr));
        }
    }
}
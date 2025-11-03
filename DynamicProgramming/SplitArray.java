class SplitArray {
    public int splitArray(int[] nums, int k) {
        int[][] dp = new int[nums.length+1][100];
        for(int i=0 ;i<dp.length;i++) Arrays.fill(dp[i],-1);
        return solve(0,k,nums,dp);
    }
    int solve(int i ,int k ,int[] nums,int[][] dp){
        if(k==0 && nums.length!=i) return (int)1e9;
        if(nums.length==i){
            if(k > 0) return (int)1e9;
            else return 0;
        }
        if(dp[i][k]!=-1) return dp[i][k];
        int min = (int)1e9 ;
        int sum = 0;
        for(int j= i;j<nums.length;j++){
            sum = sum + nums[j];
            min = Math.min(min ,Math.max(sum , solve(j+1,k-1,nums,dp)));
            
        }
        return dp[i][k] = min ;
    }
}
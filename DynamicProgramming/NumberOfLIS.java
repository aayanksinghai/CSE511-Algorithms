class NumberOfLIS {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length ;
        int[] dp = new int[n];
        int[] count = new int[n];
        Arrays.fill(dp,1);
        count[0] = 1;
        int res = 0 , max =0;
        for(int i=1;i<nums.length;i++){
            for(int j=0 ;j<i;j++){
                if(nums[i] >nums[j]){
                    dp[i] = Math.max(dp[i] ,dp[j] + 1);
                }
            }
            for(int j=0 ;j<i;j++){
                if(nums[i] >nums[j] && dp[i]==dp[j]+1){
                    count[i] = count[i] + count[j];
                }
            }
            if(count[i]==0) count[i] = 1;
        }
        for(int i=0 ;i<n;i++) max = Math.max(max ,dp[i]);
        for(int i=0 ;i<n;i++){
            if(max==dp[i]){
                res = res + count[i];
            }
        }
        return res ;
        
    }
}
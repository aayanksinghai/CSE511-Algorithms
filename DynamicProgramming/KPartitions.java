class KPartitions {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0, n = nums.length , bitmask=0;
        Arrays.sort(nums);
        for (int num : nums) {
            sum = sum + num;
        }
        if (sum % k != 0)
            return false;
       
        int reqSum = sum / k;
        return solve(0,bitmask,0,reqSum , k ,nums)==1;
    }
    int solve(int i ,int bitmask ,int sum ,int reqSum ,  int k , int[] nums){
        if(k==0) return 1;
        if(sum ==reqSum){
            return solve(0 , bitmask,0,reqSum ,k-1,nums);
        }
        if(i==nums.length) return 0;
         
        int take = 0;
        if(sum + nums[i]<=reqSum){
            if(((bitmask>>i)&1)==0){
                int mask = bitmask | (1<<i);
                take = solve(i+1,mask,sum + nums[i],reqSum ,k , nums);
            }
        }else{
            return 0;
        }
        return take | solve(i+1 , bitmask , sum , reqSum ,k,nums );
        
        
    }

    
}
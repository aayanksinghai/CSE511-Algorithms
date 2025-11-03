class MatchSticks {
    int[] memo ;
    public boolean makesquare(int[] matchsticks) {
        int sum = 0, n =matchsticks.length , bitmask=0;
        int k = 4;
        Arrays.sort(matchsticks);
        for (int num : matchsticks) {
            sum = sum + num;
        }
        if (sum % k != 0)
            return false;

        int reqSum = sum / k;
        memo = new int[(1<<n)];
        Arrays.fill(memo ,-1);
        return solve(0,bitmask,0,reqSum , k ,matchsticks)==1;
    }
    int solve(int i ,int bitmask ,int sum ,int reqSum ,  int k , int[] nums){
        if(k==0) return 1;
        if(sum ==reqSum){
            return memo[bitmask] = solve(0 , bitmask,0,reqSum ,k-1,nums);
        }
        if(i==nums.length) return 0;
        if(memo[bitmask]!=-1) return memo[bitmask];
        int take = 0;
        if(sum + nums[i]<=reqSum){
            if(((bitmask>>i)&1)==0){
                int mask = bitmask | (1<<i);
                take = solve(i+1,mask,sum + nums[i],reqSum ,k , nums);
            }
        }else{
            return memo[bitmask] = 0;
        }

        memo[bitmask] = take | solve(i+1 , bitmask , sum , reqSum ,k,nums );
        int ans = memo[bitmask];
        //   memo[bitmask] = -1;
        return ans ;


    }
}
public class CanIWin {
    Map<Integer, Boolean> memo = new HashMap<>();

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (maxChoosableInteger * (maxChoosableInteger + 1)) / 2;
        if (sum < desiredTotal) return false;
        if (desiredTotal <= 0) return true;
        return canWin(maxChoosableInteger, desiredTotal, 0, 0);
    }

    private boolean canWin(int maxChoosableInteger, int desiredTotal, int used, int currentSum) {
        if (currentSum >= desiredTotal) return false;

        if (memo.containsKey(used)) return memo.get(used);

        for (int i = 1; i <= maxChoosableInteger; i++) {
            int mask = 1 << i;
            if ((used & mask) == 0) {
                if (!canWin(maxChoosableInteger, desiredTotal, used | mask, currentSum + i)) {
                    memo.put(used, true);
                    return true;
                }
            }
        }
        memo.put(used, false);
        return false;
    }
}
import java.util.*;

class ParallelCourse {
    public int minNumberOfSemesters(int n,int[][] relations,int k){
        int[] pre=new int[n];
        for(int[] e:relations) pre[e[1]-1]|=1<<(e[0]-1);

        int N=1<<n,INF=1_000_000;
        int[] dp=new int[N];
        Arrays.fill(dp,INF);
        dp[0]=1;
        for(int mask=0;mask<N;mask++){
            if(dp[mask]==INF) continue;
            if(mask==N-1) continue;

            int avail=0;
            for(int i=0;i<n;i++){
                if(((mask>>i)&1)==0 && (pre[i]&mask)==pre[i]) avail|=1<<i;
            }
            int cnt=Integer.bitCount(avail);
            if(cnt==0) continue;

            if(cnt<=k){
                int next=mask|avail;
                dp[next]=Math.min(dp[next],dp[mask]+1);
            }else{
                for(int sub=avail;sub>0;sub=(sub-1)&avail){
                    if(Integer.bitCount(sub)==k){
                        int next=mask|sub;
                        dp[next]=Math.min(dp[next],dp[mask]+1);
                    }
                }
            }
        }
        return dp[N-1]-1;
    }
}


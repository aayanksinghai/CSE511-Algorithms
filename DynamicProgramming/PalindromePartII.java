class PalindromePartII {
    public int minCut(String s) {
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp,-1);
        return solve(0, s,dp) - 1;
    }

    int solve(int i, String s,int[] dp) {
        if (i == s.length())
            return 0;
            if(dp[i]!=-1) return dp[i];
        int min = (int) 1e9;
        for (int j = i; j < s.length(); j++) {
            if (isPal(s,i,j)) {
                int val = 1 + solve(j + 1, s,dp);
                min = Math.min(min, val);

            }
        }
        return dp[i] = min;
    }

    boolean isPal(String s ,int st,int end) {
        int i = st, j = end;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
class StudentsExams {
    public int maxStudents(char[][] seats) {
        int[][][][] dp = new int[13][13][300][300];
        for(int i=0 ;i<dp.length;i++){
            for(int j=0 ;j<dp[0].length;j++){
                for(int k=0 ;k<dp[0][0].length;k++){
                    Arrays.fill(dp[i][j][k],-1);
                }
            }
        }
        return solve(0, 0, 0, 0, seats ,dp);
    }

    int solve(int row, int col, int prevBitmask, int currBitmask, char[][] seats , int[][][][] dp) {
        if (row == seats.length)
            return 0;
       
        if (col >= seats[0].length)
            return dp[row][col][prevBitmask][currBitmask] = solve(row + 1, 0, currBitmask, 0, seats,dp);
        int max = -(int) 1e9;
         if(dp[row][col][prevBitmask][currBitmask]!=-1) return dp[row][col][prevBitmask][currBitmask];
        int nottake = solve(row, col + 1, prevBitmask, currBitmask, seats,dp);
        max = Math.max(max , nottake);
        if (seats[row][col] == '#')
            return nottake;
        if (isSafe(row, col, prevBitmask, seats) == 1) {

            currBitmask = (1 << col) | currBitmask; //set
            int take = 1 + solve(row, col + 2, prevBitmask, currBitmask, seats,dp);
            max = Math.max(max, take);
        }

        return dp[row][col][prevBitmask][currBitmask] = max;
    }

    int isSafe(int row, int col, int prevBitmask, char[][] seats) {
        if (seats[row][col] == '#' ||
                (col + 1 < seats[0].length && (((prevBitmask >> (col + 1)) & 1) == 1)) ||
                (col - 1 >= 0 && (((prevBitmask >> (col - 1)) & 1) == 1))) {
            return 0;
        }

        return 1;
    }
}
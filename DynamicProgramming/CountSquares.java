class CountSquares {
    public int countSquares(int[][] matrix) {
        int m= matrix.length ,n=matrix[0].length ,sum=0;
        int[][] dp = new int[m][n];
        for(int i=0 ;i<matrix.length;i++){
            if(matrix[i][0]==1)dp[i][0] = 1;
             sum = sum + dp[i][0];
        }
        for(int i=1 ;i<matrix[0].length;i++){
            if(matrix[0][i]==1)dp[0][i] = 1;
            sum = sum + dp[0][i];
        }
       
        for(int i=1 ;i<m;i++){
            for(int j=1 ;j<n;j++){
                if(matrix[i][j]==1){
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                    sum = sum + dp[i][j];
                }
                
            }
        }
        return sum ;
    }
}
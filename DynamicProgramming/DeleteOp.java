class DeleteOp {
    public int minDistance(String word1, String word2) {
        int[][] dpA = new int[word1.length()+1][word2.length()+1];
        for(int i=0 ;i<=word1.length();i++){
            for(int j=0;j<=word2.length();j++){
                 if(i==word1.length() && j==word2.length()) dpA[i][j] = 0 ;
                else if(i==word1.length() || j==word2.length()) dpA[i][j] = Math.max(word1.length()-i,word2.length()-j);
            }
        }
         for(int i=word1.length()-1 ;i>=0;i--){
            for(int j=word2.length()-1;j>=0;j--){
               if(word1.charAt(i)==word2.charAt(j)) dpA[i][j] = dpA[i+1][j+1];
               else dpA[i][j] = Math.min(Math.min(1 + dpA[i+1][j],1 + dpA[i][j+1]),2 + dpA[i+1][j+1]);
            }
        }
        return dpA[0][0];

    }
    
}
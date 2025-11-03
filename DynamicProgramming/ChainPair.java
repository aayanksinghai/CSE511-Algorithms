class ChainPair {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs ,(a,b)->a[0]-b[0]);
        int prevEnd=pairs[0][1] ,count = 1;
        for(int i=1 ;i<pairs.length;i++){
            if(pairs[i][0] > prevEnd){
                prevEnd = pairs[i][1];
                count++;
            }else{
                prevEnd = Math.min(prevEnd ,pairs[i][1]);
            }
        }
        return count ;
    }
}
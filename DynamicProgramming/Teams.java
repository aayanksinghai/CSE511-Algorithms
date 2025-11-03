class Teams {
    public int numTeams(int[] rating) {
        // countSmallerToLeft * countGreaterToRight
        int n = rating.length , res =0;
        int[] right =  countGreaterToRight(rating);
        int[] left = countSmallerToLeft(rating);
        for(int i=1 ;i<n-1;i++){
            res = res + right[i] * left[i];
        }
        int[] reverseRating = new int[n];
      for (int i = 0, j = n - 1; i < n && j >= 0; i++, j--) {
    reverseRating[i] = rating[j];
}

         right =  countGreaterToRight(reverseRating);
        left = countSmallerToLeft(reverseRating);
        for(int i=1 ;i<n-1;i++){
            res = res + right[i] * left[i];
        }
        return res ;
    }
    int[] countGreaterToRight(int[] arr){
         int  n=arr.length;
        int[] res = new int[n];
        int[][] sortedArray = new int[n][2];
        for(int i=0 ;i<n;i++){
            sortedArray[i][0] = arr[i];
            sortedArray[i][1] = i ;
        }
        Arrays.sort(sortedArray , (a ,b) -> a[0]-b[0]);
        int[] sortedIndex = new int[n];
        for(int i=0 ;i<n;i++){
            int pos = sortedArray[i][1];
            sortedIndex[pos] = i ;
        }
        int[] ar = new int[n];
        Segment_Tree sgt = new Segment_Tree(n);
        for(int i=n-1 ;i>=0 ;i--){
             int pos = sortedIndex[i];
            if(pos ==n-1){
                sgt.updateSeg(pos ,0,1,0,n-1);
                res[i] = 0;
            }else{
                res[i] = sgt.query(0,n-1,0,pos+1,n-1,ar);
                sgt.updateSeg(pos ,0,1,0,n-1);
            }
        }
        return res ;
    }
    int[] countSmallerToLeft(int[] arr){
        int  n=arr.length;
        int[] res = new int[n];
        int[][] sortedArray = new int[n][2];
        for(int i=0 ;i<n;i++){
            sortedArray[i][0] = arr[i];
            sortedArray[i][1] = i ;
        }
        Arrays.sort(sortedArray , (a ,b) -> a[0]-b[0]);
        int[] sortedIndex = new int[n];
        for(int i=0 ;i<n;i++){
            int pos = sortedArray[i][1];
            sortedIndex[pos] = i ;
        }
        int[] ar = new int[n];
        Segment_Tree sgt = new Segment_Tree(n);
        for(int i=0 ;i<n;i++){
            int pos = sortedIndex[i];
            if(pos ==0){
                sgt.updateSeg(pos ,0,1,0,n-1);
                res[i] = 0;
            }else{
                res[i] = sgt.query(0,n-1,0,0,pos-1,ar);
                sgt.updateSeg(pos ,0,1,0,n-1);
            }
        }
        return res ;
    }
      class Segment_Tree {
        int[] tree;
        int n;

        Segment_Tree(int n) {
            this.n = n;
            tree = new int[4 * Math.max(n, 1)];
        }


        int query(int lo ,int hi , int i , int lo_r ,int hi_r , int[] arr){

            if(hi_r<lo || (lo_r>hi)) return 0;
            if(lo_r<=lo && hi<=hi_r) return tree[i];
            int mid = (lo+hi)/2;
            int left = query(lo ,mid , 2*i+1,lo_r,hi_r ,arr);
            int right = query(mid+1,hi,2*i+2,lo_r,hi_r,arr);
            return left + right ;

        }
        void updateSeg(int ind, int i, int val, int lo, int hi) {
            if (lo == hi && lo == ind) {
                tree[i] = val;
                return;
            }
            if (ind >= lo && ind <= hi) {
                int mid = (lo + hi) / 2;
                updateSeg(ind, i * 2 + 1, val, lo, mid);
                updateSeg(ind, i * 2 + 2, val, mid + 1, hi);
                tree[i] = tree[i * 2 + 1] + tree[i * 2 + 2];
            }
        }
      }
}

      

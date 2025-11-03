class LongestMountain {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int[] dpI = new int[n];
        dpI[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                dpI[i] = 1 + dpI[i - 1];
            } else {
                dpI[i] = 1;
            }
        }
        int[] dpD = new int[n];
        dpD[arr.length-1] = 1;
        for (int i = arr.length-2; i>=0; i--) {
            if (arr[i] > arr[i + 1]) {
                dpD[i] = 1 + dpD[i + 1];
            } else {
                dpD[i] = 1;
            }
        }
        int max = 0;
        for(int i=1;i<arr.length-1;i++){
            if(dpI[i] > 1 && dpD[i] > 1){
                 max = Math.max(max , dpI[i]+dpD[i]-1);
            }
           
        }
        return max ;
    }
}
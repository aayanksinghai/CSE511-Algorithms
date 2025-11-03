class CarsIllegal {
    public int minimumTime(String s) {
        int[] dpLR = new int[s.length()];
        if(s.charAt(0)=='1') dpLR[0] = 1;
        for(int i=1 ;i<s.length();i++){
            if(s.charAt(i)=='1'){
                dpLR[i] = Math.min(dpLR[i-1] + 2 ,i+1 );
            }else{
                dpLR[i] = dpLR[i-1];
            }
        }
         int[] dpRL = new int[s.length()];
         if(s.charAt(s.length()-1)=='1') dpRL[s.length()-1] = 1;
        for(int i=s.length()-2 ;i>=0;i--){
            if(s.charAt(i)=='1'){
                dpRL[i] = Math.min(dpRL[i+1] + 2 ,s.length()-i);
            }else{
                dpRL[i] = dpRL[i+1];
            }
        }
        int min = (int)1e9;
        for(int i=0 ;i<dpRL.length;i++){
            if(i==0){
                min = Math.min(min , dpRL[0]);
            }else if(i==dpRL.length-1){
                min = Math.min(min , dpLR[i]);
            }else{
                min = Math.min(min , dpLR[i] + dpRL[i+1]);
            }
        }
        return min;

    }
}
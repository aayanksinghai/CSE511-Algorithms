class PalindromeSubstrings {
    public int countSubstrings(String s) {
        int count = 0  , n=s.length();
        // Even length
        for(int i = 0 ;i< s.length();i++){
            int lo = i , hi = i ;
            while(lo>=0 && hi<n){
                if(s.charAt(lo)==s.charAt(hi)) count++;
                else break ;
                lo--;
                hi++;
            }
        }
         for(int i = 0 ;i< s.length() -1;i++){
            int lo = i , hi = i+1 ;
            while(lo>=0 && hi<n){
                if(s.charAt(lo)==s.charAt(hi)) count++;
                else break ;
                lo--;
                hi++;
            }
        }
        return count ;

    }
}
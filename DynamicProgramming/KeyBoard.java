class KeyBoard {
    public int minSteps(int n) {
        return solve(1,0 , n);
    }
    int solve(int cp , int pc ,int n){
        if(cp==n) return 0;
        if(cp > n) return (int)1e9;
        if(pc ==0){
            return 2 + solve(cp * 2 , cp ,n);
        }else{
            return Math.min(1 + solve(cp+pc,pc ,n),2+solve(cp*2,cp,n));
        }
    }
}
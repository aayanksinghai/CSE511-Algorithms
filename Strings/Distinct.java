package Strings;

import java.util.HashSet;
import java.util.Scanner;

public class Distinct {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int empNo = sc.nextInt();
        int gridRow = sc.nextInt();
        int gridColumn = sc.nextInt();
        int[][] allocation = new int[gridRow][gridColumn];
        int[] ans = new int[empNo];

        HashSet<Integer> set = new HashSet<>();
        
        for(int i = 0; i < gridRow; i++){
            int j = 0;
            for(; j < gridColumn; j++){
                allocation[i][j] = sc.nextInt();                
            }  
        }

        for(int k = 0; k < gridRow; k++){
            if(!set.contains(allocation[k][1])){
                set.add(allocation[k][1]);
            }
            ans[k] = set.size();
        }
        

        for(int i = 0; i < gridRow; i++){
            System.out.print(ans[i] + " ");
        }
        sc.close();
    }
}

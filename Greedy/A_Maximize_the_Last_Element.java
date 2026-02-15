
// AAYANK SINGHAI (MT2025001)
import java.util.Scanner;

public class A_Maximize_the_Last_Element {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();

            // We only need to check the elements at even indices
            int maxVal = -1;

            for (int i = 0; i < n; i++) {
                int val = sc.nextInt();

                if (i % 2 == 0) {
                    maxVal = Math.max(maxVal, val);
                }
            }
            System.out.println(maxVal);
        }
        sc.close();

    }
}
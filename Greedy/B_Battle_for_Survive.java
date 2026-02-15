
// AAYANK SINGHAI (MT2025001)
import java.util.Scanner;

public class B_Battle_for_Survive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            long[] a = new long[n];
            long sum = 0;

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextLong();
                sum += a[i];
            }

            // The fighter at index n-1 (0-indexed) is a[n-2]
            long result = sum - (2 * a[n - 2]);

            System.out.println(result);
        }
        sc.close();
    }
}

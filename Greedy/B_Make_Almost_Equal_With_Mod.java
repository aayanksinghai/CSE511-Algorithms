
// AAYANK SINGHAI (MT2025001)
import java.util.*;
import java.io.*;

public class B_Make_Almost_Equal_With_Mod {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            long[] arr = new long[n];
            String[] parts = sc.next().trim().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(parts[i]);
            }

            long pow2 = 2;
            while (true) {
                Set<Long> remainders = new HashSet<>();
                for (long val : arr) {
                    remainders.add(val % pow2);
                }
                if (remainders.size() == 2) {
                    sb.append(pow2).append("\n");
                    break;
                }
                pow2 *= 2;
            }
        }

        System.out.print(sb.toString());
        sc.close();
    }
}
// AAYANK SINGHAI (MT2025001)

import java.io.*;
import java.util.*;

public class B_Removals_Game {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();

            int[] a = new int[n];
            int[] b = new int[n];

            for (int i = 0; i < n; i++)
                a[i] = sc.nextInt();

            for (int i = 0; i < n; i++)
                b[i] = sc.nextInt();

            boolean isSame = true, isReverse = true;
            for (int i = 0; i < n; i++) {
                if (a[i] != b[i])
                    isSame = false;
                if (a[i] != b[n - 1 - i])
                    isReverse = false;
                if (!isSame && !isReverse)
                    break; // early exit
            }

            sb.append((isSame || isReverse) ? "Bob\n" : "Alice\n");
        }

        System.out.print(sb.toString());
        sc.close();
    }
}

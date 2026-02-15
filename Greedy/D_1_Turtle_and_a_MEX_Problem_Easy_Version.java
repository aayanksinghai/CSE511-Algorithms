
// AAYANK SINGHAI (MT2025001)
import java.util.Scanner;

public class D_1_Turtle_and_a_MEX_Problem_Easy_Version {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        StringBuilder out = new StringBuilder();
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            long m = sc.nextLong();

            int[] sm = new int[n];
            for (int i = 0; i < n; i++) {
                int len = sc.nextInt();
                boolean[] present = new boolean[len + 2];
                for (int j = 0; j < len; j++) {
                    int x = sc.nextInt();
                    if (x < present.length)
                        present[x] = true;
                }
                int missingCount = 0;
                int secondMissing = 0;
                for (int v = 0; v < present.length; v++) {
                    if (!present[v])
                        missingCount++;
                    if (missingCount == 2) {
                        secondMissing = v;
                        break;
                    }
                }
                sm[i] = secondMissing;
            }

            int lim = 0;
            for (int x : sm)
                if (x > lim)
                    lim = x;

            long stop = Math.min(m, (long) lim);
            long ans = (stop + 1) * (long) lim;

            if (m > lim) {
                long sumM = m * (m + 1) / 2;
                long sumLim = (long) lim * (lim + 1) / 2;
                ans += (sumM - sumLim);
            }

            out.append(ans).append('\n');
        }
        System.out.print(out.toString());
        sc.close();
    }
}

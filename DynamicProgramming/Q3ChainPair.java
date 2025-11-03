
// AAYANK SINGHAI MT2025001
import java.util.Arrays;

class Q3ChainPair {
    public int findLongestChain(int[][] p) {
        Arrays.sort(p, (a, b) -> Integer.compare(a[1], b[1]));

        int c = 0;
        int end = Integer.MIN_VALUE;

        for (int[] pair : p) {
            if (pair[0] > end) {
                c++;
                end = pair[1];
            }
        }
        return c;
    }
}
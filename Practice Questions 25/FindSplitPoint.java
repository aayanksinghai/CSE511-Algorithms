public class FindSplitPoint {

    /**
     * Finds an index k such that the number of 0s in S[0..k] equals the
     * number of 1s in S[k+1..n-1].
     *
     * @param s The input binary string.
     * @return The index k if it exists, otherwise -1.
     */
    public static int findK(String s) {
        if (s == null || s.isEmpty()) {
            return -1;
        }

        // 1. Count the total number of '1's in the string.
        int totalOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                totalOnes++;
            }
        }

        // 2. Calculate the potential index k from the simplified equation.
        int k = totalOnes - 1;
        int n = s.length();

        // 3. Check if k is a valid index.
        if (k >= 0 && k < n) {
            return k;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        String s1 = "01101"; // n=5
        // Total ones = 3.
        // k = 3 - 1 = 2.
        // Check k=2:
        // S[0..2] = "011". Number of 0s = 1.
        // S[3..4] = "01". Number of 1s = 1.
        // They match!
        System.out.println("For string \"" + s1 + "\", k = " + findK(s1)); // Expected: 2

        String s2 = "111"; // n=3
        // Total ones = 3.
        // k = 3 - 1 = 2.
        // Check k=2:
        // S[0..2] = "111". Number of 0s = 0.
        // S[3..2] is empty. Number of 1s = 0.
        // They match!
        System.out.println("For string \"" + s2 + "\", k = " + findK(s2)); // Expected: 2

        String s3 = "0000"; // n=4
        // Total ones = 0.
        // k = 0 - 1 = -1. This is not a valid index.
        System.out.println("For string \"" + s3 + "\", k = " + findK(s3)); // Expected: -1
    }
}
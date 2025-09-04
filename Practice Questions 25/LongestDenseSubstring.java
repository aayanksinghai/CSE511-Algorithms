public class LongestDenseSubstring {

    /**
     * Computes the length of the longest dense substring in linear time.
     *
     * @param s The input binary string.
     * @return The length of the longest dense substring.
     */
    public static int findLongestDenseSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int n = s.length();

        // Step 1: Create the prefix sum array P
        // P[i] stores the sum of the transformed array from 0 to i-1
        int[] P = new int[n + 1];
        P[0] = 0;
        for (int i = 0; i < n; i++) {
            P[i + 1] = P[i] + (s.charAt(i) == '1' ? 1 : -1);
        }

        // Step 2: Create maxFromRight array
        // maxFromRight[i] stores the maximum value in P[i...n]
        int[] maxFromRight = new int[n + 1];
        maxFromRight[n] = P[n];
        for (int i = n - 1; i >= 0; i--) {
            maxFromRight[i] = Math.max(P[i], maxFromRight[i + 1]);
        }

        // Step 3: Two-pointer scan
        int maxLength = 0;
        int i = 0, j = 0;
        while (j <= n) {
            // Check if there is a potential end-point at or after j for the start-point i
            if (maxFromRight[j] > P[i]) {
                // This is a valid candidate pair. The length is j-i.
                // Update maxLength and try to extend the interval by moving j.
                maxLength = Math.max(maxLength, j - i);
                j++;
            } else {
                // P[i] is too high. No point from j onwards will be greater.
                // Move i to the next position. Since we also require j > i, we can
                // set j = i+1 if j was lagging behind.
                i++;
                if (j <= i) {
                    j = i + 1;
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s1 = "10101";
        // P = [0, 1, 0, 1, 0, 1]
        // Longest dense substring is "10101", length 5.
        System.out.println(
                "For string \"" + s1 + "\", length of longest dense substring is: " + findLongestDenseSubstring(s1)); // Expected:
                                                                                                                      // 5

        String s2 = "00110";
        // P = [0, -1, -2, -1, 0, -1]
        // Dense substrings: "1", "1", "11", "011", "110". Longest are "011" and "110",
        // length 3.
        System.out.println(
                "For string \"" + s2 + "\", length of longest dense substring is: " + findLongestDenseSubstring(s2)); // Expected:
                                                                                                                      // 3

        String s3 = "000";
        // No dense substrings
        System.out.println(
                "For string \"" + s3 + "\", length of longest dense substring is: " + findLongestDenseSubstring(s3)); // Expected:
                                                                                                                      // 0
    }
}
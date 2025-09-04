import java.util.HashMap;
import java.util.Map;

public class LongestEqualSubstring {

    /**
     * Computes the length of the longest substring with an equal number of 0s and
     * 1s.
     * The algorithm runs in O(n) time and O(n) space.
     *
     * @param s The input binary string.
     * @return The length of the longest such substring.
     */
    public static int findLongestSubstring(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }

        int n = s.length();
        // The map stores the first occurrence of a prefix sum.
        Map<Integer, Integer> sumIndexMap = new HashMap<>();

        int sum = 0;
        int maxLength = 0;

        // Base case: a sum of 0 is found before the string starts (at index -1).
        sumIndexMap.put(0, -1);

        for (int i = 0; i < n; i++) {
            // Treat '1' as +1 and '0' as -1
            sum += (s.charAt(i) == '1' ? 1 : -1);

            // If this sum has been seen before...
            if (sumIndexMap.containsKey(sum)) {
                // ...it means the substring between the previous and current index has a sum of
                // 0.
                int previousIndex = sumIndexMap.get(sum);
                maxLength = Math.max(maxLength, i - previousIndex);
            } else {
                // If we are seeing this sum for the first time, record its index.
                sumIndexMap.put(sum, i);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s1 = "101010";
        // The longest is the entire string "101010", length 6.
        System.out.println("For string \"" + s1 + "\", the longest length is: " + findLongestSubstring(s1)); // Expected:
                                                                                                             // 6

        String s2 = "11000100011";
        // The longest is "000100011", sum = 4-5=-1. No.
        // Let's trace: 1,2,1,0,-1,0,-1,-2,-3,-2,-1.
        // Longest is "100010001", sum = 3-6=-3. No.
        // The substring "00010001" has 4 0s and 4 1s. Length 8.
        System.out.println("For string \"" + s2 + "\", the longest length is: " + findLongestSubstring(s2)); // Expected:
                                                                                                             // 8
    }
}
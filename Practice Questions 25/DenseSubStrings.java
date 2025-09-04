public class DenseSubStrings {

    /**
     * Main function to compute the number of dense substrings.
     *
     * @param s The input binary string.
     * @return The total number of dense substrings.
     */
    public static long countDenseSubstrings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int n = s.length();

        // Step 1 & 2: Transform and create the prefix sum array
        long[] prefixSum = new long[n + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < n; i++) {
            int val = (s.charAt(i) == '1') ? 1 : -1;
            prefixSum[i + 1] = prefixSum[i] + val;
        }

        // Step 3: Count pairs (i, k) where i < k and prefixSum[i] < prefixSum[k]
        return mergeSortAndCount(prefixSum, 0, n);
    }

    /**
     * Recursive function to sort the array and count "non-inversions".
     *
     * @param arr   The array to be sorted (prefix sums).
     * @param left  The starting index.
     * @param right The ending index.
     * @return The count of pairs (i, k) where i < k and arr[i] < arr[k].
     */
    private static long mergeSortAndCount(long[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }

        long count = 0;
        int mid = left + (right - left) / 2;

        // Count pairs in left and right halves
        count += mergeSortAndCount(arr, left, mid);
        count += mergeSortAndCount(arr, mid + 1, right);

        // Count crossing pairs
        count += mergeAndCount(arr, left, mid, right);

        return count;
    }

    /**
     * Merges two sorted subarrays and counts the crossing pairs.
     */
    private static long mergeAndCount(long[] arr, int left, int mid, int right) {
        long[] temp = new long[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        long crossCount = 0;

        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                // arr[i] is smaller than arr[j] and all subsequent elements in the right half.
                // Number of elements in the right half from j onwards is (right - j + 1).
                crossCount += (right - j + 1);
                temp[k++] = arr[i++];
            } else { // arr[i] >= arr[j]
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // Copy the sorted (merged) elements back to the original array
        System.arraycopy(temp, 0, arr, left, temp.length);

        return crossCount;
    }

    public static void main(String[] args) {
        String s = "10101";
        // Dense substrings are: "1", "101", "10101", "1" (at index 2), "101", "1" (at
        // index 4)
        // Total = 6
        System.out.println("For string \"" + s + "\", the number of dense substrings is: " + countDenseSubstrings(s)); // Expected:
                                                                                                                       // 6

        String s2 = "110";
        // Dense substrings: "1", "1", "11", "110"
        // Total = 4
        System.out.println("For string \"" + s2 + "\", the number of dense substrings is: " + countDenseSubstrings(s2)); // Expected:
                                                                                                                         // 4
    }
}
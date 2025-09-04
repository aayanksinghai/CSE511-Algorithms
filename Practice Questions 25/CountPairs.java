public class CountPairs {

    /**
     * Counts the number of pairs (A[i], A[j]) in a sorted array where A[j] - A[i] >
     * X.
     * The algorithm runs in O(n) time using the two-pointer technique.
     *
     * @param A a sorted array of integers.
     * @param X the positive integer difference to check against.
     * @return the total number of pairs satisfying the condition.
     */
    public static long countPairsWithDifferenceGreaterThanX(int[] A, int X) {
        // Handle edge cases
        if (A == null || A.length < 2) {
            return 0;
        }

        int n = A.length;
        long count = 0;
        int i = 0; // The first pointer
        int j = 0; // The second pointer

        // Iterate through the array with the first pointer 'i'
        for (i = 0; i < n; i++) {
            // Advance the second pointer 'j' until the condition A[j] - A[i] > X is met.
            // 'j' does not reset; it continues from its last position.
            while (j < n && A[j] - A[i] <= X) {
                j++;
            }

            // If j is within the bounds of the array, it means we found the first
            // element that satisfies the condition. All elements from j to n-1
            // will also satisfy the condition for the current A[i].
            if (j < n) {
                count += (n - j);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] A = { 3, 6, 8, 11, 15 };
        int X = 5;
        // Pairs are:
        // (3, 11), (3, 15) -> difference > 5
        // (6, 15) -> difference > 5
        // (8, 15) -> difference > 5
        // Total pairs = 4

        System.out.println("Array: [3, 6, 8, 11, 15], X = 5");
        System.out.println("Number of pairs: " + countPairsWithDifferenceGreaterThanX(A, X)); // Expected: 4

        System.out.println("---");

        int[] A2 = { 1, 2, 3, 4, 5 };
        int X2 = 0;
        // Pairs are (1,3), (1,4), (1,5), (2,4), (2,5), (3,5) for X=1, let's test X=0
        // All 10 pairs have difference > 0.
        System.out.println("Array: [1, 2, 3, 4, 5], X = 0");
        System.out.println("Number of pairs: " + countPairsWithDifferenceGreaterThanX(A2, X2)); // Expected: 10
    }
}
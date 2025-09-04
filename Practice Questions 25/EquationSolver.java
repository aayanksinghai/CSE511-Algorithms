import java.util.HashSet;
import java.util.Set;

public class EquationSolver {

    /**
     * Decides if there exist i, j, k, l such that A[i] - 2*A[j] = A[k] - 3*A[l].
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     *
     * @param A The input array of integers.
     * @return true if such indices exist, false otherwise.
     */
    public static boolean hasSolution(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }

        int n = A.length;
        Set<Integer> valueSet = new HashSet<>();

        // Step 1 & 2: Populate the set with all possible values of A[i] - 2*A[j]
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = A[i] - 2 * A[j];
                valueSet.add(value);
            }
        }

        // Step 3 & 4: Check if any value of A[k] - 3*A[l] exists in the set
        for (int k = 0; k < n; k++) {
            for (int l = 0; l < n; l++) {
                int value = A[k] - 3 * A[l];
                if (valueSet.contains(value)) {
                    // Match found!
                    return true;
                }
            }
        }

        // Step 5: No match found after checking all combinations
        return false;
    }

    public static void main(String[] args) {
        // Example 1: A solution exists
        int[] A1 = { 10, 2, 8, 4 };
        // Let i=0, j=1, k=2, l=3
        // A[0] - 2*A[1] = 10 - 2*2 = 6
        // A[2] - 3*A[3] = 8 - 3*4 = 8 - 12 = -4
        // No match there. Let's find a real one.
        // Let i=0, j=3 -> 10 - 2*4 = 2
        // Let k=2, l=1 -> 8 - 3*2 = 2
        // So, A[0] - 2*A[3] = A[2] - 3*A[1] is a solution.
        System.out.println("Array {10, 2, 8, 4} has a solution: " + hasSolution(A1)); // Expected: true

        // Example 2: No solution exists
        int[] A2 = { 1, 1, 1, 100 };
        System.out.println("Array {1, 1, 1, 100} has a solution: " + hasSolution(A2)); // Expected: false
    }
}
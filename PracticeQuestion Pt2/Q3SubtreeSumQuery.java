import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Solves: "For a given node v, find the sum of values in the subtree rooted at
 * v."
 * Style: Codeforces (using Scanner as requested).
 */
public class Q3SubtreeSumQuery {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read the number of test cases.
        int t = sc.nextInt();
        while (t-- > 0) {
            solve(sc);
        }

        sc.close();
    }

    /**
     * Handles the logic for a single test case.
     */
    private static void solve(Scanner sc) {
        // --- 1. Read Input ---
        int n = sc.nextInt();
        int q = sc.nextInt();

        // --- 2. Build the Tree ---
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // --- 3. Read Node Values ---
        int[] values = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            values[i] = sc.nextInt();
        }

        // --- 4. Pre-computation Step ---
        // This O(n) pass calculates the answer for every node.
        long[] subtreeSums = new long[n + 1]; // Use long to prevent overflow

        // The DFS starts the post-order traversal from the root.
        calculateSubtreeSums(1, 0, adj, values, subtreeSums);

        // --- 5. Answer Queries ---
        // Each query is now an O(1) lookup.
        for (int i = 0; i < q; i++) {
            int targetNodeV = sc.nextInt();
            System.out.println(subtreeSums[targetNodeV]);
        }
    }

    /**
     * Calculates the subtree sum for all nodes using a post-order DFS traversal.
     * The function computes the sum for 'u' after all its children are computed.
     *
     * @return The total sum of the subtree rooted at 'u'.
     */
    private static long calculateSubtreeSums(int u, int p, List<List<Integer>> adj, int[] values, long[] subtreeSums) {
        // Start with the node's own value.
        long currentSubtreeSum = values[u];

        // Recursively call for all children and add their results.
        for (int v : adj.get(u)) {
            if (v != p) {
                currentSubtreeSum += calculateSubtreeSums(v, u, adj, values, subtreeSums);
            }
        }

        // Store the final computed sum for node 'u' and return it to the parent.
        subtreeSums[u] = currentSubtreeSum;
        return currentSubtreeSum;
    }
}
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Solves: "For a given node v, find the maximum value in the subtree rooted at
 * v."
 * Style: Codeforces (using Scanner as requested).
 */
public class Q4SubtreeMaxQuery {

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
        int[] subtreeMaxes = new int[n + 1];

        // The DFS starts the post-order traversal from the root.
        calculateSubtreeMaxes(1, 0, adj, values, subtreeMaxes);

        // --- 5. Answer Queries ---
        // Each query is now an O(1) lookup.
        for (int i = 0; i < q; i++) {
            int targetNodeV = sc.nextInt();
            System.out.println(subtreeMaxes[targetNodeV]);
        }
    }

    /**
     * Calculates the subtree max for all nodes using a post-order DFS traversal.
     * The function computes the max for 'u' after all its children are computed.
     *
     * @return The maximum value in the subtree rooted at 'u'.
     */
    private static int calculateSubtreeMaxes(int u, int p, List<List<Integer>> adj, int[] values, int[] subtreeMaxes) {
        // Start with the node's own value as the current max.
        int currentSubtreeMax = values[u];

        // Recursively call for all children and compare their results with the current
        // max.
        for (int v : adj.get(u)) {
            if (v != p) {
                currentSubtreeMax = Math.max(currentSubtreeMax, calculateSubtreeMaxes(v, u, adj, values, subtreeMaxes));
            }
        }

        // Store the final computed max for node 'u' and return it to the parent.
        subtreeMaxes[u] = currentSubtreeMax;
        return currentSubtreeMax;
    }
}
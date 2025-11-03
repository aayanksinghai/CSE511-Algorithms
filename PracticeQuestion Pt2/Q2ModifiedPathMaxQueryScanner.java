import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Solves: "For a given node v, find the maximum value on the path from the
 * root."
 * Style: Codeforces (using Scanner as requested).
 */
public class Q2ModifiedPathMaxQueryScanner {

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
        int[] pathMaxes = new int[n + 1];
        precompute(1, 0, Integer.MIN_VALUE, adj, values, pathMaxes);

        // --- 5. Answer Queries ---
        // Each query is now an O(1) lookup.
        for (int i = 0; i < q; i++) {
            int targetNodeV = sc.nextInt();
            System.out.println(pathMaxes[targetNodeV]);
        }
    }

    /**
     * Pre-computes the max path value for all nodes in a single O(n) DFS pass.
     *
     * @param u                 The current node being visited.
     * @param p                 The parent of the current node.
     * @param maxOnPathToParent The maximum value on the path from the root to the
     *                          parent 'p'.
     * @param adj               The adjacency list for the tree.
     * @param values            The values of all nodes.
     * @param pathMaxes         The array to store the final results.
     */
    private static void precompute(int u, int p, int maxOnPathToParent, List<List<Integer>> adj, int[] values,
            int[] pathMaxes) {
        int maxForCurrentPath = Math.max(maxOnPathToParent, values[u]);
        pathMaxes[u] = maxForCurrentPath;

        for (int v : adj.get(u)) {
            if (v != p) {
                precompute(v, u, maxForCurrentPath, adj, values, pathMaxes);
            }
        }
    }
}
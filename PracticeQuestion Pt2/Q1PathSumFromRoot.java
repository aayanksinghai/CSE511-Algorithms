import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Solves the "Path Sum from Root" problem for a general tree.
 * The program is structured for competitive programming with a focus on clean
 * code.
 * It reads a tree as an edge list and computes, for each node, the sum of
 * values
 * on the path from the root (node 1) to that node.
 */

// prettier-ignore
/*
 * 1
 * 8
 * 1 2
 * 1 3
 * 2 4
 * 2 5
 * 3 6
 * 5 7
 * 6 8
 * 10 -3 5 8 0 -2 15 1
 */

// Output: 10 7 15 15 7 13 22 14

public class Q1PathSumFromRoot {

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
        // --- 1. Read Input and Build the Tree ---
        int n = sc.nextInt();

        // The tree is represented using an adjacency list.
        // adj.get(i) will contain a list of all neighbors of node i.
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        // Read n-1 edges to build the tree structure.
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Read the integer value for each node.
        int[] values = new int[n + 1]; // Using 1-based indexing
        for (int i = 1; i <= n; i++) {
            values[i] = sc.nextInt();
        }

        // --- 2. Run the Algorithm ---
        long[] pathSums = new long[n + 1]; // To store the final results

        // Start the DFS from the root (node 1).
        // The parent of the root is a dummy node 0.
        // The path sum *to* the root is simply its own value.
        dfs(1, 0, values[1], adj, values, pathSums);

        // --- 3. Print the Output ---
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(pathSums[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    /**
     * Performs a Depth First Search to calculate path sums recursively.
     *
     * @param u              The current node being visited.
     * @param p              The parent of the current node (to avoid traversing
     *                       backward).
     * @param currentPathSum The sum of values on the path from the root to node u.
     * @param adj            The adjacency list representation of the tree.
     * @param values         The array of original values for each node.
     * @param pathSums       The array to store the final computed path sum for each
     *                       node.
     */
    private static void dfs(int u, int p, long currentPathSum, List<List<Integer>> adj, int[] values, long[] pathSums) {
        // Store the calculated path sum for the current node 'u'.
        pathSums[u] = currentPathSum;

        // Traverse all neighbors of 'u'.
        for (int v : adj.get(u)) {
            // If a neighbor 'v' is not the parent 'p', it is a child in this traversal.
            if (v != p) {
                // Recursively call dfs for the child node.
                // The new path sum passed down is the current sum plus the child's own value.
                dfs(v, u, currentPathSum + values[v], adj, values, pathSums);
            }
        }
    }
}
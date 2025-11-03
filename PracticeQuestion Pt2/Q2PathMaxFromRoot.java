import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Solves the "Path Maximum from Root" problem for a general tree.
 * The program computes, for each node, the maximum value on the unique path
 * from the root (node 1) to that node.
 */
public class Q2PathMaxFromRoot {

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
        int[] pathMaxes = new int[n + 1]; // To store the final results

        // Start the DFS from the root (node 1).
        // The max value on the "path to the parent" of the root is initialized
        // to a very small number to ensure the root's own value is chosen.
        dfs(1, 0, Integer.MIN_VALUE, adj, values, pathMaxes);

        // --- 3. Print the Output ---
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(pathMaxes[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    /**
     * Performs a Depth First Search to calculate path maximums recursively.
     *
     * @param u                 The current node being visited.
     * @param p                 The parent of the current node.
     * @param maxOnPathToParent The maximum value on the path from the root to the
     *                          parent 'p'.
     * @param adj               The adjacency list representation of the tree.
     * @param values            The array of original values for each node.
     * @param pathMaxes         The array to store the final computed path maximum
     *                          for each node.
     */
    private static void dfs(int u, int p, int maxOnPathToParent, List<List<Integer>> adj, int[] values,
            int[] pathMaxes) {
        // The max for the current path is the greater of the node's own value
        // and the max value seen on the path to its parent.
        int maxForCurrentPath = Math.max(maxOnPathToParent, values[u]);

        // Store the result for the current node 'u'.
        pathMaxes[u] = maxForCurrentPath;

        // Traverse all neighbors of 'u'.
        for (int v : adj.get(u)) {
            // If a neighbor 'v' is not the parent 'p', it is a child.
            if (v != p) {
                // Recurse for the child node, passing down the new max found so far.
                dfs(v, u, maxForCurrentPath, adj, values, pathMaxes);
            }
        }
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Q8ShortestPathToLeafQuery {

    static final long INF = Long.MAX_VALUE / 2; // Use a large value for infinity
    static int[] values;
    static List<List<Integer>> adj;
    static long[] dp_down, dp_up;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            solve(sc);
        }
        sc.close();
    }

    private static void solve(Scanner sc) {
        int n = sc.nextInt();
        int q = sc.nextInt();

        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            adj.add(new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        values = new int[n + 1];
        for (int i = 1; i <= n; i++)
            values[i] = sc.nextInt();

        dp_down = new long[n + 1];
        dp_up = new long[n + 1];
        Arrays.fill(dp_up, INF); // Initialize up-paths to infinity

        dfs_down(1, 0); // Pass 1
        dfs_up(1, 0); // Pass 2

        for (int i = 0; i < q; i++) {
            int v = sc.nextInt();
            System.out.println(Math.min(dp_down[v], dp_up[v]));
        }
    }

    // Pass 1: Post-order traversal to find min path sum to a leaf downwards
    private static void dfs_down(int u, int p) {
        boolean isLeaf = true;
        long minChildPath = INF;

        for (int v : adj.get(u)) {
            if (v != p) {
                isLeaf = false;
                dfs_down(v, u);
                minChildPath = Math.min(minChildPath, dp_down[v]);
            }
        }

        if (isLeaf) {
            dp_down[u] = values[u];
        } else {
            dp_down[u] = values[u] + minChildPath;
        }
    }

    // Pass 2: Pre-order traversal to find min path sum to a leaf upwards
    private static void dfs_up(int u, int p) {
        // "Two-Minimums" trick for children's dp_down values
        long min1 = INF, min2 = INF;
        for (int v : adj.get(u)) {
            if (v != p) {
                if (dp_down[v] <= min1) {
                    min2 = min1;
                    min1 = dp_down[v];
                } else if (dp_down[v] < min2) {
                    min2 = dp_down[v];
                }
            }
        }

        // Propagate dp_up values to children
        for (int v : adj.get(u)) {
            if (v != p) {
                long best_sibling_path = (dp_down[v] == min1) ? min2 : min1;

                // The path from parent `u` can go further up, or down to a sibling
                long path_from_parent = Math.min(dp_up[u], values[u] + best_sibling_path);

                // The up-path for v starts with its own value, plus the best path from its
                // parent
                dp_up[v] = values[v] + path_from_parent;

                dfs_up(v, u);
            }
        }
    }
}
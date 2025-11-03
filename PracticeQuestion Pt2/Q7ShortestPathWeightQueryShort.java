import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q7ShortestPathWeightQueryShort {

    static final long INF = Long.MAX_VALUE / 2;
    static int[] values;
    static List<List<Integer>> adj;
    static long[] dp_down, final_ans;

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
        final_ans = new long[n + 1];

        dfs_down(1, 0);
        // The 'up' value for the root is INF since it has no path upwards to another
        // node
        dfs_up(1, 0, INF);

        for (int i = 0; i < q; i++) {
            int v = sc.nextInt();
            System.out.println(final_ans[v]);
        }
    }

    // Pass 1: Post-order traversal to find min path sum downwards from each node
    private static void dfs_down(int u, int p) {
        long minChildPath = 0;
        for (int v : adj.get(u)) {
            if (v != p) {
                dfs_down(v, u);
                minChildPath = Math.min(minChildPath, dp_down[v]);
            }
        }
        dp_down[u] = values[u] + minChildPath;
    }

    // Pass 2: Pre-order traversal to find min path sum upwards from each node
    private static void dfs_up(int u, int p, long up_val_from_parent) {
        // "Two-Minimums" trick to find the best and second-best downward paths from
        // children
        long min1 = 0, min2 = 0;
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

        // Calculate the final answer for node u
        long ans_down = values[u] + min1;
        final_ans[u] = Math.min(ans_down, up_val_from_parent);

        // Handle edge cases for leaves and the root
        boolean isLeaf = true;
        for (int v : adj.get(u)) {
            if (v != p) {
                isLeaf = false;
                break;
            }
        }
        if (isLeaf)
            final_ans[u] = up_val_from_parent;
        if (u == 1)
            final_ans[u] = ans_down;

        // Propagate values to children for their 'up' calculation
        for (int v : adj.get(u)) {
            if (v != p) {
                long sibling_min = (dp_down[v] == min1) ? min2 : min1;

                // *** THIS IS THE CORRECTED LINE ***
                long min_path_from_u = Math.min(up_val_from_parent, values[u] + sibling_min);

                long up_val_for_v = values[v] + min_path_from_u;
                dfs_up(v, u, up_val_for_v);
            }
        }
    }
}
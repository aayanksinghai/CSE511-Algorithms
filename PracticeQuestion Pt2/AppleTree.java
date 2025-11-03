import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppleTree {

    static List<Integer>[] children;
    static long[] leaves;

    static void buildTree(int u, int parent, List<Integer>[] adj) {
        for (int v : adj[u]) {
            if (v != parent) {
                children[u].add(v);
                buildTree(v, u, adj);
            }
        }
    }

    static void countLeaves(int u) {
        if (children[u].isEmpty()) {
            leaves[u] = 1;
            return;
        }

        long sum = 0;
        for (int v : children[u]) {
            countLeaves(v);
            sum += leaves[v];
        }
        leaves[u] = sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();

        children = new ArrayList[n + 1];
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            children[i] = new ArrayList<>();
            adj[i] = new ArrayList<>();
        }
        leaves = new long[n + 1];

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        buildTree(1, 0, adj);
        countLeaves(1);

        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            long ans = leaves[x] * leaves[y];
            out.println(ans);
        }

        out.flush();
        sc.close();
    }
}

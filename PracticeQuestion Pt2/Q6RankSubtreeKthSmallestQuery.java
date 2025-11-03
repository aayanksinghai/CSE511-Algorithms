import java.util.*;

/**
 * Solves: "For a given node v and integer k, find the k-th smallest value
 * in the subtree rooted at v."
 * Technique: Tree Flattening (Euler Tour) + Persistent Segment Tree.
 */
public class Q6RankSubtreeKthSmallestQuery {

    // Node for the Persistent Segment Tree
    static class Node {
        Node left, right;
        int count;

        Node(int count) {
            this.count = count;
        }

        Node(Node left, Node right) {
            this.left = left;
            this.right = right;
            this.count = (left != null ? left.count : 0) + (right != null ? right.count : 0);
        }
    }

    static int timer;
    static int[] values, entryTime, exitTime, flatValues;
    static List<List<Integer>> adj;
    static Node[] segmentTreeRoots;
    static List<Integer> distinctSortedValues;
    static Map<Integer, Integer> valueToCompressed;

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
        Set<Integer> distinctValues = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            values[i] = sc.nextInt();
            distinctValues.add(values[i]);
        }

        // --- 1. Coordinate Compression ---
        distinctSortedValues = new ArrayList<>(distinctValues);
        Collections.sort(distinctSortedValues);
        valueToCompressed = new HashMap<>();
        for (int i = 0; i < distinctSortedValues.size(); i++) {
            valueToCompressed.put(distinctSortedValues.get(i), i);
        }

        // --- 2. Tree Flattening ---
        timer = 0;
        entryTime = new int[n + 1];
        exitTime = new int[n + 1];
        flatValues = new int[n + 1];
        flattenDfs(1, 0);

        // --- 3. Build Persistent Segment Tree on Flattened Array ---
        int m = distinctSortedValues.size();
        segmentTreeRoots = new Node[n + 1];
        segmentTreeRoots[0] = buildEmpty(0, m - 1);

        for (int i = 1; i <= n; i++) {
            int compressedVal = valueToCompressed.get(flatValues[i]);
            segmentTreeRoots[i] = update(segmentTreeRoots[i - 1], 0, m - 1, compressedVal, 1);
        }

        // --- 4. Answer Queries ---
        for (int i = 0; i < q; i++) {
            int v = sc.nextInt();
            int k = sc.nextInt();

            int l = entryTime[v];
            int r = exitTime[v];

            Node rootL_minus_1 = segmentTreeRoots[l - 1];
            Node rootR = segmentTreeRoots[r];

            int compressedIndex = queryKth(rootL_minus_1, rootR, k, 0, m - 1);

            if (compressedIndex == -1) {
                System.out.println("Invalid k");
            } else {
                System.out.println(distinctSortedValues.get(compressedIndex));
            }
        }
    }

    // DFS to flatten the tree into an array
    private static void flattenDfs(int u, int p) {
        timer++;
        entryTime[u] = timer;
        flatValues[timer] = values[u];

        for (int v : adj.get(u)) {
            if (v != p) {
                flattenDfs(v, u);
            }
        }
        exitTime[u] = timer;
    }

    // Builds an empty segment tree
    private static Node buildEmpty(int l, int r) {
        if (l == r)
            return new Node(0);
        int mid = l + (r - l) / 2;
        return new Node(buildEmpty(l, mid), buildEmpty(mid + 1, r));
    }

    // Updates the segment tree, creating a new version
    private static Node update(Node node, int l, int r, int idx, int val) {
        if (l == r)
            return new Node(node.count + val);
        int mid = l + (r - l) / 2;
        Node newLeft = node.left, newRight = node.right;
        if (idx <= mid) {
            newLeft = update(node.left, l, mid, idx, val);
        } else {
            newRight = update(node.right, mid + 1, r, idx, val);
        }
        return new Node(newLeft, newRight);
    }

    // Queries for the k-th element in the range represented by two segment tree
    // versions
    private static int queryKth(Node nodeL, Node nodeR, int k, int l, int r) {
        int totalInRange = nodeR.count - nodeL.count;
        if (totalInRange < k || k <= 0)
            return -1;
        if (l == r)
            return l;

        int mid = l + (r - l) / 2;
        int leftCount = nodeR.left.count - nodeL.left.count;

        if (k <= leftCount) {
            return queryKth(nodeL.left, nodeR.left, k, l, mid);
        } else {
            return queryKth(nodeL.right, nodeR.right, k - leftCount, mid + 1, r);
        }
    }
}
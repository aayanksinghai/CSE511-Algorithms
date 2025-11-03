import java.util.*;

/**
 * Solves: "For a given node v and integer k, find the k-th smallest value
 * on the path from the root to v."
 * Technique: Persistent Segment Tree on a Tree.
 */
public class Q5RankPathKthSmallestQuery {

    // Node for the Persistent Segment Tree
    static class Node {
        Node left, right;
        int count;

        Node(int count) {
            this.count = count;
            this.left = null;
            this.right = null;
        }

        Node(Node left, Node right) {
            this.left = left;
            this.right = right;
            this.count = (left != null ? left.count : 0) + (right != null ? right.count : 0);
        }
    }

    static int[] values;
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

        // --- 2. Pre-computation with Persistent Segment Tree ---
        int m = distinctSortedValues.size();
        segmentTreeRoots = new Node[n + 1];
        segmentTreeRoots[0] = buildEmpty(0, m - 1); // Empty tree for parent of root

        buildPersistentST(1, 0, segmentTreeRoots[0]);

        // --- 3. Answer Queries ---
        for (int i = 0; i < q; i++) {
            int v = sc.nextInt();
            int k = sc.nextInt();
            int compressedIndex = queryKth(segmentTreeRoots[v], k, 0, m - 1);

            if (compressedIndex == -1) {
                // This case happens if k is larger than the path length
                System.out.println("Invalid k");
            } else {
                System.out.println(distinctSortedValues.get(compressedIndex));
            }
        }
    }

    // DFS to build the persistent segment tree structure over the actual tree
    private static void buildPersistentST(int u, int p, Node parentSTNode) {
        int compressedVal = valueToCompressed.get(values[u]);
        segmentTreeRoots[u] = update(parentSTNode, 0, distinctSortedValues.size() - 1, compressedVal, 1);

        for (int v : adj.get(u)) {
            if (v != p) {
                buildPersistentST(v, u, segmentTreeRoots[u]);
            }
        }
    }

    // Builds an empty segment tree
    private static Node buildEmpty(int l, int r) {
        if (l == r)
            return new Node(0);
        int mid = l + (r - l) / 2;
        return new Node(buildEmpty(l, mid), buildEmpty(mid + 1, r));
    }

    // Updates the segment tree, creating a new version (persistence)
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

    // Queries the segment tree to find the k-th smallest element
    private static int queryKth(Node node, int k, int l, int r) {
        if (node.count < k || k <= 0)
            return -1;
        if (l == r)
            return l;

        int mid = l + (r - l) / 2;
        int leftCount = node.left.count;

        if (k <= leftCount) {
            return queryKth(node.left, k, l, mid);
        } else {
            return queryKth(node.right, k - leftCount, mid + 1, r);
        }
    }
}
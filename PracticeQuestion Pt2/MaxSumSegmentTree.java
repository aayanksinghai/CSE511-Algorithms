import java.io.PrintWriter;
import java.util.Scanner;

public class MaxSumSegmentTree {

    // A helper class to represent a node in the segment tree.
    // Each node stores four values to solve the maximum subsegment sum problem.
    static class Node {
        long sum; // The sum of all elements in the segment
        long maxSum; // The maximum sum of any contiguous subsegment in the range
        long prefSum; // The maximum sum of a prefix of the segment
        long suffSum; // The maximum sum of a suffix of the segment

        Node(long sum, long maxSum, long prefSum, long suffSum) {
            this.sum = sum;
            this.maxSum = maxSum;
            this.prefSum = prefSum;
            this.suffSum = suffSum;
        }
    }

    static long[] a; // The input array
    static Node[] tree; // The segment tree array

    /**
     * Merges the information from two child nodes (left and right) into a parent
     * node.
     * This is the core logic of the segment tree for this problem.
     *
     * @param left  The left child node.
     * @param right The right child node.
     * @return A new Node representing the merged segment.
     */
    static Node merge(Node left, Node right) {
        // The total sum is just the sum of the children.
        long sum = left.sum + right.sum;

        // The best prefix of the parent is either the best prefix of the left child,
        // or the entire left child's sum plus the best prefix of the right child.
        long prefSum = Math.max(left.prefSum, left.sum + right.prefSum);

        // The best suffix of the parent is either the best suffix of the right child,
        // or the entire right child's sum plus the best suffix of the left child.
        long suffSum = Math.max(right.suffSum, right.sum + left.suffSum);

        // The maximum subsegment sum in the parent is the maximum of three cases:
        // 1. The max sum is entirely within the left child.
        // 2. The max sum is entirely within the right child.
        // 3. The max sum crosses the midpoint, which would be the best suffix of the
        // left child combined with the best prefix of the right child.
        long maxSum = Math.max(left.maxSum, Math.max(right.maxSum, left.suffSum + right.prefSum));

        return new Node(sum, maxSum, prefSum, suffSum);
    }

    /**
     * Creates a node for a leaf in the segment tree.
     *
     * @param val The value of the single element in the segment.
     * @return A new Node representing the leaf.
     */
    static Node makeNode(long val) {
        long maxSum = Math.max(0, val); // A single element can be a subsegment, or we can take an empty segment (sum
                                        // 0).
        return new Node(val, maxSum, maxSum, maxSum);
    }

    /**
     * Recursively builds the segment tree from the input array.
     *
     * @param v  The index of the current node in the `tree` array (1-based).
     * @param tl The left boundary of the current segment (inclusive).
     * @param tr The right boundary of the current segment (inclusive).
     */
    static void build(int v, int tl, int tr) {
        if (tl == tr) {
            // Leaf node: represents a single element of the array.
            tree[v] = makeNode(a[tl]);
        } else {
            // Recursive step: build left and right children, then merge them.
            int tm = tl + (tr - tl) / 2;
            build(v * 2, tl, tm);
            build(v * 2 + 1, tm + 1, tr);
            tree[v] = merge(tree[v * 2], tree[v * 2 + 1]);
        }
    }

    /**
     * Updates the value at a specific position in the array and propagates
     * the changes up the segment tree.
     *
     * @param v   The index of the current node in the `tree` array (1-based).
     * @param tl  The left boundary of the current segment.
     * @param tr  The right boundary of the current segment.
     * @param pos The position in the original array to update (0-based).
     * @param val The new value.
     */
    static void update(int v, int tl, int tr, int pos, long val) {
        if (tl == tr) {
            // Leaf node: update the value.
            tree[v] = makeNode(val);
        } else {
            int tm = tl + (tr - tl) / 2;
            if (pos <= tm) {
                // Update is in the left child.
                update(v * 2, tl, tm, pos, val);
            } else {
                // Update is in the right child.
                update(v * 2 + 1, tm + 1, tr, pos, val);
            }
            // After updating a child, re-merge to update the parent.
            tree[v] = merge(tree[v * 2], tree[v * 2 + 1]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int m = sc.nextInt();

        a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
        }

        // The size of the segment tree is typically 4 * n.
        tree = new Node[4 * n];
        build(1, 0, n - 1);

        // Print the initial maximum subsegment sum.
        out.println(tree[1].maxSum);

        for (int i = 0; i < m; i++) {
            int idx = sc.nextInt();
            long val = sc.nextLong();
            update(1, 0, n - 1, idx, val);
            // Print the maximum sum after each update.
            out.println(tree[1].maxSum);
        }

        out.flush();
        sc.close();
    }
}

import java.util.Scanner;

public class C_Number_of_Minimums_on_a_Segment {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();
        SegmentTree segmentTree = new SegmentTree(n);
        segmentTree.build(0, 0, n - 1, a);

        while (m-- > 0) {
            int type = sc.nextInt();
            if (type == 1) {
                int i = sc.nextInt();
                int v = sc.nextInt();
                segmentTree.update(0, 0, n - 1, i, v);
                a[i] = v;

            } else {
                int l = sc.nextInt();
                int r = sc.nextInt();
                SegmentTree.Pair result = segmentTree.query(0, 0, n - 1, l, r - 1);
                System.out.println(result.min + " " + result.count);
            }
        }

        sc.close();
    }

}

class SegmentTree {

    static class Pair {
        long min;
        int count;

        Pair(long min, int count) {
            this.min = min;
            this.count = count;
        }
    }

    private Pair[] seg;

    public SegmentTree(int n) {
        seg = new Pair[4 * n + 1];
    }

    private Pair combine(Pair left, Pair right) {
        if (left.min < right.min) {
            return left;
        }
        if (right.min < left.min) {
            return right;
        }
        // If minimums are equal, sum their counts.
        return new Pair(left.min, left.count + right.count);
    }

    public void build(int ind, int low, int high, int[] arr) {
        if (low == high) {
            seg[ind] = new Pair(arr[low], 1);
            return;
        }

        int mid = low + (high - low) / 2;
        build(2 * ind + 1, low, mid, arr);
        build(2 * ind + 2, mid + 1, high, arr);
        seg[ind] = combine(seg[2 * ind + 1], seg[2 * ind + 2]);
    }

    public Pair query(int ind, int low, int high, int l, int r) {
        // No Overlap
        // l r low high or low high l r
        if (r < low || high < l)
            return new Pair(Long.MAX_VALUE, 0);

        // Complete Overlap
        // [l low high r]
        if (low >= l && high <= r)
            return seg[ind];

        int mid = (low + high) / 2;
        Pair left = query(2 * ind + 1, low, mid, l, r);
        Pair right = query(2 * ind + 2, mid + 1, high, l, r);
        return combine(left, right);
    }

    public void update(int ind, int low, int high, int i, int val) {
        if (low == high) {
            seg[ind] = new Pair(val, 1);
            return;
        }

        int mid = low + (high - low) / 2;
        if (i <= mid)
            update(2 * ind + 1, low, mid, i, val);
        else
            update(2 * ind + 2, mid + 1, high, i, val);

        seg[ind] = combine(seg[2 * ind + 1], seg[2 * ind + 2]);
    }
}

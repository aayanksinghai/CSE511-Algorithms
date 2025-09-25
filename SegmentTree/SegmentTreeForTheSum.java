import java.util.Scanner;

public class SegmentTreeForTheSum {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();
        SegmentTree segmentTree1 = new SegmentTree(n);
        segmentTree1.build(0, 0, n - 1, a);

        while (m-- > 0) {
            int type = sc.nextInt();
            if (type == 1) {
                int i = sc.nextInt();
                int v = sc.nextInt();
                segmentTree1.update(0, 0, n - 1, i, v);
                a[i] = v;
            } else {
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(segmentTree1.getSum(0, 0, n - 1, l, r - 1));
            }
        }
        sc.close();

    }
}

class SegmentTree {
    public long[] seg;

    public SegmentTree(int n) {
        seg = new long[4 * n + 1];
    }

    public void build(int ind, int low, int high, int[] arr) {
        if (low == high) {
            seg[ind] = arr[low];
            return;
        }

        int mid = (low + high) / 2;
        build(2 * ind + 1, low, mid, arr);
        build(2 * ind + 2, mid + 1, high, arr);
        seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];
    }

    public long getSum(int ind, int low, int high, int l, int r) {
        if (r < low || high < l)
            return 0;
        if (low >= l && high <= r)
            return seg[ind];

        int mid = low + (high - low) / 2;
        long left = getSum(2 * ind + 1, low, mid, l, r);
        long right = getSum(2 * ind + 2, mid + 1, high, l, r);
        return left + right;
    }

    public void update(int ind, int low, int high, int i, int val) {
        if (low == high) {
            seg[ind] = val;
            return;
        }

        int mid = low + (high - low) / 2;
        if (i <= mid)
            update(2 * ind + 1, low, mid, i, val);
        else
            update(2 * ind + 2, mid + 1, high, i, val);

        seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];
    }
}

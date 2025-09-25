import java.util.Scanner;

public class SegmentTreeTemplate {

    public static void main(String[] args) {

        // Test Case
        /*
         * Type 1
         * 6
         * 2 1 0 5 4 3
         * 6
         * 4 6 2 3 1 8
         * 1
         * 1
         * 0 2
         * 3 4
         * 0
         */
        Scanner sc = new Scanner(System.in);

        int n1 = sc.nextInt();
        int[] arr1 = new int[n1];
        for (int i = 0; i < n1; i++)
            arr1[i] = sc.nextInt();
        SegmentTree segmentTree1 = new SegmentTree(n1);
        segmentTree1.build(0, 0, n1 - 1, arr1);

        int n2 = sc.nextInt();
        int[] arr2 = new int[n2];
        for (int j = 0; j < n2; j++)
            arr2[j] = sc.nextInt();
        SegmentTree segmentTree2 = new SegmentTree(n2);
        segmentTree2.build(0, 0, n2 - 1, arr2);

        int q = sc.nextInt();
        while (q-- > 0) {
            int type = sc.nextInt();
            if (type == 1) {
                int l1 = sc.nextInt();
                int r1 = sc.nextInt();
                int l2 = sc.nextInt();
                int r2 = sc.nextInt();
                int min1 = segmentTree1.query(0, 0, n1 - 1, l1, r1);
                int min2 = segmentTree2.query(0, 0, n2 - 1, l2, r2);
                System.out.println(Math.min(min1, min2));

            } else {
                int arrNo = sc.nextInt();
                int i = sc.nextInt();
                int val = sc.nextInt();

                if (arrNo == 1) {
                    segmentTree1.update(0, 0, n1 - 1, i, val);
                    arr1[i] = val;
                } else {
                    segmentTree2.update(0, 0, n2 - 1, i, val);
                    arr2[i] = val;
                }
            }

        }
        sc.close();
    }

}

class SegmentTree {
    public int[] seg;

    public SegmentTree(int n) {
        seg = new int[4 * n + 1];
    }

    public void build(int ind, int low, int high, int[] arr) {
        if (low == high) {
            seg[ind] = arr[low];
            return;
        }

        int mid = (low + high) / 2;
        build(2 * ind + 1, low, mid, arr);
        build(2 * ind + 2, mid + 1, high, arr);
        seg[ind] = Math.min(seg[2 * ind + 1], seg[2 * ind + 2]);
    }

    public int query(int ind, int low, int high, int l, int r) {
        // No Overlap
        // l r low high or low high l r
        if (r < low || high < l)
            return Integer.MAX_VALUE;

        // Complete Overlap
        // [l low high r]
        if (low >= l && high <= r)
            return seg[ind];

        int mid = (low + high) / 2;
        int left = query(2 * ind + 1, low, mid, l, r);
        int right = query(2 * ind + 2, mid + 1, high, l, r);
        return Math.min(left, right);
    }

    public void update(int ind, int low, int high, int i, int val) {
        if (low == high) {
            seg[ind] = val;
            return;
        }

        int mid = (low + high) / 2;
        if (i <= mid)
            update(2 * ind + 1, low, mid, i, val);
        else
            update(2 * ind + 2, mid + 1, high, i, val);

        seg[ind] = Math.min(seg[2 * ind + 1], seg[2 * ind + 2]);
    }
}

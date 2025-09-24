import java.util.Scanner;

public class SegmentTree {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int[] seg = new int[4 * n];
        build(0, 0, n - 1, arr, seg);

        int q = sc.nextInt();
        while (q-- > 0) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            System.out.println(query(0, 0, n - 1, l, r, seg));
        }
        sc.close();

    }

    private static void build(int ind, int low, int high, int[] arr, int[] seg) {
        if (low == high) {
            seg[ind] = arr[low];
            return;
        }

        int mid = (low + high) / 2;
        build(2 * ind + 1, low, mid, arr, seg);
        build(2 * ind + 2, mid + 1, high, arr, seg);
        seg[ind] = Math.min(seg[2 * ind + 1], seg[2 * ind + 2]);
    }

    private static int query(int ind, int low, int high, int l, int r, int[] seg) {
        // No Overlap
        // l r low high or low high l r
        if (r < low || high < l)
            return Integer.MAX_VALUE;

        // Complete Overlap
        // [l low high r]
        if (low >= l && high <= r)
            return seg[ind];

        int mid = (low + high) / 2;
        int left = query(2 * ind + 1, low, mid, l, r, seg);
        int right = query(2 * ind + 2, mid + 1, high, l, r, seg);
        return Math.min(left, right);
    }

}
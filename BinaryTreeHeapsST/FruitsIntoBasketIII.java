/*
 * Return the number of fruit types that remain unplaced after all possible allocations are made.
 * 
 * Author: AAYANK SINGHAI (MT2025001)
 * Link: https://leetcode.com/problems/fruits-into-baskets-iii/description/?envType=problem-list-v2&envId=segment-tree
 */

public class FruitsIntoBasketIII {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        if (baskets == null || baskets.length == 0) {
            return fruits.length;
        }

        SegmentTree st = new SegmentTree(baskets);
        int unplacedCount = 0;

        for (int fruitQuantity : fruits) {
            int basketIndex = st.findLeftmost(fruitQuantity);

            if (basketIndex != -1) {
                st.update(basketIndex, -1);
            } else {
                unplacedCount++;
            }
        }
        return unplacedCount;
    }
}

class SegmentTree {
    private int[] tree;
    private int n;

    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;
        build(arr, leftChild, start, mid);
        build(arr, rightChild, mid + 1, end);
        tree[node] = Math.max(tree[leftChild], tree[rightChild]);
    }

    public void update(int index, int value) {
        update(0, 0, n - 1, index, value);
    }

    private void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = value;
            return;
        }
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;
        if (index <= mid) {
            update(leftChild, start, mid, index, value);
        } else {
            update(rightChild, mid + 1, end, index, value);
        }
        tree[node] = Math.max(tree[leftChild], tree[rightChild]);
    }

    public int findLeftmost(int targetCapacity) {
        if (tree[0] < targetCapacity) {
            return -1;
        }
        return findLeftmost(0, 0, n - 1, targetCapacity);
    }

    private int findLeftmost(int node, int start, int end, int targetCapacity) {
        if (start == end) {
            return start;
        }

        int mid = start + (end - start) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        if (tree[leftChild] >= targetCapacity) {
            return findLeftmost(leftChild, start, mid, targetCapacity);
        } else {
            return findLeftmost(rightChild, mid + 1, end, targetCapacity);
        }
    }
}

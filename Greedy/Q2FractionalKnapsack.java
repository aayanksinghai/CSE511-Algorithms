
// AAYANK SINGHAI (MT2025001)
import java.util.Arrays;

class Solution {

    private class Item {
        int val, wt;
        double ratio;

        Item(int v, int w) {
            this.val = v;
            this.wt = w;
            this.ratio = (double) v / (double) w;
        }
    }

    public double fractionalKnapsack(int[] val, int[] wt, int capacity) {
        int n = val.length;
        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            items[i] = new Item(val[i], wt[i]);
        }

        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalValue = 0.0;

        for (Item item : items) {
            if (item.wt <= capacity) {
                totalValue += item.val;
                capacity -= item.wt;
            } else {
                totalValue += item.ratio * capacity;
                capacity = 0;
                break;
            }
        }

        return totalValue;
    }
}
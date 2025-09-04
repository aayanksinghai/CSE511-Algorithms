public class StockProfitMinK {

    /**
     * Computes the maximum profit where the stock must be sold at least k days
     * after it is bought.
     * The algorithm runs in O(n) time and O(1) space.
     *
     * @param prices An array of stock prices on consecutive days.
     * @param k      The minimum number of days between buying and selling.
     * @return The maximum profit that can be achieved.
     */
    public static int maxProfit(int[] prices, int k) {
        int n = prices.length;

        // A transaction requires at least k+1 days.
        if (prices == null || n <= k) {
            return 0;
        }

        int maxProfit = 0;

        // minBuyPrice tracks the minimum price in the valid buy-window.
        // For the first possible sell day (day k), the only valid buy day is day 0.
        int minBuyPrice = prices[0];

        // Iterate through all possible sell days, starting from the earliest possible
        // one.
        for (int i = k; i < n; i++) {
            // As we move to sell day 'i', the day 'i-k' becomes a new valid buy day.
            // Update our minimum buy price by considering this new day.
            minBuyPrice = Math.min(minBuyPrice, prices[i - k]);

            // Calculate the potential profit if we sell today.
            int profit = prices[i] - minBuyPrice;

            // Update the overall maximum profit.
            maxProfit = Math.max(maxProfit, profit);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        int k = 2; // Must wait at least 2 days.
        // Best trade: Buy at 1 (day 1), sell at 6 (day 4).
        // The hold duration is 4-1 = 3 days, which is >= k. Profit = 5.
        System.out.println("For prices [7, 1, 5, 3, 6, 4] and k=2, max profit is: " + maxProfit(prices, k)); // Expected:
                                                                                                             // 5

        int[] prices2 = { 10, 2, 8, 1, 12 };
        int k2 = 3; // Must wait at least 3 days.
        // Best trade: Buy at 2 (day 1), sell at 12 (day 4).
        // Hold duration is 4-1 = 3 days, which is >= k. Profit = 10.
        System.out.println("For prices [10, 2, 8, 1, 12] and k=3, max profit is: " + maxProfit(prices2, k2)); // Expected:
                                                                                                              // 10
    }
}

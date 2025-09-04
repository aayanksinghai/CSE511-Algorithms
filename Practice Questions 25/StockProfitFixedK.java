public class StockProfitFixedK {

    /**
     * Computes the maximum profit where the stock must be sold exactly k days after
     * it is bought.
     * The algorithm runs in O(n) time and O(1) space.
     *
     * @param prices An array of stock prices on consecutive days.
     * @param k      The fixed number of days between buying and selling.
     * @return The maximum profit that can be achieved.
     */
    public static int maxProfit(int[] prices, int k) {
        int n = prices.length;

        // If a transaction is not possible, profit is 0.
        if (prices == null || n <= k) {
            return 0;
        }

        int maxProfit = 0;

        // Iterate through all possible buy days.
        // The last possible buy day is n-1-k so that the sell day n-1 is valid.
        for (int i = 0; i <= n - 1 - k; i++) {
            // The sell day is fixed at i + k.
            int profit = prices[i + k] - prices[i];

            // Update the maximum profit found so far.
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        int k = 2;
        // Possible trades:
        // Buy at 7, sell at 5 -> Profit = -2
        // Buy at 1, sell at 3 -> Profit = 2
        // Buy at 5, sell at 6 -> Profit = 1
        // Buy at 3, sell at 4 -> Profit = 1
        // Max profit is 2.
        System.out.println("For prices [7, 1, 5, 3, 6, 4] and k=2, max profit is: " + maxProfit(prices, k)); // Expected:
                                                                                                             // 2

        int k2 = 4;
        // Possible trades:
        // Buy at 7, sell at 6 -> Profit = -1
        // Buy at 1, sell at 4 -> Profit = 3
        // Max profit is 3.
        System.out.println("For prices [7, 1, 5, 3, 6, 4] and k=4, max profit is: " + maxProfit(prices, k2)); // Expected:
                                                                                                              // 3
    }
}
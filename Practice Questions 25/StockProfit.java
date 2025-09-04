public class StockProfit {

    /**
     * Computes the maximum profit from a single buy and sell transaction.
     * The algorithm runs in O(n) time and O(1) space.
     *
     * @param prices An array of stock prices on consecutive days.
     * @return The maximum profit that can be achieved.
     */
    public static int maxProfit(int[] prices) {
        // If we can't make at least one transaction, profit is 0.
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int currentPrice : prices) {
            // Check for a new minimum price (a better buying opportunity)
            if (currentPrice < minPrice) {
                minPrice = currentPrice;
            }
            // Otherwise, check if selling today gives a better profit
            else if (currentPrice - minPrice > maxProfit) {
                maxProfit = currentPrice - minPrice;
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices1 = { 7, 1, 5, 3, 6, 4 };
        // Buy at 1 and sell at 6 for a profit of 5.
        System.out.println("For prices [7, 1, 5, 3, 6, 4], max profit is: " + maxProfit(prices1)); // Expected: 5

        int[] prices2 = { 7, 6, 4, 3, 1 };
        // Prices are always decreasing, so no profitable trade is possible.
        System.out.println("For prices [7, 6, 4, 3, 1], max profit is: " + maxProfit(prices2)); // Expected: 0
    }
}
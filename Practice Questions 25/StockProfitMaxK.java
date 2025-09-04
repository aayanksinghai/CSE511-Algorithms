import java.util.ArrayDeque;
import java.util.Deque;

public class StockProfitMaxK {

    /**
     * Computes max profit where stock is sold at most k days after buying.
     * This uses a sliding window minimum approach with a deque.
     *
     * @param prices An array of stock prices.
     * @param k      The maximum number of days to hold the stock.
     * @return The maximum profit.
     */
    public static int maxProfit(int[] prices, int k) {
        if (prices == null || prices.length < 2 || k <= 0) {
            return 0;
        }

        int maxProfit = 0;
        // The deque stores indices of potential buy days.
        Deque<Integer> window = new ArrayDeque<>();

        for (int i = 0; i < prices.length; i++) {
            // 1. Remove indices from the front that are outside the k-day window.
            if (!window.isEmpty() && window.peekFirst() <= i - k) {
                window.pollFirst();
            }

            // 2. Remove indices from the back that correspond to prices
            // greater than or equal to the current price.
            while (!window.isEmpty() && prices[window.peekLast()] >= prices[i]) {
                window.pollLast();
            }

            // 3. Add the current day's index as a potential buy day.
            window.offerLast(i);

            // 4. Calculate profit. The best buy day in the window is at the front.
            // The current day 'i' is the sell day.
            int profit = prices[i] - prices[window.peekFirst()];
            maxProfit = Math.max(maxProfit, profit);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = { 8, 1, 7, 2, 6, 4 };
        int k = 3;
        // Best trade: Buy at 1 (day 1), sell at 7 (day 2).
        // The hold duration is 2-1 = 1 day, which is <= k. Profit = 6.
        System.out.println("For prices [8, 1, 7, 2, 6, 4] and k=3, max profit is: " + maxProfit(prices, k)); // Expected:
                                                                                                             // 6

        int[] prices2 = { 1, 5, 2, 8, 3 };
        int k2 = 2;
        // Best trade: Buy at 1 (day 0), sell at 5 (day 1).
        // Hold duration is 1-0 = 1 day, which is <= k. Profit = 4.
        // Another trade: Buy at 2 (day 2), sell at 8 (day 3). Profit = 6.
        System.out.println("For prices [1, 5, 2, 8, 3] and k=2, max profit is: " + maxProfit(prices2, k2)); // Expected:
                                                                                                            // 6
    }
}
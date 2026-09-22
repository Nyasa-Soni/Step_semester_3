package arrays.class_problems;

import java.util.Arrays;

/**
 * L2. Best Time to Buy and Sell Stock
 * 
 * Scenario:
 * A trainee investor has one week of daily stock prices and wants to know the single best day to buy
 * and the single best later day to sell, to make the largest possible profit.
 * 
 * Task:
 * - Accept an integer array prices, where prices[i] is the price on day i.
 * - Walk through the array once, keeping track of the lowest price seen so far.
 * - At each day, work out the profit if sold today: today's price minus lowest price seen so far.
 * - Keep a running record of the largest profit seen across the whole array.
 * - If the price only ever goes down, the answer is 0.
 */
public class BestTimeToBuyAndSellStock {

    /**
     * Finds the maximum profit achievable by buying and selling a stock once.
     * 
     * @param prices Array of daily stock prices
     * @return Maximum achievable profit, or 0 if no trade is profitable
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                int currentProfit = prices[i] - minPrice;
                if (currentProfit > maxProfit) {
                    maxProfit = currentProfit;
                }
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int profit1 = maxProfit(prices1);
        System.out.println("--- Test Case 1 ---");
        System.out.println("Input: prices = " + Arrays.toString(prices1));
        System.out.println("Output: " + profit1 + " (buy on day 2 at price 1, sell on day 5 at price 6)");

        // Test Case 2
        int[] prices2 = {7, 6, 4, 3, 1};
        int profit2 = maxProfit(prices2);
        System.out.println("\n--- Test Case 2 ---");
        System.out.println("Input: prices = " + Arrays.toString(prices2));
        System.out.println("Output: " + profit2 + " (prices only fall, so no trade is profitable)");
    }
}


package dynamicProgram;

import org.junit.Assert;

/**
 * Created by hulei on 2018/9/6.
 */
public class MaxProfit {
    public static void main(String[] args) {
        Assert.assertEquals(maxProfit(new int[]{1, 2, 5, 0, 2}), 4);
        Assert.assertEquals(maxProfit(new int[]{1, 2, 3, 0, 2}), 3);
    }

    //dp & reverse,空间复杂度O(n)
    public static int maxProfit(int[] prices) {
        int[] buy = new int[prices.length + 1];
        int[] sell = new int[prices.length + 1];
        int[] rest = new int[prices.length + 1];
        for (int idx = 1; idx <= prices.length; idx++) {
            buy[idx] = Math.max(buy[idx - 1], rest[idx - 1] - prices[idx - 1]);
            sell[idx] = Math.max(sell[idx - 1], buy[idx - 1] + prices[idx - 1]);
            rest[idx] = Math.max(rest[idx - 1], Math.max(buy[idx - 1], sell[idx - 1]));
        }

        return sell[sell.length-1];
    }

    public static int maxProfit2(int[] prices) {
        int[] dp = new int[prices.length + 2];
        for (int buy = prices.length - 1; buy >= 0; buy--) {
            dp[buy] = dp[buy + 1];
            for (int sell = buy + 1; sell <= prices.length - 1; sell++) {
                if (prices[sell] > prices[buy]) {
                    int cur = prices[sell] - prices[buy] + dp[sell + 2];
                    dp[buy] = Math.max(dp[buy], cur);
                }
            }
        }

        return dp[0];
    }
}

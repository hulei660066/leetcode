package theoryOfGames;

import org.junit.Assert;

/**
 * Created by hulei on 2018/9/2.
 */
public class PredictTheWinner {
    public static void main(String[] args) {
        Assert.assertEquals(PredictTheWinner(new int[]{1, 5, 233, 7}), true);
        Assert.assertEquals(PredictTheWinner(new int[]{1, 5, 2}), false);
    }

    //DP版本
    public static boolean PredictTheWinner2(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for (int len = 1; len <= nums.length; len++) {
            for (int idx = 0; idx <= nums.length - len; idx++) {
                int end = idx + len - 1;
                int left = 0;
                if (idx + 1 <= nums.length - 1) {
                    left = dp[idx + 1][end];
                }
                int right = 0;
                if (end - 1 >= 0) {
                    right = dp[idx][end - 1];
                }
                dp[idx][end] = Math.max(nums[idx] - left, nums[end] - right);
            }
        }

        return dp[0][nums.length - 1] >= 0;
    }

    public static boolean PredictTheWinner(int[] nums) {
        int scores = helper(nums, 0, nums.length - 1);
        return scores >= 0;
    }

    //过程就是这样,在现有的思维范畴内很难想到这种方法.
    private static int helper(int[] nums, int start, int end) {
        if (start > end) { return 0; }

        int left = helper(nums, start + 1, end);
        int right = helper(nums, start, end - 1);
        return Math.max(nums[start] - left, nums[end] - right);
    }
}

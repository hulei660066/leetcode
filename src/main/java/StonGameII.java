import org.junit.Assert;

/**
 * Created by hulei on 2018/7/28.
 */
public class StonGameII {
    public static void main(String[] args) {
        Assert.assertEquals(helper(new int[]{4,7, 2}),6);
        Assert.assertEquals(helper(new int[]{}),0);
        Assert.assertEquals(helper(new int[]{4,2, 1, 3}),6);
        Assert.assertEquals(helper(new int[]{5, 6, 8, 7}), 13);
        Assert.assertEquals(helper(new int[]{2, 1, 3}),4);
    }

    public static int helper(int[] nums) {
        if (nums.length == 0) { return 0; }

        int isMyTurn=1;
        if (nums.length % 2 == 0) {
            isMyTurn = -1;
        }

        int sum = 0;
        int[][] dp = new int[nums.length][nums.length];
        for (int i = 0; i <= nums.length - 1; i++) {
            sum += nums[i];
            dp[i][i] = isMyTurn * nums[i];
        }

        for (int len = 2; len <= nums.length; len++) {
            isMyTurn = -1 * isMyTurn;
            for (int begin = 0; begin <= nums.length - len; begin++) {
                int end=begin+len-1;
                if (isMyTurn==1) {
                    dp[begin][end] = Math.max(dp[begin + 1][end] + nums[begin], dp[begin][end - 1] + nums[end]);
                } else {
                    dp[begin][end] = Math.min(dp[begin + 1][end] - nums[begin], dp[begin][end - 1] - nums[end]);
                }
            }
       }

        return (sum - dp[0][nums.length - 1]) / 2 + dp[0][nums.length - 1];
    }
}

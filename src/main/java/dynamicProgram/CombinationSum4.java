package dynamicProgram;

import org.junit.Assert;

/**
 * Created by hulei on 2018/9/10.
 */
public class CombinationSum4 {
    public static void main(String[] args) {
        Assert.assertEquals(combinationSum4(new int[]{1, 2, 3}, 4), 7);
    }

    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int idx = 1; idx <= dp.length - 1; idx++) {
            for (int num : nums) {
                if (idx - num >= 0) {
                    dp[idx] += dp[idx - num];
                }
            }
        }
        return dp[dp.length - 1];
    }
}

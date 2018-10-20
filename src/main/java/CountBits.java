import org.junit.Assert;

/**
 * Created by hulei on 2018/8/4.
 */
public class CountBits {
    public static void main(String[] args) {
        Assert.assertEquals(countBits(5), new int[]{0, 1, 1, 2, 1, 2});
    }

    //递归调用,可以看成是一个dp,dp[i]=dp[i%2]+i&1;举例:dp[7]=dp[3]+1,dp[10]=dp[5]+0.
    public static int[] countBits(int num) {
        int[] dp = new int[num + 1];
        for (int i=1; i<=num; i++) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }
}

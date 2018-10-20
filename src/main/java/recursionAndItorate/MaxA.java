package recursionAndItorate;

import org.junit.Assert;

/**
 * Created by hulei on 9/19/18.
 */
public class MaxA {
    public static void main(String[] args) {
        Assert.assertEquals(maxA(3), 3);
        Assert.assertEquals(maxA(7), 9);
    }

    //找临界点
    //F(N)=MAX(F(N-3)*2,F(N-4)*3,F(N-5)*4)
    public static int maxA(int N) {
        int[] dp = new int[N + 1];
        for (int itor = 1; itor <= N; itor++) {
            if (itor <= 6) {
                dp[itor] = itor;
                continue;
            }

            dp[itor] = Math.max(dp[itor - 3] * 2, Math.max(dp[itor - 4] * 3, dp[itor - 5] * 4));
        }
        return dp[N];
    }
}

package ruleOfNature;

import org.junit.Assert;

import java.util.Dictionary;

/**
 * Created by hulei on 9/19/18.
 */
public class NumTilings {
    public static void main(String[] args) {
        Assert.assertEquals(numTilings(60), 882347204);
        Assert.assertEquals(numTilings(30), 312342182);
        Assert.assertEquals(numTilings(4), 11);
        Assert.assertEquals(numTilings(0), 0);
        Assert.assertEquals(numTilings(1), 1);
        Assert.assertEquals(numTilings(2), 2);
        Assert.assertEquals(numTilings(3), 5);
    }

    //F(N)=F(N-1)+F(N-2)+2*F(N-3)+2*F(N-4)+...+F(1)+F(0).
    //F(N)=2*F(N-1)+F(N-3).
    //判断溢出
    public static int numTilings(int N) {
        if (N == 0) { return 0; }

        long[] dp = new long[N + 1];
        dp[N] = 1;
        for (int idx = N; idx >= 2; idx--) {
            long base = dp[idx];
            dp[idx - 1] = (dp[idx - 1] + 2 * base) % (1000000007);
            if (idx - 3 >= 0) { dp[idx - 3] = (dp[idx - 3] + base) % (1000000007); }
        }

        return (int) ((dp[0] + dp[1]) % (1000000007));
    }
}

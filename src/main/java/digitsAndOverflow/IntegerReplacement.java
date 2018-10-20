package digitsAndOverflow;

import org.junit.Assert;

/**
 * Created by hulei on 2018/9/11.
 */
public class IntegerReplacement {
    public static void main(String[] args) {
        Assert.assertEquals(integerReplacement(2147483647), 32);
        Assert.assertEquals(integerReplacement(9), 4);
        Assert.assertEquals(integerReplacement(8), 3);
        Assert.assertEquals(integerReplacement(7), 4);
    }

    //这种题的坑就要做溢出判断
    public static int integerReplacement(int n) {
        int helper = helper(n);
        return helper;
    }

    private static int helper(long n) {
        if (n == 1) { return 0; }

        if (n % 2 == 0) {
            return helper(n / 2) + 1;
        }

        int plus = helper(n + 1);
        int minus = helper(n - 1);

        return Math.min(plus, minus) + 1;
    }
}

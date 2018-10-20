import org.junit.Assert;

import javafx.util.Pair;

/**
 * Created by hulei on 2018/7/31.
 */
class MyPair {
    int zeroCount;
    int oneCount;
}

public class ZeroAndOne {

    public static void main(String[] args) {
        Assert.assertEquals(helper(new String[]{"10", "0", "1"}, 0, 0), 0);
        Assert.assertEquals(helper(new String[]{"10", "0", "1"}, 1, 1), 2);
        Assert.assertEquals(helper(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3), 4);
        Assert.assertEquals(helper(new String[]{}, 5, 3), 0);
    }

    public static int helper(String[] array, int zeorCount, int oneCount) {

        MyPair[] pairs = new MyPair[array.length];
        for (int idx = 0; idx <= array.length - 1; idx++) {
            String cur = array[idx];
            pairs[idx]=new MyPair();
            for (char c : cur.toCharArray()) {
                if (c=='0'){
                    pairs[idx].zeroCount++;
                }else {
                    pairs[idx].oneCount++;
                }
            }
        }

        int max=0;
        int[][] dp = new int[zeorCount+1][oneCount+1];
        for (int idx = 0; idx <= pairs.length - 1; idx++) {
            MyPair pair = pairs[idx];
            for (int row = zeorCount; row >= pair.zeroCount; row--) {
                for (int col = oneCount; col >= pair.oneCount; col--) {
                    dp[row][col] = Math.max(dp[row - pair.zeroCount][col - pair.oneCount] + 1, dp[row][col]);
                    max = Math.max(max, dp[row][col]);
                }
            }
        }

        return max;
    }
}

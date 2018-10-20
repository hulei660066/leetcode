package stringMatch;

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/8.
 */
public class IsMatch {
    public static void main(String[] args) {
        Assert.assertTrue(isMatch("aa", "*"));
        Assert.assertFalse(isMatch("aa", "a"));
        Assert.assertFalse(isMatch("cb", "?a"));
        Assert.assertTrue(isMatch("adceb", "*a*b"));
    }

    public static boolean isMatch(String s, String p) {
        String newS = " " + s;
        String newP = " " + p;

        boolean[][] dp = new boolean[newP.length()][newS.length()];
        dp[0][0]=true;
        for (int row = 1; row <= newP.length() - 1; row++) {
            //如果之前一个是字母,之后都无法匹配.
            if (newP.charAt(row) == '*' && dp[row - 1][0]) {
                dp[row][0] = true;
            }
        }

        for (int row = 1; row <= newP.length() - 1; row++) {
            char c = newP.charAt(row);
            for (int col = 1; col <= newS.length() - 1; col++) {
                if (newP.charAt(row) == newS.charAt(col) || c == '?') {
                    dp[row][col]=dp[row-1][col-1];
                }else if (c=='*'){
                    dp[row][col]=false;
                    //意思分别是:匹配当前;匹配0个;匹配多个
                    if (dp[row - 1][col - 1] || dp[row - 1][col] || dp[row][col - 1]) {
                        dp[row][col]=true;
                    }
                }
            }
        }

        return dp[newP.length()-1][newS.length() - 1];
    }
}

package stringMatch;

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/8.
 */
public class IsMatchII {
    public static void main(String[] args) {
        Assert.assertEquals(isMatch("a",".*"), true);
        Assert.assertEquals(isMatch("aab","c*a*b"), true);
        Assert.assertEquals(isMatch("aaa","ab*a*c*a"), true);
        Assert.assertEquals(isMatch("mississippi","mis*is*p*."), false);
        Assert.assertEquals(isMatch("aa","a"), false);
        Assert.assertEquals(isMatch("aa","a*"), true);
        Assert.assertEquals(isMatch("ab",".*"), true);
        Assert.assertEquals(isMatch("aab","c*a*b"), true);
    }

    public static boolean isMatch(String s, String p) {
        String newS = " " + s;
        String newP = " " + p;

        boolean[][] dp = new boolean[newP.length()][newS.length()];
        dp[0][0]=true;

        for (int i = 1; i <= newP.length()-1; i++) {
            //初始化一定要注意
            if (newP.charAt(i) == '*' && dp[i-2][0]) {
                dp[i][0] = true;
            }
        }

        for (int row=1;row<=newP.length()-1;row++){
            char c = newP.charAt(row);
            for (int col=1;col<=newS.length()-1;col++){
                if (newS.charAt(col) == c || c == '.') {
                    dp[row][col] = dp[row - 1][col - 1];
                } else if (c == '*') {
                    char pre = newP.charAt(row - 1);
                    if (pre == '.' || newS.charAt(col) == pre) {
                        //a*匹配多个aa;匹配一个a;匹配0个a
                        dp[row][col] = dp[row][col - 1] | dp[row - 1][col] | dp[row - 2][col];
                    }else {
                        dp[row][col] = dp[row - 2][col];
                    }
                }

            }
        }

        return dp[newP.length()-1][newS.length()-1];
    }
}

package recursionAndItorate;

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/1.
 */
public class Encode {
    //思路就是dp,从小到大,这次空间放的是具体内容,1.初始化;2.用k划分成俩部分,取max;3.如果当前能字符串可以编码,则编码之,并用dp里的那部分代替之
    public static void main(String[] args) {
        Assert.assertEquals(helper("abbbabbbcabbbabbbc"), "2[2[abbb]c]");
        Assert.assertEquals(helper("abbbabbb"), "2[abbb]");
        Assert.assertEquals(helper("aabcaabcd"), "2[aabc]d");
        Assert.assertEquals(helper("aabcaabcd"), "2[aabc]d");
        Assert.assertEquals(helper("aaaaabbbbb"), "5[a]5[b]");
        Assert.assertEquals(helper("ababab"), "3[ab]");
    }

    public static String helper(String s) {
        String[][] dp = new String[s.length()][s.length()];
        for (int i=0;i<=s.length()-1;i++){
            dp[i][i]=String.valueOf(s.charAt(i));
        }

        for (int len=2;len<=s.length();len++){
            for (int begin=0;begin<=s.length()-len;begin++){
                int end=begin+len-1;
                String sub = s.substring(begin, end + 1);
                //初始化
                dp[begin][end]=sub;

                for (int mid=begin;mid<=end-1;mid++){
                    String left = dp[begin][mid];
                    String right = dp[mid+1][end];
                    if (dp[begin][end].length() > left.length() + right.length()) {
                        dp[begin][end]=left+right;
                    }
                }

                int idx = (sub + sub).indexOf(sub, 1);
                if (idx<sub.length()){
                    //这里用的是dp,用来迭代子问题
                    String shapeShifting = sub.length() / idx + "[" + dp[begin][begin+idx-1] + "]";
                    if (shapeShifting.length()<sub.length()){
                        dp[begin][end]=shapeShifting;
                    }
                }

            }
        }

        return dp[0][s.length()-1];
    }
}

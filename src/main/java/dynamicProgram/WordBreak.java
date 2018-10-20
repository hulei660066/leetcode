package dynamicProgram;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hulei on 2018/8/30.
 */
public class WordBreak {
    public static void main(String[] args) {
        Assert.assertEquals(wordBreak("", Arrays.asList("leet", "code")), true);
        Assert.assertEquals(wordBreak("leetcode", Arrays.asList("leet", "code")), true);
    }

    //就看如何想这道题了,外循环为长度，1-len；内循环为寻找分割点，len-1共len个分割点.
    //优化：利用一个0点位，统一判断逻辑
    //非常有意思的一道题
    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int end = 1; end <= s.length(); end++) {
            for (int begin = 0; begin < end; begin++) {
                if (dp[begin] && wordDict.contains(s.substring(begin, end))) {
                    dp[end] = true;
                }
            }
        }

        return dp[s.length()];
    }
}

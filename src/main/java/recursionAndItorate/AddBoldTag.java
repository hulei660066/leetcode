package recursionAndItorate;

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/25.
 */
public class AddBoldTag {
    public static void main(String[] args) {
        Assert.assertEquals(addBoldTag("aaabbcc", new String[]{"aaa", "aab", "bc"}), "<b>aaabbc</b>c");
        Assert.assertEquals(addBoldTag("abcxyz123", new String[]{"abc", "123"}), "<b>abc</b>xyz<b>123</b>");
    }

    //区间题
    //之前hulu线上竞赛题，是说一个地区下雨，雨是有区间的，给定区间求其内的雨的个数,非常类似的一道题
    public static String addBoldTag(String s, String[] dict) {
        int[] dp = new int[s.length() + 1];
        for (String d : dict) {
            for (int idx = 0; idx <= s.length() - d.length(); idx++) {
                int end = idx + d.length();
                if (!s.substring(idx, end).equals(d)) {
                    continue;
                }
                dp[idx]++;
                dp[end]--;
            }
        }

        int cur = 0;
        int pre;
        //从0开始到len，共len+1个点
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx <= s.length() - 1; idx++) {
            pre = cur;
            cur += dp[idx];
            if (pre == 0 && cur > 0) {
                sb.append("<b>");
            }

            sb.append(s.charAt(idx));
            int post = 0;
            if (idx + 1 <= s.length() - 1) {
                post = cur + dp[idx + 1];
            }
            if (cur > 0 && post == 0) {
                sb.append("</b>");
            }
        }

        return sb.toString();
    }
}

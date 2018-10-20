import org.junit.Assert;

/**
 * Created by hulei on 2018/8/8.
 */
public class LengthOfLongestSubstringTwoDistinct {
    public static void main(String[] args) {
        Assert.assertEquals(lengthOfLongestSubstringTwoDistinct("abcbcbca"), "bcbcbc".length());
        Assert.assertEquals(lengthOfLongestSubstringTwoDistinct(""), "".length());
        Assert.assertEquals(lengthOfLongestSubstringTwoDistinct("abcbbbbcccbdddadacb"), "bcbbbbcccb".length());
    }

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int first = 0;
        int second = 0;
        int[] counts = new int[26];
        int charCount = 0;
        int max=0;
        while (second <= s.length()-1) {
            if (second == s.length()) {
                max = Math.max(max, second - first);
            }
            char c = s.charAt(second);
            if (counts[c - 'a']++ == 0) {
                charCount++;
            }

            max = Math.max(max, second - first);
            while (charCount == 3) {
                int count = --counts[s.charAt(first) - 'a'];
                if (count == 0) { charCount--; }

                first++;
            }

            second++;
        }

        return max;
    }
}

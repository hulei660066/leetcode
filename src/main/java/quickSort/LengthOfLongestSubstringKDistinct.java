package quickSort;

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/18.
 */
public class LengthOfLongestSubstringKDistinct {
    public static void main(String[] args) {
        Assert.assertEquals(lengthOfLongestSubstringKDistinct("eqgkcwGFvjjmxutystqdfhuMblWbylgjxsxgnoh", 16), 27);
        Assert.assertEquals(lengthOfLongestSubstringKDistinct("WORLD", 4), 4);
        Assert.assertEquals(lengthOfLongestSubstringKDistinct("aaaa", 1), 4);
        Assert.assertEquals(lengthOfLongestSubstringKDistinct("eceba", 3), 4);
        Assert.assertEquals(lengthOfLongestSubstringKDistinct("aaaa", 0), 0);
        Assert.assertEquals(lengthOfLongestSubstringKDistinct("", 0), 0);
    }

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) { return 0; }

        int[] counts = new int[128];
        int left = 0;
        int right = 0;
        int count = 0;
        int result = 0;
        while (right <= s.length() - 1) {
            //难点：如何统一结束条件,即如果当前下标是len或者是找到目标str后的一位。
            while (right <= s.length() - 1) {
                char c = s.charAt(right);
                if (counts[c] == 0 && count == k) { break; }

                if (counts[c] == 0) { count++; }
                counts[c]++;
                right++;
            }

            result = Math.max(result, right - left);
            while (count == k) {
                if (counts[s.charAt(left)] == 1) { count--; }
                counts[s.charAt(left++)]--;
            }
        }

        return result;
    }
}

import org.junit.Assert;

/**
 * Created by hulei on 2018/9/1.
 */
public class MaxProduct {
    public static void main(String[] args) {
        Assert.assertEquals(maxProduct(new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"}), 4);
        Assert.assertEquals(maxProduct(new String[]{"a", "aa", "aaa", "aaaa"}), 0);
        Assert.assertEquals(maxProduct(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}), 16);
    }

    //没有头绪
    //原本想用一个26长度的数组存放字符情况，其实可以用位数组，因为对个数不关心。
    public static int maxProduct(String[] words) {
        int[] bytes = new int[words.length];
        int max = 0;
        for (int idx = 0; idx <= words.length - 1; idx++) {
            String word = words[idx];
            for (int i = 0; i <= word.length() - 1; i++) {
                bytes[idx] |= 1 << word.charAt(i) - 'a';
            }

            for (int i = 0; i < idx; i++) {
                //注意运算符的优先条件，好吧。
                if ((bytes[i] & bytes[idx]) == 0) {
                    max = Math.max(max, word.length() * words[i].length());
                }
            }
        }

        return max;
    }
}

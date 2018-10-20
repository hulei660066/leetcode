package recursionAndItorate;

import org.junit.Assert;

/**
 * Created by hulei on 9/19/18.
 */
public class ExpressiveWords {
    public static void main(String[] args) {
        Assert.assertEquals(expressiveWords("aaa", new String[]{"aaaa", "hi", "helo"}), 0);
        Assert.assertEquals(expressiveWords("heeellooo", new String[]{"hello", "hi", "helo"}), 1);
    }

    public static int expressiveWords(String S, String[] words) {
        boolean[] isStretchy = new boolean[S.length()];
        for (int idx = 2; idx <= S.length() - 1; idx++) {
            if (S.charAt(idx - 2) == S.charAt(idx - 1) && S.charAt(idx - 1) == S.charAt(idx)) {
                isStretchy[idx - 2] = true;
                isStretchy[idx - 1] = true;
                isStretchy[idx] = true;
            }
        }

        int result = 0;
        for (String word : words) {
            int left = 0;
            boolean isOk = true;
            for (int idx = 0; idx <= S.length() - 1; idx++) {
                if ((left == word.length() || word.charAt(left) != S.charAt(idx)) && !isStretchy[idx]) {
                    isOk = false;
                    break;
                }

                if (left != word.length() && word.charAt(left) == S.charAt(idx)) {
                    left++;
                }
            }
            if (isOk && left == word.length()) {
                result++;
            }
        }

        return result;
    }
}

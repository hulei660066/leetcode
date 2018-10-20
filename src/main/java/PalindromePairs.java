import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hulei on 2018/8/22.
 */
public class PalindromePairs {
    public static void main(String[] args) {
        Assert.assertEquals(palindromePairs(new String[]{"", "abcd", "dcba", "lls", "s", "sssll"}), new int[][]{{0, 1}, {1, 0}, {3, 2}, {2, 4}});
    }

    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> results = new LinkedList<>();
        for (int first = 0; first <= words.length - 1; first++) {
            for (int second = 0; second <= words.length - 1; second++) {
                if (first == second) { continue; }
                boolean isPalindrome = isPalindrome(words[first], words[second]);
                if (isPalindrome) {
                    List<Integer> result = new LinkedList<>();
                    result.add(first);
                    result.add(second);
                    results.add(result);
                }
            }
        }

        return results;
    }

    private static boolean isPalindrome(String word, String word1) {
        String temp = word + word1;
        int begin = 0;
        int end = temp.length() - 1;
        while (begin < end) {
            if (temp.charAt(begin++) != temp.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}

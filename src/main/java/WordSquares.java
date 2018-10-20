import org.junit.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hulei on 2018/8/2.
 */
public class WordSquares {
    public static void main(String[] args) {
        Assert.assertEquals(wordSquares(new String[]{"abcd","bcda","cdaa","daaa","aaaa","bbbb","aaab","wwww"}), new LinkedList<List<String>>());
        Assert.assertEquals(wordSquares(new String[]{"area","lead","wall","lady","ball"}), new LinkedList<List<String>>());
        Assert.assertEquals(wordSquares(new String[]{"ball","area","lead","lady"}), new LinkedList<List<String>>());
    }

    static Map<String, List<String>> prefixs;
    public static List<List<String>> wordSquares(String[] words) {
        prefixs = new HashMap<>();
        for (String word : words) {
            for (int len = 1; len <= word.length() - 1; len++) {
                String prefix = word.substring(0, len);
                if (!prefixs.containsKey(prefix)) {
                    prefixs.put(prefix, new LinkedList<>());
                }

                prefixs.get(prefix).add(word);
            }
        }

        List<List<String>> results = new LinkedList<>();
        for (String word:words){
            List<String> cur = new LinkedList<String>() {{add(word);}};
            dfs(cur, results);
        }

        return results;
    }

    private static void dfs(List<String> cur, List<List<String>> results) {
        if (cur.size() == cur.get(0).length()) {
            results.add(cur);
            return;
        }

        StringBuilder prefix = new StringBuilder();
        int size = cur.size();
        for (int i = 0; i <= size-1; i++) {
            prefix.append(cur.get(i).charAt(size));
        }

        if (prefixs.containsKey(prefix.toString())) {
            List<String> nexts = prefixs.get(prefix.toString());
            for (String next:nexts){
                    List<String> newCur = new LinkedList<>(cur);
                    newCur.add(next);
                    dfs(newCur, results);
            }
        }
    }
}

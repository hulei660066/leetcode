package quickSort;

import org.junit.Assert;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by hulei on 2018/8/18.
 */
public class WordSquares {

    public static void main(String[] args) {
        Assert.assertEquals(wordSquares(new String[]{"area", "lead", "wall", "lady", "ball"}), new LinkedList<>());
    }

    private static List<List<String>> results;
    public static List<List<String>> wordSquares(String[] words) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            for (int i = 1; i <= word.length(); i++) {
                String str = word.substring(0, i);
                if (!map.containsKey(str)) {
                    map.put(str, new LinkedList<>());
                }
                map.get(str).add(word);
            }
        }

        results = new LinkedList<>();
        for (String word : words) {
            List<String> list = new LinkedList<>();
            list.add(word);
            helper(map, list, word.length());
        }

        return results;
    }

    private static void helper(Map<String, List<String>> map, List<String> list, int len) {
        if (list.size() == len) {
            results.add(list);
            return;
        }

        String prefix = null;
        int idx = list.size();
        for (int i = 0; i <= list.size()-1; i++) {
            StringBuilder sb = new StringBuilder();
            for (String l : list) {
                sb.append(l.charAt(idx));
            }

            prefix = sb.toString();
        }
        List<String> words = new LinkedList<>();
        if (map.containsKey(prefix)) { words = map.get(prefix); }

        for (String word : words) {
            LinkedList<String> newList = new LinkedList<>(list);
            newList.add(word);
            helper(map, newList, len);
        }
    }
}

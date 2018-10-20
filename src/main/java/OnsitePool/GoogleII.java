package OnsitePool;

import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hulei on 2018/8/30.
 */
class Cell {
    Cell[] childern = new Cell[26];
    List<String> values = null;
}

public class GoogleII {
    public static void main(String[] args) {
        GoogleII googleII = new GoogleII(Arrays.asList("google", "gooagle", "goobgle"));
        Assert.assertEquals(googleII.findWordsByFix("go", "le"), Arrays.asList("google", "gooagle", "goobgle"));
        Assert.assertEquals(googleII.findWordsByFix("gooa", "agle"), Arrays.asList("gooagle"));
    }

    private Cell trie;

    public GoogleII(List<String> dicts) {
        trie = new Cell();
        for (String dict : dicts) {
            for (int len = 1; len <= dict.length() - 1; len++) {
                String fix = dict.substring(0, len) + dict.substring(dict.length() - len, dict.length());
                Cell temp = trie;
                for (int i = 0; i <= fix.length() - 1; i++) {
                    if (temp.childern[fix.charAt(i) - 'a'] == null) {
                        temp.childern[fix.charAt(i) - 'a'] = new Cell();
                    }
                    temp = temp.childern[fix.charAt(i) - 'a'];

                    if (i == fix.length() - 1) {
                        if (temp.values == null) {
                            temp.values = new LinkedList<>();
                        }
                        temp.values.add(dict);
                    }
                }

            }
        }
    }


    List<String> findWordsByFix(String prefix, String postfix) {
        String fix = prefix + postfix;
        Cell temp = trie;
        List<String> results = new LinkedList<>();
        for (int i = 0; i <= fix.length() - 1; i++) {
            if (temp.childern[fix.charAt(i) - 'a'] == null) { return results; }

            temp = temp.childern[fix.charAt(i) - 'a'];
            if (i == fix.length() - 1) {
                if (temp.values != null) {
                    results = temp.values;
                }
            }
        }

        return results;
    }
}

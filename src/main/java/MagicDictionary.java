import java.util.HashSet;
import java.util.Set;

/**
 * Created by hulei on 2018/8/30.
 */
public class MagicDictionary {
    /**
     * Initialize your data structure here.
     */
    Set<String> origin;
    Set<String> derive;

    public MagicDictionary() {
        origin = new HashSet<>();
        derive = new HashSet<>();

    }

    /**
     * Build a dictionary through a list of words
     */
    public void buildDict(String[] dict) {
        for (String dic : dict) {
            origin.add(dic);
            for (int i = 0; i <= dic.length() - 1; i++) {
                StringBuilder sb = new StringBuilder(dic);
                sb.deleteCharAt(i);
                derive.add(sb.toString());
            }
        }
    }

    /**
     * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
     */
    public boolean search(String word) {
        if (origin.contains(word)) { return false; }

        for (int i = 0; i <= word.length() - 1; i++) {
            StringBuilder sb = new StringBuilder(word);
            sb.deleteCharAt(i);
            if (derive.contains(sb.toString())) {
                return true;
            }
        }

        return false;
    }
}

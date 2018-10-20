import org.junit.Assert;

import sun.nio.cs.FastCharsetProvider;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hulei on 2018/8/23.
 */
public class IsSentenceSimilarity {
    public static void main(String[] args) {
        List<List<String>> temp = new LinkedList<>();
        temp.add(new LinkedList<String>() {{
            add("great");
            add("fine");
        }});
        temp.add(new LinkedList<String>() {{
            add("drama");
            add("acting");
        }});
        temp.add(new LinkedList<String>() {{
            add("skills");
            add("talent");
        }});

        Assert.assertFalse(isSentenceSimilarity(new String[]{"great", "acting", "skills"}, new String[]{"fine", "talent", "drama"}, temp));
        Assert.assertTrue(isSentenceSimilarity(new String[]{"great", "acting", "skills"}, new String[]{"fine", "drama", "talent"}, temp));
    }

    //题意是双向的，类似于无向图,时对称的，既然要简洁，只要有一个匹配就行，所以就很难想象了
    public static boolean isSentenceSimilarity(String[] words1, String[] words2, List<List<String>> pairs) {
        Map<String, Set<String>> map = new HashMap<>();
        for (List<String> pair : pairs) {
            Set<String> set = new HashSet<>();
            if (!map.containsKey(pair.get(0))) {
                map.put(pair.get(0), set);
            }
            map.get(pair.get(0)).add(pair.get(1));
        }

        for (int idx = 0; idx <= words1.length - 1; idx++) {
            Set<String> temp = new HashSet<>();
            boolean isEqual = words1[idx].equals(words2[idx]);
            boolean contain1 = map.getOrDefault(words1[idx], temp).contains(words2[idx]);
            boolean contain2 = map.getOrDefault(words2[idx], temp).contains(words1[idx]);
            if (isEqual || contain1 || contain2) {
                continue;
            }

            return false;
        }

        return true;
    }
}

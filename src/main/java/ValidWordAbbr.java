import org.junit.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by hulei on 2018/8/24.
 */
public class ValidWordAbbr {
    public static void main(String[] args) {
        ValidWordAbbr validWordAbbr = new ValidWordAbbr(new String[]{"deer", "door", "cake", "card"});
        Assert.assertEquals(validWordAbbr.isUnique("cart"), true);
        Assert.assertEquals(validWordAbbr.isUnique("deer"), false);
        Assert.assertEquals(validWordAbbr.isUnique("cane"), false);
        Assert.assertEquals(validWordAbbr.isUnique("make"), true);
    }

    Map<String, Integer> map;
    Map<String, String> reverseMap;

    public boolean isUnique(String word) {
        String abbre = getAbbre(word);
        if (map.getOrDefault(abbre, 1) == 1 && reverseMap.getOrDefault(abbre, word).equals(word)) {
            return true;
        }
        return false;
    }

    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        reverseMap = new HashMap<>();

        Set<String> set = new HashSet<>();
        for (String dict : dictionary) {
            if (!set.contains(dict)){
                set.add(dict);
                String d = getAbbre(dict);
                map.put(d, map.getOrDefault(d, 0) + 1);
                reverseMap.put(d, dict);
            }
        }
    }

    private String getAbbre(String dict) {
        String s = dict;
        if (dict.length() > 2) {
            int mid = dict.length() - 2;
            s = "" + dict.charAt(0) + mid + dict.charAt(dict.length() - 1);
        }

        return s;
    }
}

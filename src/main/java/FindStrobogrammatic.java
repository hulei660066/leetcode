import org.junit.Assert;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by hulei on 2018/8/27.
 */
public class FindStrobogrammatic {
    public static void main(String[] args) {
        Assert.assertEquals(findStrobogrammatic(3), new LinkedList<>());
        Assert.assertEquals(findStrobogrammatic(2).size(), 4);
    }

    static Map<Character, Character> map;
    static char[] chars;

    public static List<String> findStrobogrammatic(int n) {
        map = new HashMap<>();
        map.put('1', '1');
        map.put('8', '8');
        map.put('6', '9');
        map.put('9', '6');
        chars = new char[]{'0', '1', '8'};

        List<String> result = new LinkedList<>();
        helper(n, new StringBuilder(), result);

        return result;
    }

    //corner case 真的很多
    private static void helper(int n, StringBuilder temp, List<String> results) {
        if (n == 0) {
            results.add(temp.toString());
            return;
        }

        if (n == 1) {
            for (char k : chars) {
                StringBuilder result = new StringBuilder(temp);
                result.insert((result.length() + 1) / 2, k);
                results.add(result.toString());
            }
            return;
        }

        for (Map.Entry<Character, Character> entry : map.entrySet()) {
            StringBuilder newTemp = new StringBuilder(temp);
            newTemp.insert(0, entry.getKey());
            newTemp.append(entry.getValue());
            helper(n - 2, newTemp, results);
        }

        if (n >= 4) {
            StringBuilder newTemp = new StringBuilder(temp);
            newTemp.insert(0, '0');
            newTemp.append('0');
            helper(n - 2, newTemp, results);
        }
    }
}

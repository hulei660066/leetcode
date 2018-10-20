import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hulei on 2018/8/27.
 */
public class IsStrobogrammatic {
    public static void main(String[] args) {
        Assert.assertEquals(isStrobogrammatic("69"), true);
    }

    public static boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('2', '5');
        map.put('5', '2');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');

        StringBuilder sb = new StringBuilder();
        for (char c : num.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            } else {
                sb.insert(0, map.get(c));
            }
        }

        if (sb.toString().equals(num)){
            return true;
        }else {
            return false;
        }
    }
}

package recursionAndItorate;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hulei on 2018/8/27.
 */
public class GeneratePossibleNextMoves {
    public static void main(String[] args) {
        Assert.assertEquals(generatePossibleNextMoves("++++"), new LinkedList<>());
    }

    public static List<String> generatePossibleNextMoves(String s) {
        int idx = 1;
        List<String> results = new LinkedList<>();
        while (idx <= s.length() - 1) {
            if (s.charAt(idx - 1) == '+' && s.charAt(idx) == '+') {
                StringBuilder temp = new StringBuilder(s);
                temp.setCharAt(idx - 1, '-');
                temp.setCharAt(idx, '-');
                results.add(temp.toString());
            }

            idx++;
        }

        return results;
    }

}

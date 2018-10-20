package twoItorate;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hulei on 2018/9/15.
 */
public class PyramidTransition {
    public static void main(String[] args) {
        Assert.assertEquals(pyramidTransition("ABCD", Arrays.asList("BCE", "BCF", "ABA", "CDA", "AEG", "FAG", "GGG")), true);
        Assert.assertEquals(pyramidTransition("BDBBAA", Arrays.asList("ACB", "ACA", "AAA", "ACD", "BCD", "BAA", "BCB", "BCC", "BAD", "BAB", "BAC",
                "CAB", "CCD", "CAA", "CCA", "CCC", "CAD", "DAD", "DAA", "DAC", "DCD", "DCC", "DCA", "DDD", "BDD", "ABB", "ABC", "ABD", "BDB",
                "BBD", "BBC", "BBA", "ADD", "ADC", "ADA", "DDC", "DDB", "DDA", "CDA", "CDD", "CBA", "CDB", "CBD", "DBA", "DBC", "DBD", "BDA")), true);
        Assert.assertEquals(pyramidTransition("XYZ", Arrays.asList("XYD", "YZE", "DEA", "FFF")), true);
        Assert.assertEquals(pyramidTransition("XXYX", Arrays.asList("XXX", "XXY", "XYX", "XYY", "YXZ")), false);
    }

    public static boolean pyramidTransition(String bottom, List<String> allowed) {
        int len = 26;
        StringBuilder[][] matrix = new StringBuilder[len][len];
        for (int row = 0; row <= len - 1; row++) {
            for (int col = 0; col <= len - 1; col++) {
                matrix[row][col] = new StringBuilder();
            }
        }

        for (String a : allowed) {
            matrix[a.charAt(0) - 'A'][a.charAt(1) - 'A'].append(a.charAt(2));
        }
        List<String> list = new LinkedList<>();
        for (char c : bottom.toCharArray()) {
            list.add(String.valueOf(c));
        }
        while (list.size() > 1) {
            List<String> newList = new LinkedList<>();
            for (int idx = 1; idx <= list.size() - 1; idx++) {
                String pre = list.get(idx - 1);
                String cur = list.get(idx);
                int[] mark = new int[len];
                for (int row = 0; row <= pre.length() - 1; row++) {
                    for (int col = 0; col <= cur.length() - 1; col++) {
                        StringBuilder ups = matrix[pre.charAt(row) - 'A'][cur.charAt(col) - 'A'];
                        for (int i = ups.length() - 1; i >= 0; i--) {
                            mark[ups.charAt(i) - 'A']++;
                        }
                    }
                }
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i <= mark.length - 1; i++) {
                    if (mark[i] != 0) {
                        temp.append((char) ('A' + i));
                    }
                }
                if (temp.length() == 0) {
                    return false;
                }

                newList.add(temp.toString());
            }
            list = newList;
        }

        return true;
    }
}

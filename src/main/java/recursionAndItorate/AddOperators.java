package recursionAndItorate;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hulei on 2018/8/21.
 */
public class AddOperators {
    public static void main(String[] args) {
        Assert.assertEquals(addOperators("105", 5), new String[]{"1*0+5", "10-5"});
        Assert.assertEquals(addOperators("123", 6), new String[]{"1+2+3", "1*2*3"});
        Assert.assertEquals(addOperators("232", 8), new String[]{"2*3+2", "2+3*2"});
    }

    static List<String> results;
    static int targetS;

    //这道题要求保留具体结果，所以用dp不现实。O
    public static List<String> addOperators(String num, int target) {
        results = new LinkedList<>();
        targetS = target;
        helper(num, 0, 0, 0, new StringBuilder());

        return results;
    }

    private static void helper(String num, int began, int result, int lastValue, StringBuilder sb) {
        if (began == num.length()) {
            if (result == targetS) { results.add(sb.toString()); }
            return;
        }

        //范围从包含一个字符和全部包含
        for (int end = began + 1; end <= num.length(); end++) {
            long value = Long.valueOf(num.substring(began, end));
            if (value > Integer.MAX_VALUE) {break;}
            int intValue = (int) value;
            //corner case ：055
            if (num.charAt(began) == '0' && (end - began) != 1) { continue; }

            if (began == 0) {
                helper(num, end, intValue, intValue, new StringBuilder().append(intValue));
            } else {
                helper(num, end, result + intValue, intValue, new StringBuilder(sb).append("+").append(intValue));
                helper(num, end, result - intValue, -intValue, new StringBuilder(sb).append("-").append(intValue));
                helper(num, end, result - lastValue + lastValue * intValue, lastValue * intValue, new StringBuilder(sb).append("*").append(intValue));
            }
        }
    }
}

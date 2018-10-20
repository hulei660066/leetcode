package countAndTricky;

import org.junit.Assert;

/**
 * Created by hulei on 2018/9/1.
 */
public class CanTransform {
    public static void main(String[] args) {
        Assert.assertEquals(canTransform("XLXRRXXRXX", "LXXXXXXRRR"), true);
        Assert.assertEquals(canTransform("RXXL", "LXXR"), false);
        Assert.assertEquals(canTransform("XXRXXLXXXX", "XXXXRXXLXX"), false);
        Assert.assertEquals(canTransform("XXXXXLXXXX", "LXXXXXXXXX"), true);
        Assert.assertEquals(canTransform("X", "L"), false);
        Assert.assertEquals(canTransform("RXXLRXRXL", "XRLXXRRLX"), true);
    }

    //一个思路:start出现一个L，countL加一，end出现则减一；start出现一个R，countR加一，end出现则减一；L不能为正，R不能为负
    public static boolean canTransform(String start, String end) {
        int countL = 0;
        int countR = 0;
        for (int idx = 0; idx <= start.length() - 1; idx++) {
            char charOfStart = start.charAt(idx);
            char charOfEnd = end.charAt(idx);
            if (charOfStart == 'R') { countR++; } else if (charOfStart == 'L') { countL++; }
            if (charOfEnd == 'R') { countR--; } else if (charOfEnd == 'L') { countL--; }

            //countL * countR != 0 means R and L show up simultaneously. instance: "R" "L"
            //countL == 1 means cannot change LX to XL. instance "LX" "XL"
            //countR == -1 means cannot change XR to RX. instance "XR" "RX"
            if (countL * countR != 0 || countL == 1 || countR == -1) {
                return false;
            }
        }

        //countL/countR equals 0 if L/R match perfect,
        return countL == 0 && countR == 0;
    }

    // RX XL 递归效应
    public static boolean canTransform2(String start, String end) {
        StringBuilder starts = new StringBuilder(start);
        for (int idx = 0; idx <= starts.length() - 1; idx++) {
            char cur = starts.charAt(idx);
            if (cur == end.charAt(idx)) {
                continue;
            }

            char replace = starts.charAt(idx);
            if (idx + 1 <= starts.length() - 1) {
                replace = starts.charAt(idx + 1);
            }
            String temp = cur + "" + replace;
            if (!temp.equals("RX") && !temp.equals("XL")) {
                return false;
            }
            if (replace != end.charAt(idx)) {
                return false;
            }
            starts.setCharAt(idx + 1, cur);
        }

        return true;
    }
}

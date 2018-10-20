package theoryOfGames;

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/27.
 */
public class CanWin {
    public static void main(String[] args) {
        Assert.assertEquals(canWin("++++"), true);
    }

    //博弈题
    public static boolean canWin(String s) {
        int idx = 1;
        boolean isRiverNeverLoss = true;
        while (idx <= s.length() - 1) {
            if (s.charAt(idx - 1) == '+' && s.charAt(idx) == '+') {
                StringBuilder newS = new StringBuilder(s);
                newS.setCharAt(idx - 1, '-');
                newS.setCharAt(idx, '-');
                isRiverNeverLoss = isRiverNeverLoss && canWin(newS.toString());
            }
            idx++;
        }

        return !isRiverNeverLoss;
    }
}

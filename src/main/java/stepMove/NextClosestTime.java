package stepMove;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hulei on 2018/8/22.
 */
public class NextClosestTime {
    public static void main(String[] args) {
        Assert.assertEquals(nextClosestTime("23:59"), "22:22");
        Assert.assertEquals(nextClosestTime("19:34"), "19:39");
    }

    //这道题没啥，注意下corner case 就minArea好
    public static String nextClosestTime(String time) {
        int firstH = time.charAt(0) - '0';
        int secodH = time.charAt(1) - '0';
        int firstM = time.charAt(3) - '0';
        int secodM = time.charAt(4) - '0';
        int[] digits = {firstH, secodH, firstM, secodM};
        int[] idxs = {4, 3, 1, 0};
        Map<Integer, Integer> upper = new HashMap<>();
        upper.put(4, 9);//00~59分
        upper.put(3, 5);//00~59分
        upper.put(1, 9);//00~19小时
        if (firstH == 2) {
            upper.put(1, 3);//20~23小时
        }
        upper.put(0, 2);//00~23小时

        StringBuilder sb = new StringBuilder(time);
        int smallest = getSmallest(digits);
        for (int idx : idxs) {
            int firstBigger = getFirstBigger((int) time.charAt(idx) - '0', digits);
            boolean isFundBigger = firstBigger != Integer.MAX_VALUE;
            char replace = (char) (firstBigger + '0');
            if (isFundBigger && upper.get(idx) >= firstBigger) {
                sb.setCharAt(idx, replace);
                break;
            }

            sb.setCharAt(idx, (char) (smallest + '0'));
        }

        return sb.toString();
    }

    private static int getFirstBigger(int idx, int[] digits) {
        int bigger = Integer.MAX_VALUE;
        for (int digit : digits) {
            if (idx != digit && digit > idx) {
                bigger = Math.min(bigger - idx, digit - idx) + idx;
            }
        }

        return bigger;
    }

    private static int getSmallest(int[] digits) {
        int min = Integer.MAX_VALUE;
        for (int digit : digits) {
            min = Math.min(min, digit);
        }

        return min;
    }
}

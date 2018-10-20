import org.junit.Assert;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by hulei on 2018/8/14.
 */
public class FindRotateSteps {
    public static void main(String[] args) {
        Assert.assertEquals(findRotateSteps("pqwcx", "cpqwx"), 13);
        Assert.assertEquals(findRotateSteps("abcde", "ade"), 6);
        Assert.assertEquals(findRotateSteps("godding", "gd"), 4);
    }

    //涉及到周期就加倍
    //貌似可以用dp,其本质是什么
    public static int findRotateSteps(String ring, String key) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int idx = 0; idx <= ring.length() - 1; idx++) {
            char c = ring.charAt(idx);
            if (!map.containsKey(c)) {
                map.put(c, new LinkedList<>());
            }
            List<Integer> pos = map.get(c);
            pos.add(idx);
        }

        List<Integer> prePoss = new LinkedList<Integer>() {{add(0);}};
        List<Integer> preSteps = new LinkedList<Integer>() {{add(0);}};
        int bigMin = Integer.MAX_VALUE;
        for (int idx = 0; idx <= key.length() - 1; idx++) {
            List<Integer> curSteps = new LinkedList<>();
            char curChar = key.charAt(idx);
            List<Integer> curPoss = map.get(curChar);

            for (int curPos : curPoss) {
                int min = Integer.MAX_VALUE;
                for (int i = 0; i <= prePoss.size() - 1; i++) {
                    Integer preStep = preSteps.get(i);
                    Integer prepos = prePoss.get(i);
                    int xiao = Math.min(prepos, curPos);
                    int da = Math.max(prepos, curPos);
                    int step = preStep + Math.min(Math.abs(curPos - prepos), Math.abs(xiao + ring.length() - da)) + 1;
                    min = Math.min(step, min);
                }
                curSteps.add(min);

                if (idx == key.length() - 1) {
                    bigMin = Math.min(bigMin, min);
                }
            }

            prePoss = curPoss;
            preSteps = curSteps;
        }

        //        return brute(ring + ring, key, 0, 0);
        return bigMin;
    }

    static int len = 0;
    private static int brute(String ring, String key, int pivot, int step) {
        if (step == key.length()) { return 0; }

        if (pivot >= len) { pivot -= len; }

        int idx = pivot;
        while (ring.charAt(idx) != key.charAt(step)) {
            idx++;
        }
        int antiClock = idx - pivot + brute(ring, key, idx, step + 1) + 1;

        pivot += len;
        idx = pivot;
        while (ring.charAt(idx) != key.charAt(step)) {
            idx--;
        }
        int clock = Math.abs(idx - pivot) + brute(ring, key, idx, step + 1) + 1;

        return Math.min(antiClock, clock);
    }
}

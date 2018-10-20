import org.junit.Assert;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hulei on 2018/9/13.
 */
public class EscapeGhosts {
    public static void main(String[] args) {
        Assert.assertEquals(escapeGhosts(new int[][]{{2, 0}}, new int[]{1, 0}), false);
        Assert.assertEquals(escapeGhosts(new int[][]{{1, 0}}, new int[]{2, 0}), false);
        Assert.assertEquals(escapeGhosts(new int[][]{{1, 0}, {0, 3}}, new int[]{0, 1}), true);
    }

    public static boolean escapeGhosts(int[][] ghosts, int[] target) {
        int dis = Math.abs(target[0]) + Math.abs(target[1]);
        for (int[] ghost : ghosts) {
            int temp = Math.abs(target[0] - ghost[0]) + Math.abs(target[1] - ghost[1]);
            if (temp <= dis) { return false; }
        }

        return true;
    }
}

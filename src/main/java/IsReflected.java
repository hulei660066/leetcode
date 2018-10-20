import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hulei on 2018/9/11.
 */
public class IsReflected {
    public static void main(String[] args) {
        Assert.assertEquals(isReflected(new int[][]{{0, 0}, {1, 0}}), true);
        Assert.assertEquals(isReflected(new int[][]{{1, 1}, {-1, 1}}), true);
        Assert.assertEquals(isReflected(new int[][]{{1, 1}, {-1, -1}}), false);
    }

    public static boolean isReflected(int[][] points) {
        if (points.length == 0) { return true; }

        List<int[]> list = Arrays.asList(points);
        list.sort(Comparator.comparingInt(a -> a[1]));
        for (int idx = 1; idx <= points.length - 1; idx++) {
            int[] pre = points[idx - 1];
            int[] cur = points[idx];
            if (pre[1] == cur[1]) {
                return true;
            }
        }

        return false;
    }
}

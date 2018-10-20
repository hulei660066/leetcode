import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hulei on 2018/8/19.
 */

class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }
}

public class NumIslands2 {
    public static void main(String[] args) {
        Assert.assertEquals(numIslands2(2, 2, new Point[]{new Point(0, 0), new Point(1, 1), new Point(1, 0), new Point(0, 1)}), new
                LinkedList<Integer>() {{
            add(1);
            add(2);
            add(1);
            add(1);
        }});
        Assert.assertEquals(numIslands2(1, 1, new Point[]{}), new LinkedList<>());
        Assert.assertEquals(numIslands2(4, 5, new Point[]{new Point(1, 1), new Point(0, 1), new Point(3, 3), new Point(3, 4)}), new
                LinkedList<Integer>() {{
            add(1);
            add(1);
            add(2);
            add(2);
        }});
        Assert.assertEquals(numIslands2(3, 3, new Point[]{new Point(0, 0), new Point(0, 1), new Point(2, 2), new Point(2, 1)}), new
                LinkedList<Integer>() {{
            add(1);
            add(1);
            add(2);
            add(2);
        }});
    }

    public static List<Integer> numIslands2(int m, int n, Point[] operators) {
        List<Integer> counts = new LinkedList<>();
        if (operators == null) { return counts; }

        int[] union = new int[n * m];
        for (int i = 0; i <= union.length - 1; i++) { union[i] = -1; }

        int count = 0;
        int[][] dirs = new int[][]{new int[]{1, 0}, new int[]{0, 1}, new int[]{-1, 0}, new int[]{0, -1}};
        for (Point pivot : operators) {
            int idx = pivot.y * m + pivot.x;
            if (union[idx] != -1) {
                counts.add(count);
                continue;
            }

            union[idx] = idx;
            count++;
            for (int[] dir : dirs) {
                int row = pivot.y + dir[0];
                int col = pivot.x + dir[1];
                int idxNeighbor = row * m + col;
                boolean isInRange = row >= 0 && row <= n - 1 && col >= 0 && col <= m - 1;
                if (isInRange && union[idxNeighbor] != -1) {
                    int bossPivot = getBoss(union, idx);
                    int bossNeighbor = getBoss(union, idxNeighbor);
                    if (bossPivot != bossNeighbor) {
                        union[bossNeighbor] = bossPivot;
                        count--;
                    }
                }
            }
            counts.add(count);
        }

        return counts;
    }

    private static int getBoss(int[] union, int idx) {
        if (union[idx] == idx) { return idx; }

        int boss = getBoss(union, union[idx]);
        union[idx] = boss;

        return boss;
    }
}

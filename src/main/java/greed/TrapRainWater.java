package greed;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by hulei on 2018/8/3.
 */
public class TrapRainWater {
    //最小生成树; Dijkstra：寻找单源最短路径
    public static void main(String[] args) {
        Assert.assertEquals(trapRainWater(new int[][]{new int[]{1, 4, 3}, new int[]{3, 2, 1}, new int[]{2, 3, 3}}), 0);
        Assert.assertEquals(trapRainWater(new int[][]{new int[]{1, 4, 3, 1, 3, 2}, new int[]{3, 2, 1, 3, 2, 4}, new int[]{2, 3, 3, 2, 3, 1}}), 4);
        Assert.assertEquals(trapRainWater(new int[][]{new int[]{1, 4, 3, 1, 3, 2}, new int[]{3, 2, 1, 3, 2, 4}}), 0);
        Assert.assertEquals(trapRainWater(new int[][]{new int[]{12, 13, 1, 12}, new int[]{13, 4, 13, 12}, new int[]{13, 8, 10, 12}, new int[]{12,
                13, 12, 12}, new int[]{13, 13, 13, 13}}), 14);
    }

    static int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    //又一个贪心算法,如果思维不对,就很用直觉理解其.
    public static int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = (m == 0 ? 0 : heightMap[0].length);
        int res = 0;

        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        for (int i = 0; i < m; i++) {
            pq.offer(new int[] {i, 0, heightMap[i][0]});
            pq.offer(new int[] {i, n - 1, heightMap[i][n - 1]});
            visited[i][0] = visited[i][n - 1] = true;
        }

        for (int j = 1; j < n - 1; j++) {
            pq.offer(new int[] {0, j, heightMap[0][j]});
            pq.offer(new int[] {m - 1, j, heightMap[m - 1][j]});
            visited[0][j] = visited[m - 1][j] = true;
        }

        while (!pq.isEmpty()) {
            int[] cell = pq.poll();

            for (int[] d : dirs) {
                int i = cell[0] + d[0], j = cell[1] + d[1];
                if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) continue;
                res += Math.max(0, cell[2] - heightMap[i][j]);
                pq.offer(new int[] {i, j, Math.max(heightMap[i][j], cell[2])});
                visited[i][j] = true;
            }
        }

        return res;
    }
}

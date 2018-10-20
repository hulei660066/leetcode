package quickSort;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hulei on 2018/8/18.
 */
public class ShortestDistance {
    public static void main(String[] args) {
        Assert.assertEquals(shortestDistance(new int[][]{new int[]{0, 2, 0, 2, 2, 0, 2, 2}, new int[]{0, 2, 2, 2, 1, 0, 1, 2}, new int[]{0, 0, 0,
                1, 0, 2, 0, 0}, new int[]{2, 0, 0, 2, 0, 2, 2, 0}, new int[]{0, 0, 0, 2, 0, 0, 0, 0}}), 11);
        Assert.assertEquals(shortestDistance(new int[][]{new int[]{1, 1, 1, 1, 1, 0}, new int[]{0, 0, 0, 0, 0, 1}, new int[]{0, 1, 1, 0, 0, 1}, new
                int[]{1, 0, 0, 1, 0, 1}, new int[]{1, 0, 1, 0, 0, 1}, new int[]{1, 0, 0, 0, 0, 1}, new int[]{0, 1, 1, 1, 1, 0}}), 88);
        Assert.assertEquals(shortestDistance(new int[][]{new int[]{1, 0, 2, 0, 1}, new int[]{0, 0, 0, 0, 0}, new int[]{0, 0, 1, 0, 0}}), 7);

    }

    //有障碍物，所以需要check是否是连通图
    public static int shortestDistance(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) { return -1; }

        int[][] visiteCount = new int[grid.length][grid[0].length];
        int[][] distance = new int[grid.length][grid[0].length];
        int[][] dirs = new int[][]{new int[]{1, 0}, new int[]{0, 1}, new int[]{-1, 0}, new int[]{0, -1}};
        int count = 0;
        for (int i = 0; i <= grid.length - 1; i++) {
            for (int j = 0; j <= grid[0].length - 1; j++) {
                if (grid[i][j] == 1) {
                    count++;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j, 0});

                    while (!queue.isEmpty()) {
                        int[] pivot = queue.poll();
                        int dis = pivot[2] + 1;
                        for (int[] dir : dirs) {
                            int row = dir[0] + pivot[0];
                            int col = dir[1] + pivot[1];
                            boolean isInRange = row >= 0 && row <= grid.length - 1 && col >= 0 && col < grid[0].length;
                            if (isInRange && visiteCount[row][col] == count-1 && grid[row][col] == 0) {
                                distance[row][col] += dis;
                                visiteCount[row][col]++;
                                queue.offer(new int[]{row, col, dis});
                            }
                        }
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= grid.length - 1; i++) {
            for (int j = 0; j <= grid[0].length - 1; j++) {
                //                if (grid[i][j] == 0 && isFirstVisited[i][j] != count) { return -1; }

                if (grid[i][j] == 0 && visiteCount[i][j] == count) {
                    result = Math.min(result, distance[i][j]);
                }

            }
        }

        return result;
    }
}

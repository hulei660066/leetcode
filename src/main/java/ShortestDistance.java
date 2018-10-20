import org.junit.Assert;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by hulei on 2018/8/30.
 */
public class ShortestDistance {
    public static void main(String[] args) {
        Assert.assertEquals(shortestDistance(new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}}, new
                int[]{0, 4}, new int[]{4, 4}), -1);
        Assert.assertEquals(shortestDistance(new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}}, new
                int[]{0, 4}, new int[]{0, 4}), 0);
        Assert.assertEquals(shortestDistance(new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}}, new
                int[]{0, 4}, new int[]{4, 4}), 12);
    }

    public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        boolean[][] isVisited = new boolean[maze.length][maze[0].length];
        queue.offer(new int[]{start[0], start[1], 0});
        isVisited[start[0]][start[1]] = true;

        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            //判断是否是边界条件
            if (cur[0] == destination[0] && cur[1] == destination[1]) {
                return cur[2];
            }

            for (int[] dir : dirs) {
                int[] next = getNextDir(maze, cur, dir);
                //去重
                if (!isVisited[next[0]][next[1]]) {
                    queue.offer(next);
                    isVisited[next[0]][next[1]] = true;
                }
            }
        }

        return -1;
    }

    private static int[] getNextDir(int[][] maze, int[] cur, int[] dir) {
        int count = cur[2];
        int row = cur[0];
        int col = cur[1];
        while (row + dir[0] >= 0 && row + dir[0] <= maze.length - 1 && col + dir[1] >= 0 && col + dir[1] <= maze[0].length - 1 && maze[row +
                dir[0]][col + dir[1]] != 1) {
            row += dir[0];
            col += dir[1];
            count++;
        }

        return new int[]{row, col, count};
    }
}

package matrix;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hulei on 2018/8/28.
 */
public class WallsAndGates {
    public static void main(String[] args) {
        wallsAndGates(new int[][]{{0, 2147483647, 2147483647, 0, 2147483647, 0, -1, 2147483647, -1, 0, 0, -1, -1, 2147483647, -1, 2147483647,
                2147483647, 0, 2147483647, 2147483647, 0, 0, -1, 2147483647, -1, 2147483647, -1, 2147483647, 2147483647, 0, 0, 0}, {0, 2147483647,
                0, 2147483647, 0, 0, -1, -1, -1, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647, -1, 2147483647, 0, 0, 2147483647,
                2147483647, 2147483647, 0, 0, 0, 0, -1, 2147483647, -1, 2147483647, 0, 2147483647, 0}, {0, 0, 0, 0, 0, -1, 2147483647, -1, -1,
                2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 0, 0, 0, -1, 0, -1, 0, 2147483647, -1, -1, 0, 2147483647, -1, 0, -1,
                2147483647, -1, -1}, {0, 2147483647, 2147483647, 0, -1, -1, -1, 0, 0, -1, 0, 0, 0, 0, 0, 2147483647, 2147483647, 2147483647,
                2147483647, 0, -1, 0, -1, 0, -1, 0, 2147483647, -1, -1, -1, -1, -1}, {2147483647, 0, 0, 0, 2147483647, 2147483647, 2147483647, 0,
                -1, 0, -1, -1, -1, -1, 0, 0, 2147483647, 2147483647, 2147483647, -1, -1, -1, -1, 2147483647, -1, -1, 0, 0, 0, 2147483647, -1, 0}});
        wallsAndGates(new int[][]{{2147483647, -1, 0, 2147483647}, {2147483647, 2147483647, 2147483647, -1}, {2147483647, -1, 2147483647, -1}, {0,
                -1, 2147483647, 2147483647}});
    }

    public static void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) { return; }

        Queue<int[]> queue = new LinkedList<>();
        for (int row = 0; row <= rooms.length - 1; row++) {
            for (int col = 0; col <= rooms[0].length - 1; col++) {
                if (rooms[row][col] == 0) {
                    queue.offer(new int[]{row, col, 0});
                }
            }
        }

        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir : dirs) {
                int newRow = cur[0] + dir[0];
                int newCol = cur[1] + dir[1];
                int level = cur[2] + 1;
                boolean isInRange = newRow >= 0 && newRow <= rooms.length - 1 && newCol >= 0 && newCol <= rooms[0].length - 1;
                if (isInRange && rooms[newRow][newCol] == 2147483647) {
                    rooms[newRow][newCol] = level;
                    queue.offer(new int[]{newRow, newCol, level});
                }
            }
        }
    }
}

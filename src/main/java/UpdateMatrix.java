import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hulei on 2018/9/6.
 */
public class UpdateMatrix {
    public static void main(String[] args) {
        Assert.assertEquals(updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}}), new int[][]{});
    }

    public static int[][] updateMatrix(int[][] matrix) {
        Queue<int[]> queue = new LinkedList<>();
        for (int row = 0; row <= matrix.length - 1; row++) {
            for (int col = 0; col <= matrix[0].length - 1; col++) {
                if (matrix[row][col] == 0) {
                    queue.offer(new int[]{row, col, 0});
                }
            }
        }

        int[][] results = new int[matrix.length][matrix[0].length];
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int count = poll[2] + 1;
            for (int[] dir : dirs) {
                int row = poll[0] + dir[0];
                int col = poll[1] + dir[1];
                boolean isInRange = row >= 0 && row <= matrix.length - 1 && col >= 0 && col < matrix[0].length;
                if (isInRange && matrix[row][col] != 0 && results[row][col] == 0) {
                    results[row][col] = count;
                    queue.offer(new int[]{row, col, count});
                }
            }
        }

        return results;
    }
}

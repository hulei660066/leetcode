package matrix;

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/24.
 */
public class MaxKilledEnemies {
    public static void main(String[] args) {
        Assert.assertEquals(maxKilledEnemies(new char[][]{{'0', 'E', '0', '0'}, {'E', '0', 'W', 'E'}, {'0', 'E', '0', '0'}}),3);
    }

    public static int maxKilledEnemies(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) { return 0; }

        int[][][] dp = new int[grid.length][grid[0].length][];
        for (int row = grid.length - 1; row >= 0; row--) {
            for (int col = grid[0].length - 1; col >= 0; col--) {
                int down = 0;
                if (row != grid.length - 1) {
                    down = dp[row + 1][col][2];
                }
                int right = 0;
                if (col != grid[0].length - 1) {
                    right = dp[row][col + 1][3];
                }
                if (grid[row][col] == 'E') {
                    down++;
                    right++;
                } else if (grid[row][col] == 'W') {
                    down = 0;
                    right = 0;
                }
                dp[row][col] = new int[]{0, 0, down, right};
            }
        }

        int max = 0;
        for (int row = 0; row <= grid.length - 1; row++) {
            for (int col = 0; col <= grid[0].length - 1; col++) {
                int up = 0;
                if (row != 0) {
                    up = dp[row - 1][col][0];
                }
                int left = 0;
                if (col != 0) {
                    left = dp[row][col - 1][1];
                }

                if (grid[row][col] == '0') {
                    max = Math.max(max, up + left + dp[row][col][2] + dp[row][col][3]);
                }

                if (grid[row][col] == 'E') {
                    up++;
                    left++;
                } else if (grid[row][col] == 'W') {
                    up = 0;
                    left = 0;
                }

                dp[row][col] = new int[]{up, left};
            }
        }

        return max;
    }
}

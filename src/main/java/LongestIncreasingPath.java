import org.junit.Assert;

/**
 * Created by hulei on 2018/8/20.
 */
public class LongestIncreasingPath {
    public static void main(String[] args) {
        Assert.assertEquals(longestIncreasingPath(new int[][]{{7, 8, 9}, {9, 7, 6}, {7, 2, 3}}), 6);
        Assert.assertEquals(longestIncreasingPath(new int[][]{}), 0);
        Assert.assertEquals(longestIncreasingPath(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}), 4);
    }

    //这道题类似于拓扑排序
    public static int longestIncreasingPath(int[][] matrix) {
        int result = 0;
        if (matrix.length == 0 || matrix[0].length == 0) { return result; }

        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int row = 0; row <= matrix.length - 1; row++) {
            for (int col = 0; col <= matrix[0].length - 1; col++) {
                int temp = helper(matrix, row, col, dp);
                result = Math.max(result, temp);
            }
        }

        return result;
    }

    private static int helper(int[][] matrix, int row, int col, int[][] dp) {
        if (dp[row][col] != 0) { return dp[row][col]; }

        int result = 1;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : dirs) {
            int newR = dir[0] + row;
            int newC = dir[1] + col;
            boolean isInRange = newR >= 0 && newR <= matrix.length - 1 && newC >= 0 && newC <= matrix[0].length - 1;
            if (isInRange && matrix[newR][newC] < matrix[row][col]) {
                int temp = helper(matrix, newR, newC, dp) + 1;
                result = Math.max(result, temp);
            }

        }

        dp[row][col] = result;
        return result;
    }
}

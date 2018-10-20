import org.junit.Assert;

/**
 * Created by hulei on 2018/8/31.
 */
public class LongestLine {
    public static void main(String[] args) {
        Assert.assertEquals(longestLine(new int[][]{{0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 1}}), 3);
    }

    //对角线 反对角线有公式
    public static int longestLine(int[][] M) {
        int[][] rows = new int[M.length][M[0].length];
        int[][] cols = new int[M.length][M[0].length];
        int[][] diagonal = new int[M.length][M[0].length];
        int[][] antiDiagonal = new int[M.length][M[0].length];

        int max = 0;
        for (int row = 0; row <= M.length - 1; row++) {
            for (int col = 0; col <= M[0].length - 1; col++) {
                int up = 0;
                if (row != 0) { up = M[row - 1][col]; }

                int pre = 0;
                if (col != 0) { pre = M[row][col - 1]; }

                int upPre = 0;
                if (row != 0 && col != 0) { upPre = M[row - 1][col - 1]; }

                int upPost = 0;
                if (row != 0 && col != M[0].length - 1) { upPost = M[row - 1][col + 1]; }

                if (M[row][col] == 1) {

                }
            }
        }

        return max;
    }
}

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/6.
 */
public class NumMatrix {
    public static void main(String[] args) {
        NumMatrix numMatrix = new NumMatrix(new int[][]{new int[]{}, new int[]{}, new int[]{}, new int[]{}, new int[]{}});
        Assert.assertEquals(numMatrix.sumRegion(2, 1, 4, 3), 8);

        NumMatrix numMatrix1 = new NumMatrix(new int[][]{new int[]{3, 0, 1, 4, 2}, new int[]{5, 6, 3, 2, 1}, new int[]{1, 2, 0, 1, 5}, new int[]{4,
                1, 0, 1, 7}, new int[]{1, 0, 3, 0, 5}});
        Assert.assertEquals(numMatrix1.sumRegion(2, 1, 4, 3), 8);
    }

    long[][] sum;
    public NumMatrix(int[][] matrix) {
        this.sum = new long[matrix.length][matrix[0].length];
        for (int rowIdx = 0; rowIdx <= matrix.length - 1; rowIdx++) {
            for (int colIdx = 0; colIdx <= matrix[0].length - 1; colIdx++) {
                long up=0;
                if (rowIdx != 0) { up = this.sum[rowIdx - 1][colIdx]; }

                long left=0;
                if (colIdx != 0) { left = this.sum[rowIdx][colIdx - 1]; }

                long upLeft = 0;
                if (rowIdx != 0 && colIdx != 0) { upLeft = this.sum[rowIdx - 1][colIdx - 1]; }

                sum[rowIdx][colIdx] = matrix[rowIdx][colIdx] + up + left - upLeft;
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        long downLeft=0;
        if (col1 - 1 >= 0) { downLeft = sum[row2][col1 - 1]; }

        long upRight=0;
        if (row1 - 1 >= 0) { upRight = sum[row1 - 1][col2]; }

        long upLeft=0;
        if (row1-1>=0&&col1-1>=0){
            upLeft = sum[row1-1][col1-1];
        }

        long downRight = 0;
        if (row2 <= sum.length - 10 && col2 <= sum[0].length - 1) {
            downRight = sum[row2][col2];
        }

        long result = downRight - downLeft - upRight + upLeft;
        return (int)result;
    }
}

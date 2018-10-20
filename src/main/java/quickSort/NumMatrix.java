package quickSort;

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/18.
 */
public class NumMatrix {
    public static void main(String[] args) {
        NumMatrix obj = new NumMatrix(new int[][]{new int[]{1}, new int[]{-5}, new int[]{-8}, new int[]{2}, new int[]{-6},new int[]{-3},new
                int[]{5},new int[]{-7},new int[]{8},new int[]{-7}});
        Assert.assertEquals(obj.sumRegion(5, 0, 8, 0),3);
        obj.update(3, 0, -1);
        obj.update(3, 0, -1);
        obj.update(0, 0, -6);
        obj.update(5, 0, -7);
        Assert.assertEquals(obj.sumRegion(4, 0, 7, 0),-15);
        Assert.assertEquals(obj.sumRegion(1, 0, 6, 0), -22);
        obj.update(8, 0, -3);
        obj.update(2, 0, -7);
        obj.update(2, 0, 4);
//        NumMatrix obj = new NumMatrix(new int[][]{new int[]{3, 0, 1, 4, 2}, new int[]{5, 6, 3, 2, 1}, new int[]{1, 2, 0, 1, 5}, new int[]{4, 1, 0,
//                1, 7}, new int[]{1, 0, 3, 0, 5}});

//        Assert.assertEquals(obj.sumRegion(2, 1, 4, 3), 8);
//        obj.update(3, 2, 2);
//        Assert.assertEquals(obj.sumRegion(2, 1, 4, 3), 10);
    }

    private int[][] matrix;
    private long[][] rowSum;

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        this.rowSum = new long[matrix.length][matrix[0].length];
        for (int row = 0; row <= matrix.length - 1; row++) {
            long sum = 0;
            for (int col = 0; col <= matrix[0].length - 1; col++) {
                sum += matrix[row][col];
                rowSum[row][col] = sum;
            }
        }
    }

    public void update(int row, int col, int val) {
        long diff = val - matrix[row][col];
        matrix[row][col] = val;
        for (int idx = col; idx <= matrix[row].length - 1; idx++) {
            rowSum[row][idx] += diff;
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        long sum=0;
        for (int row=row1;row<=row2;row++){
            long leftSum=0;
            if (col1-1>=0){
                leftSum=rowSum[row][col1-1];
            }
            sum+=rowSum[row][col2]-leftSum;
        }

        return (int) sum;
    }
}

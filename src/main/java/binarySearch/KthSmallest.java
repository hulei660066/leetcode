package binarySearch;

import org.junit.Assert;

/**
 * Created by hulei on 2018/9/10.
 */
public class KthSmallest {
    public static void main(String[] args) {
        Assert.assertEquals(kthSmallest(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 7), 13);
        Assert.assertEquals(kthSmallest(new int[][]{{-5}}, 1), -5);
        Assert.assertEquals(kthSmallest(new int[][]{{1, 3, 5}, {6, 7, 12}, {11, 14, 14}}, 7), 12);
        Assert.assertEquals(kthSmallest(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8), 13);
    }

    //如何构造一种秩序
    //对于相同的元素
    public static int kthSmallest(int[][] matrix, int k) {
        if (matrix.length == 0 && matrix[0].length == 0) { return 0; }
        int rowLen = matrix.length;
        int colLen = matrix[0].length;

        int small = matrix[0][0];
        int big = matrix[rowLen - 1][colLen - 1];
        while (small <= big) {
            int val = small + ((big - small) >> 1);
            int[] count = findCount(matrix, val);
            if (count[0] == k && count[1] == 1) {
                return val;
            }

            if (count[0] >= k) {
                big = val - 1;
            } else {
                small = val + 1;
            }
        }

        return small;
    }

    public static int kthSmallest2(int[][] matrix, int k) {
        if (matrix.length == 0 && matrix[0].length == 0) { return 0; }
        int rowLen = matrix.length;
        int colLen = matrix[0].length;

        int small = matrix[0][0];
        int big = matrix[rowLen - 1][colLen - 1];
        while (small < big) {
            //这种方式对于负数是不正确的。
            int val = (small + big) >>> 1;
            int[] count = findCount(matrix, val);
            if (count[0] == k && count[1] == 1) {
                return val;
            }

            if (count[0] >= k) {
                big = val;
            } else {
                small = val + 1;
            }
        }

        return small;
    }

    private static int[] findCount(int[][] matrix, int val) {
        int count = 0;
        int isExist = 0;
        for (int row = 0; row <= matrix.length - 1; row++) {
            for (int col = 0; col <= matrix[0].length - 1; col++) {
                if (matrix[row][col] <= val) count++;
                if (matrix[row][col] == val) isExist = 1;
            }
        }

        return new int[]{count, isExist};
    }

}
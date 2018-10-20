import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hulei on 2018/8/6.
 */
public class NumIslandsII {
    public static void main(String[] args) {
        Assert.assertEquals(numIslandsII(3, 3, new int[][]{new int[]{0, 0}, new int[]{0, 1}, new int[]{1, 2}, new int[]{2, 1}}), new int[]{1, 1, 2, 3});
    }

    static int[] numIslandsII(int m, int n,int[][] points){
        int[] union = new int[m * n];
        for (int i = 0; i <= m * n - 1; i++) { union[i] = i; }

        int[][] matrix = new int[m][n];
        int[] results = new int[points.length];
        int idx=0;
        int count=0;
        for (int[] point : points) {
            count++;
            int row=point[0];
            int col=point[1];
            matrix[row][col] = 1;
            List<int[]> neighbors = new LinkedList<>();
            neighbors.add(new int[]{row - 1, col});
            neighbors.add(new int[]{row, col+1});
            neighbors.add(new int[]{row + 1, col});
            neighbors.add(new int[]{row , col-1});
            for (int[] nb:neighbors){
                if (nb[0] >= 0 && nb[0] <= m - 1 && nb[1] >= 0 && nb[1] <= n - 1 && matrix[nb[0]][nb[1]] == 1) {
                    int curBoss = getBoss(union, row * n + col);
                    int neighborBoss = getBoss(union, nb[0] * n + nb[1]);
                    if (curBoss!=neighborBoss){
                        union[neighborBoss]=curBoss;
                        count--;
                    }
                }
            }

            results[idx++] = count;
        }

        return results;
    }

    private static int getBoss(int[] union, int i) {
        if (union[i] == i) { return i; }
        int boss = getBoss(union, union[i]);
        union[i]=boss;

        return boss;
    }
}

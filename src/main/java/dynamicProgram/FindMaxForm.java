package dynamicProgram;

import org.junit.Assert;

import java.util.PriorityQueue;

/**
 * Created by hulei on 2018/9/12.
 */
public class FindMaxForm {
    public static void main(String[] args) {
        Assert.assertEquals(findMaxForm(new String[]{"11", "11", "10", "10", "10"}, 3, 3), 3);
        Assert.assertEquals(findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3), 4);
        Assert.assertEquals(findMaxForm(new String[]{"11", "11", "0", "0", "10", "1", "1", "0", "11", "1", "0", "111", "11111000", "0", "11",
                "000", "1", "1", "0", "00", "1", "101", "001", "000", "0", "00", "0011", "0", "10000"}, 90, 60), 29);
        Assert.assertEquals(findMaxForm(new String[]{"10", "0", "1"}, 1, 1), 2);
    }

    //难道有什么策略吗？贪心吗？
    //dp吗,用有限的资源实现最大化组合
    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] matrix = new int[m + 1][n + 1];
        int max = 0;
        for (String str : strs) {
            int[] counts = new int[2];
            for (int i = 0; i <= str.length() - 1; i++) {
                counts[str.charAt(i) - '0']++;
            }
            for (int zero = m; zero >= counts[0]; zero--) {
                for (int one = n; one >= counts[1]; one--) {
                    matrix[zero][one] = Math.max(matrix[zero][one], matrix[zero - counts[0]][one - counts[1]] + 1);
                    max = Math.max(max, matrix[zero][one]);
                }
            }
        }

        return max;
    }
}

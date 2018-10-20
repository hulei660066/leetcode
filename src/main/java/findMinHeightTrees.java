import com.sun.xml.internal.bind.v2.model.core.ID;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by hulei on 2018/9/10.
 */
public class findMinHeightTrees {
    public static void main(String[] args) {
        Assert.assertEquals(findMinHeightTrees(6, new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}}), Arrays.asList(3, 4));
        Assert.assertEquals(findMinHeightTrees(8, new int[][]{{0, 1}, {1, 2}, {2, 3}, {0, 4}, {4, 5}, {4, 6}, {6, 7}}), Arrays.asList(0));
        Assert.assertEquals(findMinHeightTrees(4, new int[][]{{1, 0}, {1, 2}, {1, 3}}), Arrays.asList(1));
    }

    //Status: Memory Limit Exceeded
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        int[][] matrix = new int[n][n];
        int[] counts = new int[n];
        for (int[] edge : edges) {
            matrix[edge[0]][edge[1]] = 1;
            matrix[edge[1]][edge[0]] = 1;

            counts[edge[0]]++;
            counts[edge[1]]++;
        }

        int[] range = new int[n];
        int count = n;
        while (count > 2) {
            List<Integer> rows = new LinkedList<>();
            for (int idx = 0; idx <= n - 1; idx++) {
                if (counts[idx] == 1) {
                    rows.add(idx);
                    range[idx] = count--;
                }
            }

            for (int row : rows) {
                for (int col = 0; col <= n - 1; col++) {
                    if (matrix[row][col] == 1) {
                        matrix[row][col] = 0;
                        matrix[col][row] = 0;
                        counts[row]--;
                        counts[col]--;
                    }
                }
            }
        }

        List<Integer> results = new LinkedList<>();
        for (int idx = 0; idx <= n - 1; idx++) {
            if (range[idx] == 0) {
                results.add(idx);
            }
        }

        return results;
    }

    //思路: 找叶子节点,然后开始一层一层遍历到中心点.
    //corner case: 一个节点；俩个节点
    public static List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        List<Set<Integer>> keys = new ArrayList<>(n);
        for (int idx = 0; idx <= n - 1; idx++) {
            keys.add(idx, new HashSet<>());
        }

        for (int[] edge : edges) {
            keys.get(edge[0]).add(edge[1]);
            keys.get(edge[1]).add(edge[0]);
        }


        int count = n;
        int[] range = new int[n];
        while (count > 2) {
            List<Integer> cur = new LinkedList<>();
            for (int idx = 0; idx <= n - 1; idx++) {
                if (keys.get(idx).size() == 1) {
                    cur.add(idx);
                    range[idx] = count--;
                }
            }

            for (int idx : cur) {
                Integer val = keys.get(idx).iterator().next();
                keys.get(idx).remove(val);
                keys.get(val).remove(idx);
            }
        }

        List<Integer> results = new LinkedList<>();
        for (int idx = 0; idx <= n - 1; idx++) {
            if (range[idx] == 0) {
                results.add(idx);
            }
        }

        return results;
    }
}

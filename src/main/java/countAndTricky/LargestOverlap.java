package countAndTricky;

import org.junit.Assert;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by hulei on 2018/9/1.
 */
public class LargestOverlap {
    public static void main(String[] args) {
        Assert.assertEquals(largestOverlap(new int[][]{{1, 1, 0}, {0, 1, 0}, {0, 1, 0}}, new int[][]{{0, 0, 0}, {0, 1, 1}, {0, 0, 1}}), 3);
    }

    //问题的本质是：focus on how to uniquely encode the difference between two 1 position in A and B.
    public static int largestOverlap(int[][] A, int[][] B) {
        List<int[]> listA = new LinkedList<>();
        List<int[]> listB = new LinkedList<>();
        for (int row = 0; row <= A.length - 1; row++) {
            for (int col = 0; col <= A.length - 1; col++) {
                if (A[row][col] == 1) { listA.add(new int[]{row, col}); }
                if (B[row][col] == 1) { listB.add(new int[]{row, col}); }
            }
        }

        int max = 0;
        Map<Long, Integer> map = new HashMap<>();
        for (int idxA = 0; idxA <= listA.size() - 1; idxA++) {
            for (int idxB = 0; idxB <= listB.size() - 1; idxB++) {
                int diffRow = listA.get(idxA)[0] - listB.get(idxB)[0];
                int diffCol = listA.get(idxA)[1] - listB.get(idxB)[1];
                //用 integer.max_value溢出
                long uniqueKey = diffRow * 100 + diffCol;
                int add = map.getOrDefault(uniqueKey, 0) + 1;
                map.put(uniqueKey, add);
                max = Math.max(max, add);
            }
        }

        return max;
    }
}

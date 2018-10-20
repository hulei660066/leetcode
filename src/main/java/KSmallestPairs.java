import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import jdk.nashorn.internal.ir.IfNode;

/**
 * Created by hulei on 2018/9/3.
 */
public class KSmallestPairs {
    public static void main(String[] args) {
        Assert.assertEquals(kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3), Arrays.asList(new int[]{1, 2}, new int[]{1, 4}, new
                int[]{1, 6}));
    }

    public static List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> results = new LinkedList<>();
        if (nums1.length == 0 || nums2.length == 0) { return results; }

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> (nums1[a[0]] + nums2[a[1]])));
        queue.offer(new int[]{0, 0});
        boolean[][] isVisited = new boolean[nums1.length][nums2.length];
        while (!queue.isEmpty()) {
            if (results.size() == k) { break; }
            int[] idxs = queue.poll();
            results.add(new int[]{nums1[idxs[0]], nums2[idxs[1]]});
            if (idxs[0] + 1 <= nums1.length - 1 && !isVisited[idxs[0] + 1][idxs[1]]) {
                queue.offer(new int[]{idxs[0] + 1, idxs[1]});
                isVisited[idxs[0] + 1][idxs[1]] = true;
            }

            if (idxs[1] + 1 <= nums2.length - 1 && !isVisited[idxs[0]][idxs[1] + 1]) {
                queue.offer(new int[]{idxs[0], idxs[1] + 1});
                isVisited[idxs[0]][idxs[1] + 1] = true;
            }
        }

        return results;
    }
}

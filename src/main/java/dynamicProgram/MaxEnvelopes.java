package dynamicProgram;

import org.junit.Assert;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by hulei on 2018/8/21.
 */
public class MaxEnvelopes {
    public static void main(String[] args) {
        Assert.assertEquals(maxEnvelopes(new int[][]{}), 0);
        Assert.assertEquals(maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}}), 3);
    }

    public static int maxEnvelopes(int[][] envelopes) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int[] en : envelopes) queue.offer(en);

        int max = 0;

        //用list非常耗时间
        int[][] dp = new int[envelopes.length][];
        int[] counts = new int[envelopes.length];
        while (!queue.isEmpty()) {
            int idx = envelopes.length - queue.size();
            int[] cur = queue.poll();
            int curCount = 1;
            for (int i = 0; i <= idx - 1; i++) {
                int[] pre = dp[i];
                if (pre[0] < cur[0] && pre[1] < cur[1]) {
                    curCount = Math.max(curCount, counts[i] + 1);
                }
            }

            dp[idx] = cur;
            counts[idx] = curCount;
            max = Math.max(max, curCount);
        }

        return max;
    }
}

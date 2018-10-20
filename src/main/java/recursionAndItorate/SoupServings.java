package recursionAndItorate;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by hulei on 9/19/18.
 */
public class SoupServings {
    public static void main(String[] args) {
        Assert.assertEquals(soupServings(50), 0.625, 0.000001);
        Assert.assertEquals(soupServings(800), 0.625, 0.000001);
    }

    //从小往大走，这样就可以利用之前的结果了。
    //方向从大到小
    public static double soupServings(int N) {
        double[][] probs = new double[N + 1][N + 1];
        probs[N][N] = 1;
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] == b[0] ? b[1] - a[1] : b[0] - a[0]);
        queue.add(new int[]{N, N});

        int[][] dirs = new int[][]{{100, 0}, {75, 25}, {50, 50}, {25, 75}};
        double result = 0.0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            double prob = probs[cur[0]][cur[1]] / 4;
            for (int[] dir : dirs) {
                int[] next = {cur[0] - dir[0], cur[1] - dir[1]};
                if (next[0] <= 0 && next[1] <= 0) {
                    result += prob / 2;
                }
                if (next[0] <= 0 && next[1] > 0) {
                    result += prob;
                }
                if (next[0] > 0 && next[1] > 0) {
                    probs[next[0]][next[1]] += prob;
                    queue.offer(next);
                }
            }
        }

        return result;
    }

    //先实现递归版
    public static double soupServings2(int N) {
        Map<Long, Double> map = new HashMap<>();
        return helper(N, N, 1.0, map, N);
    }

    //重复点比想象中的大。
    private static double helper(int first, int second, double probability, Map<Long, Double> map, int total) {
        if (map.containsKey(first * total + second)) {
            return map.get(first * total + second);
        }
        if (first <= 0 && second <= 0) {
            return probability / 2;
        }
        if (first <= 0 && second > 0) {
            return probability;
        }
        if (second <= 0) {
            return 0;
        }

        double result = 0;
        result += helper(first - 100, second, probability / 4, map, total);
        result += helper(first - 75, second - 25, probability / 4, map, total);
        result += helper(first - 50, second - 50, probability / 4, map, total);
        result += helper(first - 25, second - 75, probability / 4, map, total);

        map.put((long) first * total + second, result);
        return result;
    }

}

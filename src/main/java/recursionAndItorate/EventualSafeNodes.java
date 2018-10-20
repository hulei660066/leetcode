package recursionAndItorate;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by hulei on 9/19/18.
 */
public class EventualSafeNodes {
    public static void main(String[] args) {
        Assert.assertEquals(eventualSafeNodes(new int[][]{{}, {0, 2, 3, 4}, {3}, {4}, {}}), Arrays.asList(0, 1, 2, 3, 4));
        Assert.assertEquals(eventualSafeNodes(new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}}), Arrays.asList(2, 4, 5, 6));
    }

    //topo-sort?
    //扫描、遍历
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        int[] isVisited = new int[graph.length];
        for (int idx = 0; idx <= graph.length - 1; idx++) {
            helper(graph, idx, isVisited);
        }

        List<Integer> results = new LinkedList<>();
        for (int idx = 0; idx <= graph.length - 1; idx++) {
            if (isVisited[idx] == 1) { results.add(idx); }
        }
        return results;
    }

    //0：没有遍历过；-1:正在遍历或者不是end点；1：是end。
    private static boolean helper(int[][] graph, int cur, int[] isVisited) {
        if (isVisited[cur] == -1) { return false; }
        if (isVisited[cur] == 1) { return true; }

        isVisited[cur] = -1;
        boolean isEnd = true;
        for (int next : graph[cur]) {
            boolean ret = helper(graph, next, isVisited);
            isEnd &= ret;
        }
        if (isEnd) { isVisited[cur] = 1; }

        return isEnd;
    }

    //思路：从结束点开始遍历，如果当前点所有的邻接点都是结束点，则其也是结束点。
    //topo-sort?
    public static List<Integer> eventualSafeNodes2(int[][] graph) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int idx = 0; idx <= graph.length - 1; idx++) {
            if (graph[idx].length == 0) { queue.offer(idx); }

            for (int next : graph[idx]) {
                if (!map.containsKey(next)) {
                    map.put(next, new LinkedList<>());
                }
                map.get(next).add(idx);
            }
        }

        HashSet<Integer> results = new HashSet<>();
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            int[] nexts = graph[cur];
            boolean isNextTerminal = true;
            for (int next : nexts) {
                if (!results.contains(next)) {
                    isNextTerminal = false;
                    break;
                }
            }
            if (isNextTerminal) {
                results.add(cur);
                List<Integer> pres = map.getOrDefault(cur, new LinkedList<>());
                for (int pre : pres) {
                    queue.offer(pre);
                }
            }
        }

        List<Integer> list = new LinkedList<>(results);
        Collections.sort(list);
        return list;
    }
}

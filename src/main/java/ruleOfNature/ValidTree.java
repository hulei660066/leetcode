package ruleOfNature;

import org.junit.Assert;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by hulei on 2018/9/8.
 */
public class ValidTree {
    public static void main(String[] args) {
        Assert.assertTrue(validTree(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}}));
        Assert.assertFalse(validTree(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}));
    }

    //规律：无环连通图
    static boolean isCyclical;

    public static boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int id = 0; id <= n - 1; id++) { map.put(id, new LinkedList<>());}
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        int[] isVisited = new int[n];
        isCyclical = false;
        for (int idx = 0; idx <= n - 1; idx++) {
            dfs(map, idx, idx, isVisited);
        }

        boolean isEdgeNumRight = edges.length + 1 == n;
        return !isCyclical && isEdgeNumRight;
    }

    private static void dfs(Map<Integer, List<Integer>> map, int pre, int cur, int[] isVisited) {
        if (isVisited[cur] == -1) {
            isCyclical = true;
            return;
        }
        if (isVisited[cur] == 1) { return; }

        isVisited[cur] = -1;
        List<Integer> nexts = map.get(cur);
        for (int next : nexts) {
            if (next == pre) { continue; }

            dfs(map, cur, next, isVisited);
        }
        isVisited[cur] = 1;
    }

    //规律：n个顶点，对应n-1条边，并且只有一个联通图
    public static boolean validTree2(int n, int[][] edges) {
        int[] nums = new int[n];
        for (int id = 0; id <= n - 1; id++) { nums[id] = id; }
        for (int[] edge : edges) {
            int boss0 = getBoss(nums, edge[0]);
            int boss1 = getBoss(nums, edge[1]);
            nums[boss0] = boss1;
        }

        boolean isConnectedGraph = true;
        for (int id = 0; id <= n - 1; id++) {
            getBoss(nums, id);
            if (id != 0 && nums[id - 1] != nums[id]) {
                isConnectedGraph = false;
            }
        }

        boolean isEdgesNumRight = edges.length + 1 == n;
        return isEdgesNumRight && isConnectedGraph;
    }

    private static int getBoss(int[] nums, int val) {
        if (nums[val] == val) {return val;}
        int boss = getBoss(nums, nums[val]);
        nums[val] = boss;

        return boss;
    }
}

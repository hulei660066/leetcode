package range;

import org.junit.Assert;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import javax.xml.ws.soap.Addressing;

import jdk.nashorn.internal.ir.IfNode;

/**
 * Created by hulei on 2018/8/7.
 */
public class GetSkyline {
    public static void main(String[] args) {
        Assert.assertEquals(getSkyline(new int[][]{new int[]{0, 2, 3}, new int[]{2, 5, 3}}), new LinkedList<int[]>() {{
            add(new int[]{0, 3});
            add(new int[]{5, 0});
        }});
        Assert.assertEquals(getSkyline(new int[][]{new int[]{2, 9, 10}, new int[]{9, 12, 15}}), new LinkedList<int[]>() {{
            add(new int[]{2, 10});
            add(new int[]{9, 15});
            add(new int[]{12, 0});
        }});
        Assert.assertEquals(getSkyline(new int[][]{new int[]{2, 9, 10}, new int[]{3, 7, 15}, new int[]{5, 12, 12}, new int[]{15, 20, 10}, new
                int[]{19, 24, 8}}), new LinkedList<int[]>() {{
            add(new int[]{2, 10});
            add(new int[]{3, 15});
            add(new int[]{7, 12});
            add(new int[]{12, 0});
            add(new int[]{15, 10});
            add(new int[]{20, 8});
            add(new int[]{24, 0});
        }});
        Assert.assertEquals(getSkyline(new int[][]{new int[]{0, 2, 3}, new int[]{2, 5, 3}}), new LinkedList<int[]>() {{
            add(new int[]{1, 6});
            add(new int[]{3, 0});
        }});

        Assert.assertEquals(getSkyline(new int[][]{new int[]{1, 2, 5}, new int[]{1, 3, 6}}), new LinkedList<int[]>() {{
            add(new int[]{1, 6});
            add(new int[]{3, 0});
        }});
    }


    //核心思想是维护一个存放当前height的大堆，然后按idx遍历起始点、终止点，根据大堆找到当前点的高度
    //cornor case：起始点、终止点相同的时候，确保高度大的放前面。
    //如何区分开始点和结束点，很tricky
    public static List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new LinkedList<>();
        if (buildings.length == 0) { return result; }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        for (int i = 0; i <= buildings.length - 1; i++) {
            int[] t = buildings[i];
            queue.add(new int[]{t[0], t[2]});
            queue.add(new int[]{t[1], -t[2]});
        }

        PriorityQueue<Integer> bigHeap = new PriorityQueue<>((a, b) -> b - a);
        bigHeap.add(0);
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[1] > 0) {
                bigHeap.add(cur[1]);
            } else {
                bigHeap.remove(-cur[1]);
            }

            int h = bigHeap.peek();

            if (result.isEmpty() || h != result.get(result.size() - 1)[1]) {
                result.add(new int[]{cur[0], h});
            }
        }

        return result;
    }
}

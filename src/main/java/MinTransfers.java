import org.junit.Assert;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by hulei on 2018/8/1.
 */
public class MinTransfers {

    public static void main(String[] args) {
        Assert.assertEquals(balanceGraph(new int[][]{new int[]{16,15,1},new int[]{9,11,59},new int[]{0,1,46},new int[]{14,15,92},new int[]{16,11,
                37},new int[]{14,13,54},new int[]{6,5,17},new int[]{7,6,72},new int[]{5,0,68},new int[]{15,11,4},new int[]{10,11,74},new int[]{5,7,
                54},new int[]{3,4,63},new int[]{11,15,24},new int[]{15,12,17},new int[]{13,14,89},new int[]{0,6,65},new int[]{6,5,91},new int[]{15,13,7},new int[]{11,10,30}}), 12);
        Assert.assertEquals(balanceGraph(new int[][]{new int[]{0, 1, 10}, new int[]{2, 0, 5}}), 2);
        Assert.assertEquals(balanceGraph(new int[][]{new int[]{0, 1, 10}, new int[]{2, 0, 5}}), 2);
        Assert.assertEquals(balanceGraph(new int[][]{new int[]{0,1,10}, new int[]{1,0,1}, new int[]{1,2,5}, new int[]{2,0,5}}), 1);
    }

    public static int balanceGraph(int[][] edges) {
        HashMap<Integer,Long> transfers = new HashMap<Integer, Long>();
        for (int i=0;i<=edges.length-1;i++){
            int[] transfer = edges[i];
            long load = transfers.getOrDefault(transfer[0], 0L) - transfer[2];
            transfers.put(transfer[0], load);

            long borrow = transfers.getOrDefault(transfer[1], 0L) + transfer[2];
            transfers.put(transfer[1], borrow);
        }

        LinkedList<Long> guys = new LinkedList<Long>();
        for (Map.Entry<Integer,Long> entry : transfers.entrySet()) {
            if (entry.getValue()==0){ continue; }
            guys.add(entry.getValue());
        }

        return dfs(guys);
    }

    // 本问题本质等价于有向图,有以下属性:
    // 1.定义出度为正,入度为负,即所有点的出度入度之和为零;
    // 2.定义子集为自身所有点的度数和为零的集合,最小的子集是一个收支平衡的顶点,子集的交易数为v-1(v为顶点数);
    // 3.定义最小子集为不能再细分的子集,本问题完美的转换为尽可能的找最多个数的子集.每多一个子集,交易总数就少一个.
    // 4.递归的思想:取集合set任一点cur,遍历所有其他顶点B,得到新顶点newTrans=cur+B,递归解决新集合set=set-cur-B+newTrans(如果newTrans不为零).
    // 5.动态规划的思想:先找顶点数为1的最小子集;然后找顶点数为2的最小子集;然后是3(2,1)(1,2)...,直到所有顶点都归属于一个最小子集.
    private static int dfs(LinkedList<Long> guys) {
        if (guys.size() == 0) { return 0; }

        int result=Integer.MAX_VALUE;
        LinkedList<Long> copy = new LinkedList<Long>(guys);
        Long cur = copy.removeFirst();
        for (int i=0;i<=copy.size()-1;i++){
            if (copy.get(i) * cur > 0) { continue; }

            LinkedList<Long> newGuys = new LinkedList<Long>(copy);
            long newTrans = cur + newGuys.remove(i);
            if (newTrans != 0) { newGuys.add(newTrans); }
            int count = dfs(newGuys);
            result = Math.min(count + 1, result);
        }

        return result;
    }

}

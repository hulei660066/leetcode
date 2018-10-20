import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hulei on 2018/9/11.
 */
public class VerticalOrder {
    public static void main(String[] args) {
        TreeNode head = new TreeNode(0);
        Assert.assertEquals(verticalOrder(head), true);
    }

    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> results = new LinkedList<>();

        LinkedList<Object[]> queue = new LinkedList<>();
        queue.add(new Object[]{root, 0});

        Map<Integer, List<Integer>> map = new TreeMap<>();
        while (!queue.isEmpty()) {
            Object[] poll = queue.poll();
            TreeNode cur = (TreeNode) poll[0];
            if (cur == null) { continue; }

            int col = (int) poll[1];
            if (!map.containsKey(col)) {
                map.put(col, new LinkedList<>());
            }
            map.get(col).add(cur.val);
            queue.add(new Object[]{cur.left, col - 1});
            queue.add(new Object[]{cur.right, col + 1});
        }

        for (int key : map.keySet()) {
            results.add(map.get(key));
        }

        return results;
    }
}

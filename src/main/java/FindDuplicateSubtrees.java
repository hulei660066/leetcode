import org.junit.Assert;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by hulei on 2018/9/6.
 */
public class FindDuplicateSubtrees {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        Assert.assertEquals(findDuplicateSubtrees(root), new LinkedList<>());
    }

    //序列化然后匹配
    //后序遍历numMatchingSubseq递归
    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> map = new HashMap<>();
        List<TreeNode> results = new LinkedList<>();
        helper(root, map, results);

        return results;
    }

    private static String helper(TreeNode root, Map<String, Integer> map, List<TreeNode> results) {
        if (root == null) { return "#"; }

        String left = helper(root.left, map, results);
        String right = helper(root.right, map, results);
        String rootStr = root.val + left + right;
        map.put(rootStr, map.getOrDefault(rootStr, 0)+1);
        if (map.get(rootStr) == 2) {
            results.add(root);
        }

        return rootStr;
    }
}

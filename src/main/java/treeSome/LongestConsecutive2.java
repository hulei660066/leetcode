package treeSome;

import org.junit.Assert;


class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
/**
 * Created by hulei on 2018/8/23.
 */
public class LongestConsecutive2 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(2);
        root1.right.left = new TreeNode(2);
        root1.right.left.right = new TreeNode(1);
        Assert.assertEquals(longestConsecutive2(root1), 3);

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right = new TreeNode(0);
        Assert.assertEquals(longestConsecutive2(root), 4);

        Assert.assertEquals(longestConsecutive2(null), 0);
    }

    static int max;

    public static int longestConsecutive2(TreeNode root) {
        max = 0;
        if (root != null) { helper(root); }

        return max;
    }

    //需要注意的是要对left 和 right 结果做预处理;类似的题目LeetCode还有一道
    private static int[] helper(TreeNode root) {
        int[] left = new int[]{0, 0};
        if (root.left != null) {
            left = helper(root.left);
            if (root.left.val + 1 != root.val) { left[0] = 0; }
            if (root.left.val - 1 != root.val) { left[1] = 0; }
        }

        int[] right = new int[]{0, 0};
        if (root.right != null) {
            right = helper(root.right);
            if (root.right.val + 1 != root.val) { right[0] = 0; }
            if (root.right.val - 1 != root.val) { right[1] = 0; }
        }

        max = Math.max(max, Math.max(left[0] + right[1] + 1, left[1] + right[0] + 1));
        return new int[]{Math.max(left[0], right[0]) + 1, Math.max(left[1], right[1]) + 1};
    }
}

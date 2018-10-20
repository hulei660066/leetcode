package treeSome;

import org.junit.Assert;


/**
 * Created by hulei on 2018/8/23.
 */
public class LongestConsecutive {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(2);
        root1.right.left = new TreeNode(2);
        root1.right.left.right = new TreeNode(1);
        Assert.assertEquals(longestConsecutive(root1), 3);
    }

    static int max;
    //follow up:longestConsecutive2
    public static int longestConsecutive(TreeNode root) {
        max = 0;
        helper(root, 0, Integer.MIN_VALUE);
        return max;
    }

    private static void helper(TreeNode root, int preLen, int preVal) {
        if (root == null) { return; }
        int curLen = 1;
        if (root.val == preVal + 1) {
            curLen += preLen;
        }
        max = Math.max(max, curLen);

        helper(root.left, curLen, root.val);
        helper(root.right, curLen, root.val);
    }
}

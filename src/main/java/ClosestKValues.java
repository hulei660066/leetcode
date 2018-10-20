import java.util.LinkedList;
import java.util.List;

/**
 * Created by hulei on 2018/8/7.
 */

class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
public class ClosestKValues {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> list = new LinkedList<>();
        helper(root,target,k,list);

        return list;
    }

    private void helper(TreeNode root, double target, int k, List<Integer> list) {
        if (root == null) { return; }
        helper(root.left, target, k, list);
        if (list.size()<k){
            list.add(root.val);
        } else if (Math.abs(list.get(0) - target) > Math.abs(root.val - target)) {
            list.remove(0);
            list.add(root.val);
        }
        helper(root.right, target, k, list);
    }
}

package treeSome;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hulei on 2018/8/22.
 */
public class ClosestKValues {
    public static void main(String[] args) {
    }

    public static List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new LinkedList<>();
        helper(root, target, k, result);

        return result;
    }

    //k=0;root=null;
    private static void helper(TreeNode root, double target, int k, List<Integer> result) {
        if (root == null) { return; }
        helper(root.left, target, k, result);
        result.add(root.val);
        if (result.size() > k) {
            double leftDiff = Math.abs(result.get(0) - target);
            double rightDiff = Math.abs(result.get(result.size() - 1) - target);
            if (leftDiff > rightDiff) {
                result.remove(0);
            } else {
                result.remove(result.size() - 1);
            }
        }
        helper(root.right, target, k, result);
    }
}

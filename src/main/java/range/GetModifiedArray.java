package range;

import org.junit.Assert;

/**
 * Created by hulei on 2018/9/10.
 */
public class GetModifiedArray {
    public static void main(String[] args) {
        Assert.assertEquals(getModifiedArray(5, new int[][]{{1, 3, 2}, {2, 4, 3}, {0, 2, -2}}), new int[]{-2, 0, 3, 5, 3});
    }

    public static int[] getModifiedArray(int length, int[][] updates) {
        int[] nums = new int[length];
        for (int[] update : updates) {
            nums[update[0]] += update[2];
            if (update[1] != length - 1) {
                nums[update[1]+1] -= update[2];
            }
        }

        int pre = 0;
        for (int idx = 0; idx <= length - 1; idx++) {
            pre += nums[idx];
            nums[idx] = pre;
        }

        return nums;
    }
}

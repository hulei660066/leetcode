package quickSort;

import org.junit.Assert;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hulei on 2018/8/17.
 */
public class QuickPartition {
    public static void main(String[] args) {
        Assert.assertEquals(quickPartition(new int[]{1, 7, -5, 9, -12, 15}), new int[]{});
    }

    static int[] quickPartition(int[] nums) {
        int left = 0;
        int right = 0;
        List<Integer> list = new LinkedList<>();
        while (right <= nums.length - 1) {
            if (nums[right] < 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }
        //反向再次遍历一遍

        return nums;
    }
}

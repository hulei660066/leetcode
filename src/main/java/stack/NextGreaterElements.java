package stack;

import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by hulei on 2018/9/3.
 */
public class NextGreaterElements {
    public static void main(String[] args) {
        Assert.assertEquals(nextGreaterElements(new int[]{1, 2, 1}), new int[]{2, -1, 2});
        Assert.assertEquals(nextGreaterElements(new int[]{1, 1, 1, 1, 1}), new int[]{-1, -1, -1, -1, -1});
        Assert.assertEquals(nextGreaterElements(new int[]{5, 4, 3, 2, 1}), new int[]{-1, 5, 5, 5, 5});
    }

    //对于循环的问题，加倍或者遍历俩边.
    public static int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        for (int idx = 0; idx <= result.length - 1; idx++) {
            result[idx] = -1;
        }

        Stack<Integer> stack = new Stack<>();
        helper(nums, result, stack);
        helper(nums, result, stack);

        return result;
    }

    private static void helper(int[] nums, int[] result, Stack<Integer> stack) {
        for (int idx = 0; idx <= nums.length - 1; idx++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[idx]) {
                Integer pop = stack.pop();
                result[pop] = nums[idx];
            }
            stack.push(idx);
        }
    }
}

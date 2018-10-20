package stack;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by hulei on 2018/9/3.
 */
public class NextGreaterElement {
    public static void main(String[] args) {
        Assert.assertEquals(nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}), new int[]{-1, 3, -1});
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int idx = 0; idx <= nums2.length - 1; idx++) {
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[idx]) {
                Integer pop = stack.pop();
                map.put(nums2[pop], nums2[idx]);
            }
            stack.push(idx);
        }

        for (int idx = 0; idx <= nums1.length - 1; idx++) {
            Integer integer = map.getOrDefault(nums1[idx], -1);
            nums1[idx]=integer;
        }

        return nums1;
    }
}

package twoPoints;

import org.junit.Assert;

import java.util.Arrays;

/**
 * Created by hulei on 2018/8/25.
 */
public class ThreeSumSmaller {
    public static void main(String[] args) {
        Assert.assertEquals(quickSort(new int[]{-2, 0, 1, 3}, 0, 3), "-2013");
        Assert.assertEquals(quickSort(new int[]{3, 1, 0, -2}, 0, 3), "-2013");
        Assert.assertEquals(quickSort(new int[]{-2, 1, 3}, 0, 2), "-213");
        Assert.assertEquals(quickSort(new int[]{3, 0, -2}, 0, 2), "-203");
        Assert.assertEquals(quickSort(new int[]{3}, 0, 0), "3");
        Assert.assertEquals(quickSort(new int[]{}, 0, -1), "");

        Assert.assertEquals(threeSumSmaller(new int[]{3, 1, 0, -2}, 4), 3);
        Assert.assertEquals(threeSumSmaller(new int[]{-2, 0, 1, 3}, 2), 2);
    }

    public static int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;
        for (int idx = 0; idx <= nums.length - 1; idx++) {
            int temp = target - nums[idx];
            int begin = idx + 1;
            int end = nums.length - 1;
            while (begin < end) {
                long sum = nums[begin] + nums[end];
                if (sum < temp) {
                    //无外乎有三种情况，idx在左边、中间、右边。
                    count += (end - begin);
                    begin++;
                } else {
                    end--;
                }
            }
        }

        return count;
    }

    private static String quickSort(int[] nums, int begin, int end) {
        if (begin > end) return "";
        int left = begin - 1;
        int right = begin;
        while (right <= end) {
            if (nums[right] <= nums[end]) {
                left++;
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
            right++;
        }
        quickSort(nums, begin, left - 1);
        quickSort(nums, left + 1, right - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= nums.length - 1; i++) {
            sb.append(nums[i]);
        }

        return sb.toString();
    }
}

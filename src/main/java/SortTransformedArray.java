import org.junit.Assert;

/**
 * Created by hulei on 2018/8/27.
 */
public class SortTransformedArray {
    public static void main(String[] args) {
        Assert.assertEquals(sortTransformedArray(new int[]{-4, -2, 2, 4}, 1, 3, 5), new int[]{3, 9, 15, 33});
        Assert.assertEquals(sortTransformedArray(new int[]{-4, -2, 2, 4}, -1, 3, 5), new int[]{-23, -5, 1, 7});
    }

    public static int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        //开口朝上
        int left = 0;
        int right = nums.length - 1;
        int idx = nums.length - 1;
        int[] results = new int[nums.length];
        if (a >= 0) {
            while (left < right) {
                int resultLeft = a * nums[left] * nums[left] + b * nums[left] + c;
                int resultRight = a * nums[right] * nums[right] + b * nums[right] + c;
                if (resultLeft > resultRight) {
                    results[idx++] = resultLeft;
                    left++;
                } else {
                    results[idx++] = resultRight;
                    right--;
                }
            }
        } else {

        }
        return nums;
    }
}

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/13.
 */
public class FindMaxAverage {

    public static void main(String[] args) {
        Assert.assertEquals(findMaxAverage(new int[]{-1}, 1), -1, 0.0001);
        Assert.assertEquals(findMaxAverage(new int[]{5}, 1), 5, 0.0001);
        Assert.assertEquals(findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4), 12.75, 0.0001);
    }

    public static double findMaxAverage(int[] nums, int k) {
        long[] sunny = new long[nums.length];
        long temp = 0;
        for (int i = 0; i <= nums.length - 1; i++) {
            temp += nums[i];
            sunny[i] = temp;
        }

        long max = Integer.MIN_VALUE;
        for (int i = k - 1; i <= nums.length - 1; i++) {
            long sum = sunny[i];
            int pre = i - k + 1;
            if (pre - 1 >= 0) {
                sum -= sunny[pre - 1];
            }

            if (sum > max) {
                max = sum;
            }
        }

        return (double) max / k;
    }
}

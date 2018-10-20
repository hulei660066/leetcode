import org.junit.Assert;

/**
 * Created by hulei on 2018/8/31.
 */
public class FindPermutation {
    public static void main(String[] args) {
        Assert.assertEquals(findPermutation("DDDI"), new int[]{4, 3, 2, 1, 5});
        Assert.assertEquals(findPermutation("I"), new int[]{1, 2});
        Assert.assertEquals(findPermutation("ID"), new int[]{1, 3, 2});
    }

    public static int[] findPermutation(String s) {
        int[] nums = new int[s.length() + 1];
        for (int idx = 0; idx <= nums.length - 1; idx++) {
            nums[idx] = idx + 1;
        }

        int count = 0;
        for (int idx = 0; idx <= s.length() - 1; idx++) {
            if (s.charAt(idx) == 'D') { count++; }

            boolean isDTurn = idx + 1 == s.length() || s.charAt(idx + 1) == 'I';
            if (isDTurn && count != 0) {
                int end = idx + 1;
                int begin = end - count;
                swap(nums, begin, end);
                count = 0;
            }

        }

        return nums;
    }

    private static void swap(int[] nums, int begin, int end) {
        while (begin < end) {
            int temp = nums[begin];
            nums[begin] = nums[end];
            nums[end] = temp;
            begin++;
            end--;
        }
    }
}

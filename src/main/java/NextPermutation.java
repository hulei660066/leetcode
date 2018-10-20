import org.junit.Assert;

/**
 * Created by hulei on 2018/9/1.
 */
public class NextPermutation {
    public static void main(String[] args) {
        nextPermutation(new int[]{1, 3, 2});
        nextPermutation(new int[]{3, 2, 1});
        nextPermutation(new int[]{1, 1, 5});
        nextPermutation(new int[]{1, 2, 3});
    }

    //一个思路
    public static void nextPermutation(int[] nums) {
        int idx;
        for (idx = nums.length - 1; idx >= 1; idx--) {
            if (nums[idx - 1] < nums[idx]) { break; }
        }
        swap(nums, idx, nums.length - 1);

        if (idx - 1 >= 0) {
            for (int i = idx; i <= nums.length - 1; i++) {
                if (nums[i] > nums[idx - 1]) {
                    int swamp = nums[i];
                    nums[i] = nums[idx - 1];
                    nums[idx - 1] = swamp;
                    break;
                }
            }
        }
        return;
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

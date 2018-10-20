package extra;

import com.sun.xml.internal.bind.v2.model.core.ID;

import org.junit.Assert;

/**
 * Created by hulei on 2018/9/3.
 */
public class WiggleSort {
    public static void main(String[] args) {
        int[] input = {1, 5, 1, 1, 6, 4};
        wiggleSort(input);
        //        Assert.assertEquals(input, new int[]{1, 4, 1, 5, 1, 6});

        input = new int[]{4, 5, 5, 6};
        wiggleSort(input);
        //        Assert.assertEquals(input, new int[]{5,6,4,5});

        input = new int[]{1, 1, 1, 2, 3, 4};
        wiggleSort(input);
        //        Assert.assertEquals(input, new int[]{1, 2, 1, 3, 1, 4});

        input = new int[]{1, 2, 2, 1, 2, 1, 1, 1, 1, 2, 2, 2};
        wiggleSort(input);


        input = new int[]{1, 3, 2, 2, 3, 1};
        wiggleSort(input);
        Assert.assertEquals(input, new int[]{2, 3, 1, 3, 1, 2});
    }

    //如何处理相等的情况,引入第三者,three color sort?
    //考虑预处理
    //focus on how to deal equal cases
    //问题的本质是俩个指针
    public static void wiggleSort(int[] nums) {
        int left = 0;
        for (int right = 1; right <= nums.length - 1; right++) {
            if (nums[left] == nums[right]) { continue; }

            int max = Math.max(nums[left], nums[right]);
            int min = Math.min(nums[left], nums[right]);
            if (left % 2 == 0) {
                if (left + 1 != right) {
                    nums[right] = nums[left];
                }
                nums[left] = min;
                nums[left + 1] = max;
                left++;
                if (left + 1 <= right && nums[left] > nums[left + 1]) {
                    left++;
                }
            } else {
                if (left + 1 != right) {
                    nums[right] = nums[left];
                }
                nums[left] = max;
                nums[left + 1] = min;
                left++;
                if (left + 1 <= right && nums[left] < nums[left + 1]) {
                    left++;
                }
            }
        }
    }
}

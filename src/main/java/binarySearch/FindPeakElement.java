package binarySearch;

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/31.
 */
public class FindPeakElement {
    public static void main(String[] args) {
        Assert.assertEquals(findPeakElement(new int[]{1, 2, 3, 1}), 2);
        Assert.assertEquals(findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}), 5);
    }

    public static int findPeakElement(int[] nums) {
        int begin = 0;
        int end = nums.length - 1;
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if ((mid == 0 || nums[mid] > nums[mid - 1]) && (mid == nums.length - 1 || nums[mid] > nums[mid + 1])) {
                return mid;
            } else if (mid == 0 || nums[mid] > nums[mid - 1]) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }
}

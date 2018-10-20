package binarySearch;

import org.junit.Assert;

/**
 * Created by hulei on 2018/7/31.
 */
public class MedianOfTwoStream {
    //这道题的本质是二分法,目标是在A数组迭代找一个位置i和数组B中的一个位置j=k-i,使得max(left)<=min(right)
    //注意事项:Corner case;
    public static void main(String[] args) {
        Assert.assertEquals(findMedianSortedArrays(new int[]{}, new int[]{}), 0,0.001);
        Assert.assertEquals(findMedianSortedArrays(new int[]{1, 2}, new int[]{}), 1.5,0.001);
        Assert.assertEquals(findMedianSortedArrays(new int[]{}, new int[]{3, 4}), 3.5,0.001);
        Assert.assertEquals(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}), 2.5,0.001);
        Assert.assertEquals(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}), 2.5,0.001);

        Assert.assertEquals(helper(new int[]{1, 3, 5}, new int[]{}, 2), 3, 0.001);
        Assert.assertEquals(helper(new int[]{}, new int[]{2, 4, 6, 7}, 2), 4, 0.001);
        Assert.assertEquals(helper(new int[]{1, 3, 5}, new int[]{2, 4, 6, 7}, 2), 2, 0.001);
        Assert.assertEquals(helper(new int[]{1, 3, 5}, new int[]{2, 4, 6, 7}, 1), 1, 0.001);
        Assert.assertEquals(helper(new int[]{1, 3, 5}, new int[]{2, 4, 6, 7}, 6), 6, 0.001);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if (total == 0) { return 0; }

        if (total%2==1){
           return helper(nums1,nums2,(total+1)/2);
        }else {
            double result1 = helper(nums1, nums2, total / 2);
            double result2 = helper(nums1, nums2, total / 2 + 1);

            return (result1+result2)/2.0;
        }

    }

    public static double helper(int[] nums1, int[] nums2, int kth) {
        if (nums1.length + nums2.length < kth) { return Double.MIN_VALUE; }
        if (nums1.length==0){ return nums2[kth-1]; }
        if (nums2.length==0){ return nums1[kth-1]; }

        int begin=0;
        int end=nums1.length-1;
        while (begin <= end) {
            int mid = (begin + end) / 2;
            int len = kth - mid - 1;

            Integer aRight = Integer.MAX_VALUE;
            if (mid + 1 <= nums1.length - 1) { aRight = nums1[mid + 1]; }

            Integer bLeft = Integer.MIN_VALUE;
            Integer bRight = Integer.MAX_VALUE;
            if (len > 0 && len  <= nums2.length - 1) {
                bLeft = nums2[len - 1];
                bRight = nums2[len];
            }else if (len<=0){
                bRight=nums2[0];
            }else if (len>=nums2.length){
                bLeft=nums2[nums2.length-1];
            }

            int aLeft = nums1[mid];
            if (len >= 0 && len <= nums2.length && aLeft <= bRight && aRight >= bLeft) {
                return Math.max(aLeft, bLeft);
            }

            if (len < 0 || aLeft > bRight) {
                end = mid - 1;
            } else if (len > nums2.length || bLeft > aRight) {
                begin = mid + 1;
            }
        }

        return nums2[kth-1];
    }
}

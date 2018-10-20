package binarySearch;

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/9.
 */
public class FindMedianSortedArrays {
    public static void main(String[] args) {
        Assert.assertEquals(findMedianSortedArrays(new int[]{2,3,4,5,6}, new int[]{1}), 3.5, 0.00001);
        Assert.assertEquals(findMedianSortedArrays(new int[]{1}, new int[]{1}), 1, 0.00001);
        Assert.assertEquals(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}), 2.5, 0.00001);
        Assert.assertEquals(findMedianSortedArrays(new int[]{1, 2}, new int[]{}), 1.5, 0.00001);
        Assert.assertEquals(findMedianSortedArrays(new int[]{3}, new int[]{-2,-1}), -1, 0.00001);
        Assert.assertEquals(findMedianSortedArrays(new int[]{}, new int[]{1}), 1.0, 0.00001);
        Assert.assertEquals(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}), 2.0, 0.00001);
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        if (len == 0) { return 0; }

        if (len %2== 1){
            return helper(nums1, nums2, len / 2+1);
        } else {
            double result1 = helper(nums1, nums2,(len - 1) / 2+1);
            double result2 = helper(nums1, nums2,len / 2+1);
            return (result1+result2)/2;
        }

    }


    private static double helper(int[] nums1, int[] nums2, int kth ) {
        if (nums2.length == 0) { return nums1[kth - 1]; }

        //0代表一个都不选,len代表都选,共需考虑len+1种情况.
        int begin=0;
        int end=nums1.length;
        while (begin <= end) {
            int mid1 = (begin + end + 1) / 2;
            int mid2 = kth - mid1;
            //这边比较重要的是确保mid2不超范围.
            if (mid2<0){
                end=mid1-1;
                continue;
            }else if (mid2>nums2.length){
                begin=mid1+1;
                continue;
            }

            int left1=Integer.MIN_VALUE;
            if (mid1-1 >= 0) { left1 = nums1[mid1 - 1]; }
            int right1= Integer.MAX_VALUE;
            if (mid1 <= nums1.length - 1) { right1 = nums1[mid1]; }

            int left2=Integer.MIN_VALUE;
            if (mid2 - 1 >= 0) { left2 = nums2[mid2 - 1]; }
            int right2= Integer.MAX_VALUE;
            if (mid2 <= nums2.length - 1) { right2 = nums2[mid2]; }

            if (left1<=right2&&left2<=right1){
                return Math.max(left1,left2);
            }else if (left1>right2){
                end=mid1-1;
            }else {
                begin=mid1+1;
            }
        }

        return nums2[kth - 1];
    }
        private static double helperI(int[] nums1, int[] nums2, int kth ) {
        if (nums2.length == 0) { return nums1[kth - 1]; }

        //0代表一个都不选,len代表都选,这样所有len+1的情况都考虑了.
        int begin=-1;
        int end=nums1.length-1;
        while (begin <= end) {
            int mid1 = (begin + end ) / 2;
            int mid2 = kth - mid1-2;
            //这边比较重要的是确保mid2不超范围.
            if (mid2<-1){
                end=mid1-1;
                continue;
            }else if (mid2>nums2.length-1){
                begin=mid1+1;
                continue;
            }

            int left1=Integer.MIN_VALUE;
            if (mid1 >= 0) { left1 = nums1[mid1 ]; }
            int right1= Integer.MAX_VALUE;
            if (mid1+1 <= nums1.length - 1) { right1 = nums1[mid1+1]; }

            int left2=Integer.MIN_VALUE;
            if (mid2  >= 0) { left2 = nums2[mid2 ]; }
            int right2= Integer.MAX_VALUE;
            if (mid2+1 <= nums2.length - 1) { right2 = nums2[mid2+1]; }

            if (left1<=right2&&left2<=right1){
                return Math.max(left1,left2);
            }else if (left1>right2){
                end=mid1-1;
            }else {
                begin=mid1+1;
            }
        }

        return nums2[kth - 1];
    }
}

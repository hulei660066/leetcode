package binarySearch;

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/14.
 */
public class ReversePairs {
    public static void main(String[] args) {
        Assert.assertEquals(reversePairs(new int[]{2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647}), 0);
        Assert.assertEquals(reversePairs(new int[]{2,4,3,5,1}), 3);
        Assert.assertEquals(reversePairs(new int[]{1,3,2,3,1}), 2);
    }
    //原来以为是个排序+双指针题.实际是NPC题,全部都要遍历一遍,所以下限是n*lgn,归并排序.
    //跟位置有关的用mergesort
    //跟数字加减乘除有关的做溢出判断.
    public static int reversePairs(int[] nums) {
        return mergeSort(nums,0,nums.length-1);
    }

    private static int mergeSort(int[] nums, int left, int right) {
        if (left >= right) { return 0; }

        int mid = (left + right) / 2;
        int result = mergeSort(nums, left, mid) ;
        result += mergeSort(nums, mid + 1, right);

        int count = (int) helper(nums, left, mid, right, 2L)[1];

        int[] temp = (int[]) helper(nums, left, mid, right, 1L)[0];
        int idx=0;
        while (idx<=temp.length-1){
            nums[left++]=temp[idx++];
        }


        return result+count;
    }

    private static Object[] helper(int[] nums, int left, int mid, int right, long count) {
        int idx=0;
        int idxL=left;
        int idxR=mid+1;
        int[] temp = new int[right - left + 1];
        int result=0;
        while (idxL<=mid||idxR<=right){
            if (idxL>mid){
                temp[idx++]=nums[idxR++];
            }else if (idxR>right){
                temp[idx++]=nums[idxL++];
            }else {
                if (nums[idxL] > count * nums[idxR]) {
                    temp[idx++]=nums[idxR++];
                    result += mid - idxL + 1;
                }else {
                    temp[idx++]=nums[idxL++];
                }
            }
        }

        return new Object[]{temp,result};
    }
}

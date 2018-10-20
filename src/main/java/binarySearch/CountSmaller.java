package binarySearch;

import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hulei on 2018/8/6.
 */
public class CountSmaller {
    public static void main(String[] args) {
        Assert.assertEquals(countSmaller(new int[]{5, 2, 6, 1}), new LinkedList<>());
        Assert.assertEquals(countSmaller(new int[]{2, 0, 1}), new LinkedList<>());
        Assert.assertEquals(countSmaller(new int[]{}), new LinkedList<>());
        Assert.assertEquals(countSmaller(new int[]{5,2,6,1}), new LinkedList<>());
    }

    private static int[] counts;
    private static List<Integer> countSmaller(int[] nums) {
        int[] indexs = new int[nums.length];
        for (int i = 0; i <= indexs.length - 1; i++) {
            indexs[i] = i;
        }

        counts = new int[nums.length];
        helper(nums,0,nums.length-1,indexs);

        List<Integer> results = new LinkedList<>();
        for (int count : counts) results.add(count);
        return results;
    }

    //核心思想是对idx排序,时间负责度是O(n*lgn).
    //一直搞不清楚怎么保存idx.
    private static void helper(int[] nums, int left, int right, int[] indexs) {
        if (left >= right) { return; }

        int mid = (left + right) / 2;
        helper(nums, left, mid, indexs);
        helper(nums, mid + 1, right, indexs);
        int[] tempIdx = new int[right - left + 1];

        int idxL = left;
        int idxR = mid + 1;
        int idxT = 0;
        int count = 0;
        while (idxL<=mid||idxR<=right){
            if (idxL>mid){
                tempIdx[idxT++] = indexs[idxR++];
                continue;
            }else if (idxR>right){
                counts[indexs[idxL]]+=count;
                tempIdx[idxT++] = indexs[idxL++];
                continue;
            }

            if (nums[indexs[idxL]]>nums[indexs[idxR]]){
                tempIdx[idxT++] = indexs[idxR++];
                count++;
            } else {
                counts[indexs[idxL]]+=count;
                tempIdx[idxT++] = indexs[idxL++];
            }
        }

        System.arraycopy(tempIdx, 0, indexs, left, tempIdx.length);
    }
}

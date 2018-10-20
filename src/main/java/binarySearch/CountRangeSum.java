package binarySearch;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hulei on 2018/8/4.
 */
public class CountRangeSum {
    //大化小方式:1.二分法;2.一个一个加;3.长度从小到大增加
    //还需要思考
    public static void main(String[] args) {
        Assert.assertEquals(countRangeSum(new int[]{-2, 5, -1}, -1, 2), 3);
    }

    private static int small;
    private static int big;

    public static int countRangeSum(int[] nums, int lower, int upper) {
        long sums[] = new long[nums.length];
        long sum = 0;
        for (int i = 0; i <= nums.length - 1; i++) {
            sum += nums[i];
            sums[i] = sum;
        }

        small = lower;
        big = upper;
        return helper(sums, 0, sums.length - 1);
    }

    private static int helper(long[] sums, int left, int right) {
        if (right - left == 0) {
            if (sums[left] >= small && sums[left] <= big) {
                return 1;
            } else {
                return 0;
            }
        }

        int mid = left + (right - left) / 2;
        int count = helper(sums, mid + 1, right);
        for (int i = 0; i <= mid; i++) {
            int lIdx = mid;
            //右边,不包括当前
            while (lIdx + 1 <= right && sums[lIdx + 1] - sums[mid] < small) {
                lIdx++;
            }

            int uIdx = mid + 1;
            //左边包括当前
            while (uIdx <= right && sums[uIdx] - sums[mid] <= big) {
                uIdx++;
            }
            count += uIdx - lIdx - 1;
        }

        count += helper(sums, left, mid);

        List<Long> temp = new LinkedList<>();
        int first = left;
        int second = mid + 1;
        while (first <= mid || second <= right) {
            if (first > mid) {
                temp.add(sums[second++]);
                continue;
            }
            if (second > right) {
                temp.add(sums[first++]);
                continue;
            }

            if (sums[first] < sums[second]) {
                temp.add(sums[first++]);
            } else {
                temp.add(sums[second++]);
            }
        }
        for (int i = left; i <= right; i++) {
            sums[i] = temp.remove(0);
        }

        return count;
    }
}

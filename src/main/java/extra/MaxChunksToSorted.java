package extra;

import com.sun.org.apache.regexp.internal.REUtil;

import org.junit.Assert;

import java.util.Stack;

/**
 * Created by hulei on 2018/8/31.
 */
public class MaxChunksToSorted {
    public static void main(String[] args) {
        Assert.assertEquals(maxChunksToSorted(new int[]{1, 2, 0, 3, 4}), 3);
        Assert.assertEquals(maxChunksToSorted(new int[]{1, 4, 0, 3, 2}), 1);
        Assert.assertEquals(maxChunksToSorted(new int[]{1, 0, 2, 3, 4}), 4);
        Assert.assertEquals(maxChunksToSorted(new int[]{4, 2, 3, 1, 0}), 1);
        Assert.assertEquals(maxChunksToSorted(new int[]{4, 3, 2, 1, 0}), 1);
    }

    //本质是?
    //brute：遍历当前右边有比其小的，然后一直延伸 O(n)
    //一个萝卜一个坑，想象一种情况arr=[1,2,3,4];arr=[1,4,3,2]
    public static int maxChunksToSorted(int[] arr) {
        int max = 0;
        int count = 0;
        for (int idx = 0; idx <= arr.length - 1; idx++) {
            max = Math.max(max, arr[idx]);
            if (max == idx) { count++; }
        }

        return count;
    }

    public static int maxChunksToSorted2(int[] arr) {
        int idx = 0;
        int count = 0;
        while (idx <= arr.length - 1) {
            int end = findIndex(arr, arr[idx], idx + 1);
            count++;

            idx = end + 1;
        }
        return count;
    }

    private static int findIndex(int[] arr, int max, int i) {
        int end = i - 1;
        int maxInRange = max;
        for (int idx = i; idx <= arr.length - 1; idx++) {
            maxInRange = Math.max(arr[idx], maxInRange);

            if (arr[idx] < max) {
                end = idx;
                max = maxInRange;
            }
        }
        return end;
    }
}

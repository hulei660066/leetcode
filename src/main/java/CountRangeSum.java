import org.junit.Assert;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by hulei on 2018/8/21.
 */
public class CountRangeSum {
    public static void main(String[] args) {
        Assert.assertEquals(countRangeSum(new int[]{-2, 5, -1}, -2, 2), 3);
    }

    //下限是O(n*lgn)
    //思路1：mergeSort
    //思路2：从左遍历sum，然后排序已经遍历过的值。
    public static int countRangeSum(int[] nums, int lower, int upper) {
        int sunny = 0;
        int[] sum = new int[nums.length];
        for (int i = 0; i <= nums.length - 1; i++) {
            sunny += nums[i];
            sum[i] = sunny;
        }


        return 0;
    }
}

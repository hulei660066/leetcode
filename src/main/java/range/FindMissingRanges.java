package range;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hulei on 2018/8/24.
 */
public class FindMissingRanges {
    public static void main(String[] args) {
        Assert.assertEquals(findMissingRanges(new int[]{}, 1, 1), new LinkedList<>());
        Assert.assertEquals(findMissingRanges(new int[]{0, 1, 3, 50, 75}, 0, 99), new LinkedList<>());
        Assert.assertEquals(findMissingRanges(new int[]{55}, 0, 99), new LinkedList<>());
        Assert.assertEquals(findMissingRanges(new int[]{}, 0, 99), new LinkedList<>());
        Assert.assertEquals(findMissingRanges(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 0, 8), new LinkedList<>());
    }

    //套路题,如何统一开始、中间、结束判断条件.
    //防止溢出是个大课题,int类型的整数相加还是int，溢出后再赋值给long类型。
    //很有意思
    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> results = new LinkedList<>();
        for (int i = 0; i <= nums.length; i++) {
            long pre = lower - 1L;
            if (i != 0) { pre = nums[i - 1]; }
            long cur = upper + 1L;
            if (i != nums.length) { cur = nums[i]; }

            if (pre != cur && pre + 1 != cur) {
                if (pre + 1 == cur - 1) {
                    results.add("" + (pre + 1));
                } else {
                    results.add((pre + 1) + "->" + (cur - 1));
                }
            }
        }

        return results;
    }
}

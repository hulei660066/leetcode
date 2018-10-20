package extra;

import org.junit.Assert;

import java.security.acl.Owner;

/**
 * Created by hulei on 2018/8/28.
 */
public class HIndex {
    public static void main(String[] args) {
        Assert.assertEquals(hIndex(new int[]{7}), 1);
        Assert.assertEquals(hIndex(new int[]{0, 1}), 1);
        Assert.assertEquals(hIndex(new int[]{0}), 0);
        Assert.assertEquals(hIndex(new int[]{0, 0}), 0);
        Assert.assertEquals(hIndex(new int[]{3, 3, 3}), 3);
        Assert.assertEquals(hIndex(new int[]{}), 0);
        Assert.assertEquals(hIndex(new int[]{3, 0, 6, 1, 5}), 3);
    }

    //这道题给的数据是有边界的.
    //原来以为是用二分查找法,二分法不能处理有重复数字的.
    //一直搞不懂
    public static int hIndex(int[] citations) {
        int[] counts = new int[citations.length + 1];
        for (int val : citations) {
            if (val > citations.length) {
                counts[citations.length]++;
            } else {
                counts[val]++;
            }
        }

        int sum = 0;
        for (int i = counts.length - 1; i >= 0; i--) {
            sum += counts[i];
            if (i >= sum && i - 1 <= sum) {
                return sum;
            }
        }

        return sum;
    }

    //h是惟一的吗？是的
    //时间复杂度为O(n*lgn)
    public static int hIndex2(int[] citations) {
        int len = citations.length;
        quickSort(citations, 0, len - 1);
        int result = 0;

        int left = 0;
        int right = len;
        //0-len,一个都不包含到都包含
        while (left <= right) {
            int hIndex = (left + right) >>> 1;

            int pre = 0;
            if (hIndex < len) { pre = citations[len - hIndex - 1]; }
            int cur = Integer.MAX_VALUE;
            if (hIndex > 0) { cur = citations[len - hIndex]; }

            if (cur >= hIndex && pre <= hIndex) {
                result = hIndex;
                break;
            } else if (pre > hIndex) {
                left = hIndex + 1;
            } else {
                right = hIndex - 1;
            }
        }

        return result;
    }

    private static void quickSort(int[] citations, int begin, int end) {
        if (begin >= end) { return; }

        int left = begin - 1;
        int right = begin;
        int pivot = citations[end];
        while (right <= end) {
            if (citations[right] <= pivot) {
                int temp = citations[++left];
                citations[left] = citations[right];
                citations[right] = temp;
            }

            right++;
        }

        quickSort(citations, begin, left - 1);
        quickSort(citations, left + 1, end);
    }
}

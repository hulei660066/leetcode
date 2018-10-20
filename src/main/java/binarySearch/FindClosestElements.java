package binarySearch;

import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hulei on 2018/9/3.
 */
public class FindClosestElements {
    public static void main(String[] args) {
        Assert.assertEquals(findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3), Arrays.asList(1, 2, 3, 4));
    }

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> results = new LinkedList<>();
        int cur = findInsertIdx(arr, x);
        int pre = cur - 1;
        while (k > 0) {
            long preVal = Integer.MAX_VALUE;
            if (pre >= 0) { preVal = arr[pre]; }

            long curVal = Integer.MAX_VALUE;
            if (cur < arr.length) { curVal = arr[cur]; }

            if (Math.abs(preVal - x) <= Math.abs(curVal - x)) {
                results.add(0, (int) preVal);
                pre--;
            } else {
                results.add((int) curVal);
                cur++;
            }

            k--;
        }

        return results;
    }

    //0-len len个点
    private static int findInsertIdx(int[] arr, int x) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}

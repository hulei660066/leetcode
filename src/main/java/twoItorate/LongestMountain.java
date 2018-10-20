package twoItorate;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by hulei on 2018/9/15.
 */
public class LongestMountain {
    public static void main(String[] args) {
        Assert.assertEquals(longestMountain(new int[]{3, 6, 5, 6, 3, 9}), 3);
        Assert.assertEquals(longestMountain(new int[]{2, 3}), 0);
        Assert.assertEquals(longestMountain(new int[]{2, 2, 2}), 0);
        Assert.assertEquals(longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5}), 5);
    }

    public static int longestMountain(int[] A) {
        int[] counts = new int[A.length];
        //范围：1-n-1共n-1个
        for (int idx = 1; idx <= A.length - 1; idx++) {
            if (A[idx] > A[idx - 1]) {
                counts[idx] = counts[idx - 1] + 1;
            }
        }

        int max = 0;
        int count = 0;
        for (int idx = A.length - 2; idx >= 0; idx--) {
            count++;
            if (A[idx] > A[idx + 1]) {
                if (counts[idx] != 0) {
                    max = Math.max(max, counts[idx] + count + 1);
                }
            } else {
                count = 0;
            }
        }

        return max;
    }
}

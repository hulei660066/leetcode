package recursionAndItorate;

import org.junit.Assert;

import java.util.Queue;
import java.util.Set;

/**
 * Created by hulei on 9/19/18.
 */
public class CountNumbersWithUniqueDigits {
    public static void main(String[] args) {
        Assert.assertEquals(countNumbersWithUniqueDigits(1), 10);
        Assert.assertEquals(countNumbersWithUniqueDigits(2), 91);
        Assert.assertEquals(countNumbersWithUniqueDigits(3), 739);
        Assert.assertEquals(countNumbersWithUniqueDigits(4), 5275);
        Assert.assertEquals(countNumbersWithUniqueDigits(5), 32491);
        Assert.assertEquals(countNumbersWithUniqueDigits(6), 168571);
        Assert.assertEquals(countNumbersWithUniqueDigits(7), 712891);
    }

    //感觉是用递归。
    //概率论: 9*9*8*7...
    public static int countNumbersWithUniqueDigits(int n) {
        int[] counts = new int[12];
        counts[0] = 1;
        counts[1] = 9;
        for (int idx = 2; idx <= counts.length - 1; idx++) {
            counts[idx] = 11 - idx;
        }

        int count = 1;
        int result = 0;
        for (int digitLen = 0; digitLen <= n; digitLen++) {
            if (digitLen >= counts.length) { break; }

            count *= counts[digitLen];
            result += count;
        }
        return result;

        //        for (int idx = 3; idx <= 3; idx++) {
        //            Set<Integer> set = new HashSet<>();
        //            Queue<Integer> queue = new PriorityQueue<>();
        //            brute(idx, 0, 0, set, queue);
        //            System.out.print(queue);
        //            System.out.println();
        //        }
    }

    public static void brute(int len, int idx, int result, Set<Integer> set, Queue<Integer> results) {
        if (len == idx) {
            results.add(result);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (!set.contains(i)) {
                if (idx != 0 || i != 0) {
                    set.add(i);
                }
                int newResult = result * 10 + i;
                brute(len, idx + 1, newResult, set, results);
                set.remove(i);
            }
        }

    }
}

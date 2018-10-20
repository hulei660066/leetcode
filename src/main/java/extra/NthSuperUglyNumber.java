package extra;

import org.junit.Assert;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Created by hulei on 2018/9/11.
 */
public class NthSuperUglyNumber {
    public static void main(String[] args) {
        findPrime(Integer.MAX_VALUE >> 10);
        Assert.assertEquals(nthSuperUglyNumber(25, new int[]{3, 5, 7, 17, 19, 23, 29, 43, 47, 53}), 81);
        Assert.assertEquals(nthSuperUglyNumber(15, new int[]{3, 5, 7, 11, 19, 23, 29, 41, 43, 47}), 35);
        //1,2,4,7,8,13,14,16,19,26,28,32
        Assert.assertEquals(nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}), 32);
        Assert.assertEquals(nthSuperUglyNumber(1, new int[]{2, 3, 5}), 1);
        Assert.assertEquals(nthSuperUglyNumber(1, new int[]{1, 2, 5}), 1);
    }

    //按照一种秩序遍历
    //难点是如何去重,这里的关键是results是有序的
    public static int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int prime : primes) {
            queue.offer(new int[]{prime, prime, 0});
        }
        int[] results = new int[n];
        HashSet<Integer> set = new HashSet<>();
        results[0] = 1;
        int idx = 1;
        while (idx < n) {
            int[] cur = queue.poll();
            int nextPrime = cur[0];
            if (!set.contains(nextPrime)) {
                set.add(nextPrime);
                results[idx++] = nextPrime;
            }

            int base = cur[1];
            int index = cur[2];
            int next = base * results[++index];
            queue.add(new int[]{next, base, index});
        }

        return results[n - 1];
    }


    private static List<Integer> findPrime(int n) {
        boolean[] isComposite = new boolean[n];
        for (int idx = 2; idx <= isComposite.length - 1; idx++) {
            //优化一
            if (isComposite[idx]) continue;

            for (int sec = idx; sec <= isComposite.length - 1; sec++) {
                long location = (long) idx * sec;
                if (location > isComposite.length - 1) { break; }

                isComposite[(int) location] = true;
            }
        }

        for (int num = Integer.MAX_VALUE >> 10; num > 3; num--) {
//            for (int num = 38; num > 3; num--) {
            if (num % 2 == 1) { continue; }
            int half = num/2;
            boolean isHalfOk = false;
            for (int itor = half - 1; itor >= half / 2; itor--) {
                if (!isComposite[itor] && !isComposite[num - itor]) {
                    isHalfOk = true;
                    System.out.println(num + ":" + itor + " " + (num - itor)+ " " +num*num+ " " +(num - itor)*itor);
                }
            }
        }
        List<Integer> results = new LinkedList<>();
        for (int idx = 2; idx <= isComposite.length - 1; idx++) {
            if (!isComposite[idx]) {
                results.add(idx);
            }
        }

        return results;
    }
}

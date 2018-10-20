import org.junit.Assert;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by hulei on 2018/8/14.
 */
public class SmallestDistancePair {
    public static void main(String[] args) {
        Assert.assertEquals(smallestDistancePair(new int[]{1, 2, 2, 3}, 1), 0);
        Assert.assertEquals(smallestDistancePair(new int[]{1, 3, 1}, 1), 0);
    }

    //反推法:如果现在有一个结果,它应该落在哪个区间?二分法找的是结果
    public static int smallestDistancePair(int a[], int k) {
        int n = a.length;
        //时间复杂度: O(n*lgn)
        Arrays.sort(a);

        int low = a[1] - a[0];
        for (int i = 1; i < n - 1; i++)
            low = Math.min(low, a[i + 1] - a[i]);

        int high = a[n - 1] - a[0];

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (countPairs(a, mid) < k)
                low = mid + 1;
            else
                high = mid;
        }

        return low;
    }


    private static int countPairs(int[] a, int mid) {
        int n = a.length, res = 0;
        for (int i = 0; i < n; ++i) {
            int j = i;
            while (j < n && a[j] - a[i] <= mid) j++;
            res += j - i - 1;
        }
        return res;
    }

    //时间复杂度: O(n^2*lgk)
    public static int smallestDistancePair2(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>(k, (a, b) -> b - a);
        for (int first=0;first<=nums.length-1;first++){
            for (int sec=first+1;sec<=nums.length-1;sec++){
                int abs = Math.abs(nums[first] - nums[sec]);
                queue.offer(abs);
                if (queue.size() > k) {
                    queue.poll();
                }
            }
        }

        return queue.peek();
    }
}

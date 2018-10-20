package binarySearch;

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/13.
 */
public class FindKthNumber {
    public static void main(String[] args) {
        Assert.assertEquals(findKthNumber(3, 3, 5), 3);
    }
    public static int findKthNumber(int m, int n, int k) {
        int low = 1, high = m * n;

        while (low < high) {
            int mid = low + (high - low) / 2;
            int c = count(mid, m, n);
            //这里用high=mid是因为如果找不到
            if (c >= k) high = mid;
            else low = mid + 1;
        }

        return low;
    }

    private static int count(int v, int m, int n) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            int temp = Math.min(v / i , n);
            count += temp;
        }
        return count;
    }
}

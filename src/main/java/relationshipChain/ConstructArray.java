package relationshipChain;

import org.junit.Assert;

/**
 * Created by hulei on 2018/9/14.
 */
public class ConstructArray {
    public static void main(String[] args) {
        Assert.assertEquals(constructArray(10, 3), new int[]{1, 2, 3});
        Assert.assertEquals(constructArray(10, 9), new int[]{1, 2, 3});
        Assert.assertEquals(constructArray(3, 1), new int[]{1, 2, 3});
        Assert.assertEquals(constructArray(3, 2), new int[]{1, 3, 2});
    }

    //n个元素，共有n-1个关系链，所以初始化一个元素，然后遍历范围是1到n
    public static int[] constructArray(int n, int k) {
        int[] result = new int[n];
        int left = 1;
        int right = n;
        for (int idx = 0; idx <= n - 1; idx++) {
            if (k > 1) {
                if (k % 2 == 0) {
                    result[idx] = right--;
                } else {
                    result[idx] = left++;
                }
                k--;
            } else {
                result[idx] = left++;
            }
        }

        return result;
    }

    public static int[] constructArray2(int n, int k) {
        int[] result = new int[n];
        result[0] = 1;
        int left = 2;
        int right = n;
        int idx = 1;
        while (k-- > 1) {
            if (idx % 2 == 1) {
                result[idx++] = right--;
            } else {
                result[idx++] = left++;
            }
        }
        if (idx % 2 == 0) {
            while (left <= right) {
                result[idx++] = right--;
            }
        } else {
            while (left <= right) {
                result[idx++] = left++;
            }
        }

        return result;
    }
}

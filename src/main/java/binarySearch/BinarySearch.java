package binarySearch;

import org.junit.Assert;

import java.math.BigDecimal;

/**
 * Created by hulei on 2018/8/16.
 */
public class BinarySearch {
    public static void main(String[] args) {
        Assert.assertEquals(binarySearchI(new int[]{1,3,5}, 2), 1);
        Assert.assertEquals(binarySearchI(new int[]{}, 4), 0);
        Assert.assertEquals(binarySearchI(new int[]{}, 0), 0);
        Assert.assertEquals(binarySearchI(new int[]{1,3,5}, 0), 0);
        Assert.assertEquals(binarySearchI(new int[]{1,3,5}, 1), 0);
        Assert.assertEquals(binarySearchI(new int[]{1,3,5}, 3), 1);
        Assert.assertEquals(binarySearchI(new int[]{1,3,5}, 4), 2);
        Assert.assertEquals(binarySearchI(new int[]{1,3,5}, 5), 2);
        Assert.assertEquals(binarySearchI(new int[]{1,3,5}, 8), 3);
        Assert.assertEquals(binarySearchI(new int[]{1, 8}, 0), 0);
        Assert.assertEquals(binarySearchI(new int[]{1, 8}, 1), 0);
        Assert.assertEquals(binarySearchI(new int[]{1, 8}, 2), 1);
        Assert.assertEquals(binarySearchI(new int[]{1, 8}, 8), 1);
        Assert.assertEquals(binarySearchI(new int[]{1, 8}, 9), 2);
    }

    private static int binarySearchI(int nums[], int target) {
        int begin = 0;
        int right = nums.length;
        while (begin < right) {
            int mid = (begin + right) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                //对于找不到或者找的是区间，right=mid-1是不对的
                right = mid;
            } else {
                begin = mid + 1;
            }
        }

        return begin;
}

    private static int binarySearch(int nums[], int target) {
        int begin = 0;
        int right = nums.length - 1;
        while (begin <= right) {
            int mid = (begin + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                //因为是等号，所以如果用right=mid会对于begin=mid=right的情况会陷入循环。
                right = mid - 1;
            } else {
                begin = mid + 1;
            }
        }

        return begin;

    }

    /**
     * Created by hulei on 2018/8/1.
     */
    public static class SmallestGoodBase {
        //本质是二分查找法,不需要任何公式,只要知道sum怎么求出来就行;
        //注意事项:涉及到数字相加,相乘的,一律需要考虑溢出,方法包括用更大的数类型执行操作;包括边乘边做判断
        public static void main(String[] args) {
            Assert.assertEquals(smallestGoodBase("1000000000000000000"), "999999999999999999");
            Assert.assertEquals(smallestGoodBase("2251799813685247"), "2");
            Assert.assertEquals(smallestGoodBase("3"), "2");
            Assert.assertEquals(smallestGoodBase("13"), "3");
            Assert.assertEquals(smallestGoodBase("4681"), "8");
        }

        public static String smallestGoodBase(String n) {
            long num = Long.valueOf(n);
            //逆序体现了最小
            for (int pow = 60; pow >= 1; pow--) {
                long min = 2;
                long max = num - 1;
                while (min <= max) {
                    long mid = (max + min) / 2;
                    BigDecimal sum = BigDecimal.valueOf(0);
                    for (int i = 0; i <= pow; i++) {
                        sum = sum.multiply(BigDecimal.valueOf(mid)).add(BigDecimal.ONE);
                    }

                    int isOverFlow = sum.compareTo(BigDecimal.valueOf(Long.MAX_VALUE));
                    if (isOverFlow==1){
                        max = mid - 1;
                        continue;
                    }

                    long lSum = sum.longValue();
                    if (lSum > num) {
                        max = mid - 1;
                    } else if (lSum == num) {
                        return String.valueOf(mid);
                    } else {
                        min = mid + 1;
                    }
                }
            }

            return String.valueOf(num - 1);
        }
    }
}

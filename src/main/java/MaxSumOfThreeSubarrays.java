import org.junit.Assert;

/**
 * Created by hulei on 2018/8/10.
 */
public class MaxSumOfThreeSubarrays {
    public static void main(String[] args) {
        Assert.assertEquals(maxSumOfThreeSubarrays(new int[]{4, 5, 10, 6, 11, 17, 4, 11, 1, 3}, 1), new int[]{4, 5, 7});
        Assert.assertEquals(maxSumOfThreeSubarrays(new int[]{1, 2, 1, 2, 6, 7, 5, 1}, 2), new int[]{0, 3, 5});
        Assert.assertEquals(maxSumOfThreeSubarrays(new int[]{1, 17, 16, 10, 6, 18, 1, 1, 16, 12, 9, 20, 14, 15, 5, 17, 20, 16, 4, 3, 3, 17, 13, 9,
                16, 3, 8, 8, 14, 12, 20, 14, 20, 9, 1, 12, 14, 17, 15, 19}, 4), new int[]{10, 29, 36});
    }

    //三个元素的话,考虑先定中间的那个,然后再考虑左右边.
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        long[] sunny = new long[nums.length];
        sunny[0]=nums[0];
        for (int i=1;i<=nums.length-1;i++){
            sunny[i]=nums[i]+sunny[i-1];
        }

        long[] leftMaxs = new long[nums.length];
        int[] leftIndexs = new int[nums.length];
        long leftMax=0;
        int leftIndex=0;
        for (int left = 0; left <= nums.length - k; left++) {
            int right = left + k - 1;

            long sum = sunny[right];
            if (left - 1 >= 0) {
                sum -= sunny[left - 1];
            }

            if (leftMax<sum){
                leftMax=sum;
                leftIndex=left;
            }
            leftMaxs[right]=leftMax;
            leftIndexs[right]=leftIndex;
        }

        long[] rightMaxs = new long[nums.length];
        int[] rightIndexs = new int[nums.length];
        long rightMax=0;
        int rightIndex=0;
        for (int right = nums.length-1; right >= k-1; right--) {
            int left = right - k + 1;
            long sum=sunny[right];
            if (left - 1 > 0) {
                sum -= sunny[left - 1];
            }

            if (rightMax<sum){
                rightMax=sum;
                rightIndex=left;
            }
            rightMaxs[left]=rightMax;
            rightIndexs[left]=rightIndex;
        }

        long bigMax=0;
        int bigIndex=0;
        for (int pre = k; pre <= nums.length - 2 * k; pre++) {
            int pos = pre + k - 1;
            long sum = sunny[pos] - sunny[pre - 1];
            if (sum + leftMaxs[pre - 1] + rightMaxs[pos + 1] > bigMax) {
                bigMax = sum + leftMaxs[pre - 1] + rightMaxs[pos + 1];
                bigIndex = pre;
            }
        }

        int[] results = new int[]{leftIndexs[bigIndex - 1], bigIndex, rightIndexs[bigIndex + k]};
        return results;
    }
}

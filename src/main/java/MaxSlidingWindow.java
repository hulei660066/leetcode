import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hulei on 2018/8/7.
 */
public class MaxSlidingWindow {
    public static void main(String[] args) {
        Assert.assertEquals(maxSlidingWindow(new int[]{}, 13), new int[]{});
        Assert.assertEquals(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 13), new int[]{3, 3, 5, 5, 6, 7});
        Assert.assertEquals(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3), new int[]{3, 3, 5, 5, 6, 7});
    }
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (k > nums.length) { return new int[0]; }

        List<Integer> lists = new LinkedList<>();
        int cur=0;
        int pre=0;
        int[] results = new int[nums.length - k + 1];
        int index = 0;
        while (cur<=nums.length-1){
           while (!lists.isEmpty()&&lists.get(lists.size()-1)<nums[cur]){
               lists.remove(lists.size()-1);
           }
            lists.add(nums[cur]);
            if (cur - pre + 1 > k) {
                if (lists.size() > 0 && nums[pre] == lists.get(0)) {
                    lists.remove(0);
                }
                pre++;
            }

            if (cur-pre+1==k){
                int temp = nums[pre];
                if (lists.size()!=0){
                    temp=lists.get(0);
                }
                results[index++]=temp;
            }

            cur++;
        }

        return results;
    }
}

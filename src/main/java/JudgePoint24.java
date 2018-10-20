import org.junit.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hulei on 2018/8/11.
 */
public class JudgePoint24 {
    public static void main(String[] args) {
        Assert.assertEquals(judgePoint24(new int[]{4, 1, 8, 7}), true);
        Assert.assertEquals(judgePoint24(new int[]{1, 2, 1, 2}), false);
    }

    public static boolean judgePoint24(int[] nums) {
        return helper(nums, 0);
    }

    //先做全排列
    private static boolean helper(int[] nums, int idx) {
        if (idx == nums.length) { return func(nums); }
        for (int i = idx; i <= nums.length - 1; i++) {
            int temp = nums[idx];
            nums[idx] = nums[i];
            nums[i] = temp;
            boolean is24 = helper(nums, idx+1);
            if (is24) { return true; }

            temp = nums[idx];
            nums[idx] = nums[i];
            nums[i] = temp;
        }
        return false;
    }

    //然后做计算
    public static boolean func(int[] nums) {
        Map<Integer, Set<Double>> map = new HashMap<>();
        for (int len=1;len<=nums.length;len++){
            for (int begin=0;begin<=nums.length-len;begin++){
                int end=begin+len-1;
                if (len==1){
                    if (!map.containsKey((begin+1)*(end+1))){
                        map.put((begin+1)*(end+1),new HashSet<>());
                    }
                    map.get((begin+1)*(end+1)).add((double) nums[begin]);
                    continue;
                }

                if (!map.containsKey((begin+1)*(end+1))) { map.put((begin+1)*(end+1), new HashSet<>()); }
                Set<Double> sets= map.get((begin+1)*(end+1));
                for (int i = begin + 1; i <= end; i++) {
                    Set<Double> lefts = map.get((begin+1) * i);
                    Set<Double> rights = map.get((i+1) * (end+1));
                    for (double left:lefts){
                        for (double right:rights){
                            sets.add(left+right);
                            sets.add(left-right);
                            sets.add(left*right);
                            sets.add(left/right);
                        }
                    }
                }
            }
        }


        for (double num : map.get(4)) {
            if (Math.abs(24 - num) < 0.0001) {
                return true;
            }
        }

        return false;
    }
}

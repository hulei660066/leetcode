package relationshipChain;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by hulei on 2018/9/13.
 */
public class IsPossible {
    public static void main(String[] args) {
        Assert.assertEquals(isPossible(new int[]{1, 2, 3, 5, 6, 7}), true);
        Assert.assertEquals(isPossible(new int[]{1, 2, 3, 3, 4, 4, 5, 5}), true);
        Assert.assertEquals(isPossible(new int[]{1, 2, 3, 3, 4, 4, 5, 6}), true);
        Assert.assertEquals(isPossible(new int[]{1, 2, 3, 4, 4, 5}), false);
        Assert.assertEquals(isPossible(new int[]{1}), false);
        Assert.assertEquals(isPossible(new int[]{1, 2, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6}), true);
        Assert.assertEquals(isPossible(new int[]{1, 2, 3, 3, 4, 5}), true);
    }

    //直接遍历
    //pre<cur,继续遍历
    //pre==cur,继续
    //pre==cur+1,一层
    //pre>=cur+1,断了,清查只能有一个
    public static boolean isPossible(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int count = 0;
        for (int idx = 0; idx <= nums.length - 1; idx++) {
            count++;
            if (idx == nums.length - 1 || nums[idx] != nums[idx + 1]) {
                list.add(count);
                count = 0;
                if (idx == nums.length - 1 || nums[idx] + 1 != nums[idx + 1]) {
                    list.add(0);
                }
            }
        }
        int[] consecutiveFromPre = new int[list.size()];
        for (int idx = 0; idx <= list.size() - 1; idx++) {
            list.set(idx, list.get(idx) - consecutiveFromPre[idx]);
            if (idx + 1 <= list.size() - 1) {
                consecutiveFromPre[idx + 1] += consecutiveFromPre[idx];
            }
            Integer num = list.get(idx);
            if (num <= 0) { continue; }

            if (idx + 2 <= list.size() - 1 && list.get(idx + 1) >= num && list.get(idx + 2) >= num) {
                list.set(idx, 0);
                list.set(idx + 1, list.get(idx + 1) - num);
                list.set(idx + 2, list.get(idx + 2) - num);
                if (idx + 3 <= consecutiveFromPre.length - 1) {
                    consecutiveFromPre[idx + 3] += num;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    public static boolean isPossible2(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        Map<Integer, Integer> isConnectPre = new HashMap<>();
        for (int num : nums) {
            if (counts.get(num) == 0) { continue; }
            //优先选择连接前面
            if (isConnectPre.getOrDefault(num, 0) > 0) {
                counts.put(num, counts.get(num) - 1);
                isConnectPre.put(num, isConnectPre.get(num) - 1);
                isConnectPre.put(num + 1, isConnectPre.getOrDefault(num + 1, 0) + 1);
            } else if (counts.getOrDefault(num + 1, 0) > 0 && counts.getOrDefault(num + 2, 0) > 0) {
                counts.put(num, counts.get(num) - 1);
                counts.put(num + 1, counts.get(num + 1) - 1);
                counts.put(num + 2, counts.get(num + 2) - 1);
                isConnectPre.put(num + 3, isConnectPre.getOrDefault(num + 3, 0) + 1);
            } else {
                return false;
            }
        }

        return true;
    }
}

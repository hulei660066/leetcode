package recursionAndItorate;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by hulei on 2018/9/12.
 */
public class LargestDivisibleSubset {
    public static void main(String[] args) {
        Assert.assertEquals(largestDivisibleSubset(new int[]{1}), new int[]{1});
        Assert.assertEquals(largestDivisibleSubset(new int[]{}), new int[]{});
        Assert.assertEquals(largestDivisibleSubset(new int[]{1, 2, 3}), new int[]{1, 2});
        Assert.assertEquals(largestDivisibleSubset(new int[]{4, 8, 10, 240}), new int[]{4, 8, 240});
        Assert.assertEquals(largestDivisibleSubset(new int[]{1, 2, 3}), new int[]{1, 2});
        Assert.assertEquals(largestDivisibleSubset(new int[]{1, 2, 4, 8}), new int[]{1, 2, 4, 8});
    }

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int idx = 0; idx <= nums.length - 1; idx++) {
            map.put(nums[idx], new LinkedList<>());
        }

        int count = 0;
        int key = 0;
        for (int first = 0; first <= nums.length - 1; first++) {
            List<Integer> list = new LinkedList<>(map.get(nums[first]));
            list.add(nums[first]);
            for (int sec = first; sec <= nums.length - 1; sec++) {
                if (nums[sec] % nums[first] != 0) { continue; }

                int next = nums[sec];
                if (map.containsKey(next)) {
                    if (map.get(next).size() < list.size()) {
                        map.put(next, list);
                    }
                }
            }

            if (count < list.size()) {
                count = list.size();
                key = nums[first];
            }
        }

        return map.getOrDefault(key, new LinkedList<>());
    }
}

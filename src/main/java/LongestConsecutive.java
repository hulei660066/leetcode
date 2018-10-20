import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hulei on 2018/8/8.
 */
public class LongestConsecutive {
    public static void main(String[] args) {
        Assert.assertEquals(longestConsecutive(new int[]{1,3,5,2,4}), 5);
        Assert.assertEquals(longestConsecutive(new int[]{1, 2, 0, 1}), 3);
        Assert.assertEquals(longestConsecutive(new int[]{1, 2, 0, 1}), 3);
        Assert.assertEquals(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}), 4);
    }

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) { return 0; }

        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> counts = new HashMap<>();
        int max = 1;
        for (int num:nums){
            if (map.containsKey(num)) { continue; }

            map.put(num, num);
            counts.put(num, 1);

            if (map.containsKey(num-1)){
                int boss = findBoss(map, num - 1);
                map.put(num, boss);
                int count = counts.get(boss) + counts.get(num);
                counts.put(boss, count);
                max=Math.max(max,count);
            }

            if (map.containsKey(num+1)){
                int boss = findBoss(map, num);
                map.put(num + 1, boss);
                int count = counts.get(num + 1) + counts.get(boss);
                counts.put(boss, count);
                max=Math.max(max,count);
            }
        }

        return max;
    }

    private static int findBoss(Map<Integer, Integer> map, int num) {
        Integer boss = map.get(num);

        if (boss == num) {
            return num;
        } else {
            int bossBoss = findBoss(map, boss);
            map.put(num, bossBoss);
            return bossBoss;
        }
    }
}

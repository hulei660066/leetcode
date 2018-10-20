package design;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static greed.TrapRainWater.trapRainWater;

/**
 * Created by hulei on 2018/8/28.
 */
//一个小换位tricky.完美的逻辑链：如果之间有序的话就用list，否则用array
public class RandomizedSet {
    //follow up有重复的val，怎么办？
    public static void main(String[] args) {
        Assert.assertEquals(trapRainWater(new int[][]{new int[]{1, 4, 3}, new int[]{3, 2, 1}, new int[]{2, 3, 3}}), 0);
    }

    /**
     * Initialize your data structure here.
     */
    Map<Integer, Integer> map;
    List<Integer> array;
    Random random;

    public RandomizedSet() {
        map = new HashMap<>();
        array = new ArrayList<>();
        random = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            array.add(val);
            map.put(val, array.size() - 1);
            return true;
        }

        return false;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            Integer idx = map.get(val);
            if (idx != array.size() - 1) {
                Integer swapValue = array.get(array.size() - 1);
                array.set(idx, swapValue);
                map.put(swapValue, idx);
            }
            array.remove(array.size() - 1);
            map.remove(val);
            return true;
        }

        return false;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return array.get(random.nextInt(array.size()));
    }
}

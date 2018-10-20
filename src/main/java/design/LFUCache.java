package design; /**
 * Created by hulei on 2018/8/14.
 */

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Your design.LFUCache object will be instantiated and called as such: design.LFUCache obj = new design.LFUCache(capacity); int param_1 = obj.get(key);
 * obj.put(key,value);
 */
class LFUCache {
    public static void main(String[] args) {
        LFUCache obj = new LFUCache(2);
        obj.put(1,1);
        obj.put(2,2);
        obj.get(1);
        obj.put(3,3);
        obj.get(2);
        obj.get(3);
        obj.put(4,4);
        obj.get(1);
        obj.get(3);
        obj.get(4);
    }
    Map<Integer, int[]> key2val;
    Map<Integer, Set<Integer>> count2keys;
    int min;
    int capacity;

    public LFUCache(int capacity) {
        this.key2val = new HashMap<>();
        this.count2keys = new HashMap<>();
        this.min = Integer.MAX_VALUE;
        this.capacity = capacity;
    }

    public void put(int key, int value) {
        if (capacity <= 0) { return; }

        //如果有重复的怎么办?
        if (key2val.containsKey(key)) {
            key2val.get(key)[0] = value;
            get(key);
            return;
        }

        if (key2val.size() == this.capacity) {
            Integer removeKey = count2keys.get(min).iterator().next();
            count2keys.get(min).remove(removeKey);
            int count = key2val.get(removeKey)[1];
            count2keys.get(count).remove(removeKey);
            key2val.remove(removeKey);
        }

        key2val.put(key, new int[]{value, 1});
        if (!count2keys.containsKey(1)) {
            count2keys.put(1, new LinkedHashSet<>());
        }
        count2keys.get(1).add(key);
        min = 1;
    }

    public int get(int key) {
        if (!key2val.containsKey(key)) { return -1; }

        int[] content = key2val.get(key);
        Set<Integer> keys = count2keys.get(content[1]);
        if (content[1] == min && keys.size() == 0) min++;
        content[1]++;
        if (count2keys.get(content[1])==null){
           count2keys.put(content[1],new LinkedHashSet<>());
        }
        count2keys.get(content[1]).add(key);

        return content[0];
    }
}


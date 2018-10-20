import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hulei on 2018/8/11.
 */
public class FindRedundantConnection {
    public static void main(String[] args) {
        Assert.assertEquals(findRedundantConnection(new int[][]{new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4}, new int[]{1, 4}, new int[]{1,
                5}}), new int[]{1, 4});
        Assert.assertEquals(findRedundantConnection(new int[][]{new int[]{1, 2}, new int[]{1, 3}, new int[]{2, 3}}), new int[]{2, 3});
    }

    public static int[] findRedundantConnection(int[][] edges) {
        Map<Integer, Integer> map = new HashMap<>();

        int[] result = new int[2];
        for (int[]edge:edges){
            int boss1=findBoss(map,edge[0]);
            int boss2=findBoss(map,edge[1]);
            if (boss1!=boss2){
                map.put(boss2,boss1);
            }else {
                result = edge;
                break;
            }
        }

        return result;
    }

    private static int findBoss(Map<Integer, Integer> map, int key) {
        if (!map.containsKey(key)) { map.put(key, key); }

        Integer boss = map.get(key);
        if (boss!=key){
            boss = findBoss(map, boss);
            map.put(key,boss);
        }

        return boss;
    }
}

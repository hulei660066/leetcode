import org.junit.Assert;

/**
 * Created by hulei on 2018/8/21.
 */
public class AlienOrder {
    public static void main(String[] args) {
        Assert.assertEquals(alienOrder(new String[]{"zy", "zx"}), "yxz");
        Assert.assertEquals(alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}), "wertf");
        Assert.assertEquals(alienOrder(new String[]{"z", "x"}), "zx");
    }

    //这道题思路在于topo 排序，包括环检测,一些corner case，比如顺序不确定的字符
    public static String alienOrder(String[] words) {
        char[] map = new char[26];
        for (int i = 1; i <= words.length - 1; i++) {
            for (char c : words[i].toCharArray()) {
                map[c - 'a'] = c;
            }
        }

        for (int i = 1; i <= words.length - 1; i++) {
            String pre = words[i - 1];
            String cur = words[i];
            int preIdx = 0;
            int curIdx = 0;
            while (preIdx <= pre.length() - 1 && curIdx <= cur.length() - 1) {
                if (pre.charAt(preIdx) != cur.charAt(curIdx)) {
                    map[pre.charAt(preIdx) - 'a'] = cur.charAt(curIdx);
                    break;
                }

                preIdx++;
                curIdx++;
            }
        }
        //0 没有遍历过；-1正在遍历；1遍历过
        int[] isVisited = new int[26];
        StringBuilder sb = new StringBuilder();
        boolean isCyclic = false;
        for (int c = map.length - 1; c >= 0; c--) {
            if (map[c] != 0) {
                if (map[c] != 0 && isVisited[c] == 0) {
                    boolean temp = topologicallySort(map, c, isVisited, sb);
                    isCyclic = isCyclic | temp;
                }
            }
        }

        return isCyclic ? "" : sb.toString();
    }

    private static boolean topologicallySort(char[] map, int c, int[] isVisited, StringBuilder sb) {
        isVisited[c] = -1;


        boolean isCyclic = false;
        int newC = map[c] - 'a';
        //有next;并且next不是自己
        if (map[c] != 0 && map[c] != c + 'a') {
            if (isVisited[newC] == -1) { return true; }

            if (isVisited[newC] == 0) { isCyclic = topologicallySort(map, newC, isVisited, sb); }
        }
        sb.insert(0, (char) (c + 'a'));
        isVisited[c] = 1;

        return isCyclic;
    }
}

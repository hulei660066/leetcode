import org.junit.Assert;

/**
 * Created by hulei on 2018/9/1.
 */
public class FindReplaceString {
    public static void main(String[] args) {
        Assert.assertEquals(findReplaceString("abcd", new int[]{0, 2}, new String[]{"a", "cd"}, new String[]{"eee", "ffff"}), "eeebffff");
        Assert.assertEquals(findReplaceString("abcd", new int[]{0, 2}, new String[]{"ab", "ec"}, new String[]{"eee", "ffff"}), "eeecd");
    }

    //对指标排序；然后逆向遍历即可.
    public static String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        int[] temp = new int[indexes.length];
        for (int idx = 0; idx <= temp.length - 1; idx++) { temp[idx] = idx; }
        sortByIdx(indexes, 0, indexes.length - 1, temp);

        StringBuilder sb = new StringBuilder(S);
        for (int itor = temp.length - 1; itor >= 0; itor--) {
            int idx = temp[itor];
            int end = indexes[idx] + sources[idx].length();
            if (S.substring(indexes[idx], end).equals(sources[idx])) {
                sb.replace(indexes[idx], end, targets[idx]);
            }
        }

        return sb.toString();
    }

    private static void sortByIdx(int[] indexes, int begin, int end, int[] temp) {
        if (begin >= end) { return; }

        int left = begin - 1;
        int right = begin;
        int pivot = indexes[temp[end]];
        while (right <= end) {
            if (indexes[temp[right]] <= pivot) {
                int swap = temp[++left];
                temp[left] = temp[right];
                temp[right] = swap;
            }

            right++;
        }

        sortByIdx(indexes, begin, left - 1, temp);
        sortByIdx(indexes, left + 1, end, temp);
    }
}

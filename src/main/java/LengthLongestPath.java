import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hulei on 2018/8/22.
 */
public class LengthLongestPath {
    public static void main(String[] args) {
        Assert.assertEquals(lengthLongestPath("a.txt"), 5);
        Assert.assertEquals(lengthLongestPath("a"), 0);
        Assert.assertEquals(lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"), 20);
        Assert.assertEquals(lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"), 32);
    }

    //1.字符串解析,用\n分隔符split,这样既方便有可以把连接符去掉
    //2.加连接符判断是否是开头
    //3.corner case:空输入；无目录的文件；无文件的目录；
    public static int lengthLongestPath(String input) {
        int result = 0;
        String[] paths = input.split("\n");
        //还有一种思想是用栈
        Map<Integer, Integer> level2Dir = new HashMap<>();

        for (String path : paths) {
            String[] split = path.split("\t");
            int level = split.length - 1;
            int curLen = split[split.length - 1].length();

            int preLen = 0;
            int midLen = 0;
            if (level != 0) {
                preLen = level2Dir.get(level - 1);
                midLen = 1;
            }

            int totalLen = preLen + midLen + curLen;

            boolean isFile = path.contains(".");
            if (isFile) {
                result = Math.max(result, totalLen);
            } else {
                level2Dir.put(level, totalLen);
            }
        }

        return result;
    }

    public static int lengthLongestPath2(String input) {
        int result = 0;
        int idx = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (idx <= input.length() - 1) {
            int[] pair = parseString(input, idx);
            int newIdx = pair[0];
            int level = pair[1];
            int isFile = pair[2];

            int returnLen = 1;
            if (newIdx == input.length()) { returnLen = 0; }
            int len = newIdx - idx - returnLen - level;

            int splitLen = 1;
            if (level == 0) { splitLen = 0; }
            int totalLen = map.getOrDefault(level - 1, 0) + splitLen + len;
            if (isFile == 1) {
                result = Math.max(result, totalLen);
            } else {
                map.put(level, totalLen);
            }

            idx = newIdx;
        }

        return result;
    }

    private static int[] parseString(String input, int idx) {
        int numT = 0;
        int isFile = 0;
        while (idx <= input.length() - 1) {
            if (input.charAt(idx) == '.') { isFile = 1; }
            if (input.charAt(idx) == '\t') { numT++; }
            if (input.charAt(idx) == '\n') {
                idx++;
                break;
            }

            idx++;
        }

        return new int[]{idx, numT, isFile};
    }
}

package math;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Created by hulei on 2018/8/29.
 */
public class GenerateAbbreviations {
    public static void main(String[] args) {
        Assert.assertEquals(generateAbbreviations("word"), new LinkedList<>());
        Assert.assertEquals(generateAbbreviations("my"), new LinkedList<>());
        Assert.assertEquals(generateAbbreviations(""), new LinkedList<>());
    }

    //用递归(DFS)是不是好一点？
    public static List<String> generateAbbreviations3(String word) {
        List<String> results = new LinkedList<>();
        helper(word, 0, results);
        return results;
    }

    private static void helper(String word, int begin, List<String> results) {
        if (begin == word.length()) {
            int count = 0;
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i <= word.length() - 1; i++) {
                if (word.charAt(i) != '1') {
                    temp.append(word.charAt(i));
                } else {
                    count++;
                }
                if (count != 0 && (i == word.length() - 1 || word.charAt(i + 1) != '1')) {
                    temp.append(count);
                    count = 0;
                }
            }
            results.add(temp.toString());
            return;
        }

        StringBuilder sb = new StringBuilder(word);
        sb.setCharAt(begin, '1');
        helper(sb.toString(), begin + 1, results);

        //no change
        helper(word, begin + 1, results);
    }

    //迭代：最简单的办法就是利用数学知识，对应起来
    //对称的美
    //字符串解析
    //要不用一个temp存放；要不倒着遍历
    public static List<String> generateAbbreviations(String word) {
        List<String> results = new LinkedList<>();
        for (int binary = 0; binary <= Math.pow(2, word.length()) - 1; binary++) {
            int count = 0;
            StringBuilder sb = new StringBuilder();
            int seed = (int) Math.pow(2, word.length() - 1);
            for (int idx = 0; idx <= word.length() - 1; idx++) {
                int isOne = (seed >> idx) & binary;
                if (isOne == 0) {
                    sb.append(word.charAt(idx));
                    continue;
                }

                count++;
                boolean isEnd = idx == word.length() - 1;
                boolean isNextZero = ((seed >> (idx + 1)) & binary) == 0;
                if (count != 0 && (isEnd || isNextZero)) {
                    sb.append(count);
                    count = 0;
                }

            }
            results.add(sb.toString());
        }
        return results;
    }

    public static List<String> generateAbbreviations2(String word) {
        Set<String> results = new HashSet<>();
        results.add(word);

        Queue<String> queue = new LinkedList<>();
        queue.offer(word);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            for (int i = 0; i <= cur.length() - 1; i++) {
                char c = cur.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    StringBuilder sb = new StringBuilder(cur);
                    int sum = 1;

                    int postIdx = getIdx(sb, i, 1);
                    if (postIdx != i) {
                        sum += Integer.valueOf(sb.substring(i + 1, postIdx + 1));
                        sb.delete(i + 1, postIdx + 1);
                    }
                    int preIdx = getIdx(sb, i, -1);
                    if (preIdx != i) {
                        sum += Integer.valueOf(sb.substring(preIdx, i));
                        sb.deleteCharAt(i);
                        sb.insert(i, sum);
                        sb.delete(preIdx, i);
                    } else {
                        sb.deleteCharAt(i);
                        sb.insert(i, sum);
                    }

                    results.add(sb.toString());
                    queue.offer(sb.toString());
                }
            }
        }

        return new ArrayList<>(results);
    }

    private static int getIdx(StringBuilder cur, int idx, int dir) {
        while (idx + dir >= 0 && idx + dir <= cur.length() - 1) {
            char c = cur.charAt(idx + dir);
            if (c >= 'a' && c <= 'z') {
                return idx;
            }
            idx += dir;
        }
        return idx;
    }
}

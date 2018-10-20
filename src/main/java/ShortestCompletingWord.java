import com.sun.tools.corba.se.idl.toJavaPortable.Helper;

import org.junit.Assert;

/**
 * Created by hulei on 2018/9/5.
 */
public class ShortestCompletingWord {
    public static void main(String[] args) {
        Assert.assertEquals(shortestCompletingWord("1s3 PSt", new String[]{"step", "steps", "stripe", "stepple"}), "steps");
    }

    //字符串匹配
    public static String shortestCompletingWord(String licensePlate, String[] words) {
        int[] counts = new int[26];
        int count = 0;
        for (char c : licensePlate.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                c = (char) ('a' + c - 'A');
            }
            if (c >= 'a' && c <= 'z') {
                counts[c - 'a']++;
                count++;
            }
        }


        String result = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        for (String word : words) {
            int[] temp = new int[26];
            int tempCount = count;
            for (char c : word.toCharArray()) {
                temp[c - 'a']++;
                if (temp[c - 'a'] == counts[c - 'a']) {
                    tempCount -= counts[c - 'a'];
                }
            }
            if (tempCount == 0 && result.length() > word.length()) {
                result = word;
            }
        }
        return result;
    }
}

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/6.
 */
public class LongestSubstring {
    public static void main(String[] args) {
        Assert.assertEquals(longestSubstring("bbaaacbd", 3), 3);
        Assert.assertEquals(longestSubstring("abcdabc", 0), 7);
        Assert.assertEquals(longestSubstring("abcdabc", 2), 0);
        Assert.assertEquals(longestSubstring("abcdabc", 1), 7);
        Assert.assertEquals(longestSubstring("", 3), 0);
        Assert.assertEquals(longestSubstring("abcabcabcdabc", 3), 9);
        Assert.assertEquals(longestSubstring("ababbc", 2), 5);
        Assert.assertEquals(longestSubstring("aaabb", 3), 3);
    }
    public static int longestSubstring(String s, int k) {
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        boolean isPure=true;
        for (char c:s.toCharArray()){
            if (counts[c-'a']<k){
                isPure=false;
                break;
            }
        }
        if (isPure) { return s.length(); }

        int pre=0;
        int result=0;
        for(int i=0;i<=s.length();i++){
            if (i==s.length()||counts[s.charAt(i)-'a']<k){
                int len = longestSubstring(s.substring(pre, i), k);
                result=Math.max(result,len);
                pre=i+1;
            }

        }
        return result;
    }
}

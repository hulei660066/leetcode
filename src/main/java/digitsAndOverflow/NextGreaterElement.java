package digitsAndOverflow;

import com.sun.xml.internal.bind.v2.model.core.ID;

import org.junit.Assert;

/**
 * Created by hulei on 2018/9/5.
 */
public class NextGreaterElement {
    public static void main(String[] args) {
        Assert.assertEquals(nextGreaterElement(1999999999), -1);
        Assert.assertEquals(nextGreaterElement(2431), 3124);
        Assert.assertEquals(nextGreaterElement(12), 21);
        Assert.assertEquals(nextGreaterElement(432), -1);
    }

    public static int nextGreaterElement(int n) {
        StringBuilder str = new StringBuilder("" + n);
        int idx;
        for (idx = str.length() - 1; idx >= 1; idx--) {
            if (str.charAt(idx - 1) < str.charAt(idx)) {
                break;
            }
        }

        if (idx == 0) { return -1; }
        int left = idx;
        int right = str.length() - 1;
        while (left < right) {
            char temp = str.charAt(left);
            str.setCharAt(left, str.charAt(right));
            str.setCharAt(right, temp);
            left++;
            right--;
        }

        for (int i = idx; i <= str.length() - 1; i++) {
            if (str.charAt(i) > str.charAt(idx - 1)) {
                char temp = str.charAt(idx - 1);
                str.setCharAt(idx - 1, str.charAt(i));
                str.setCharAt(i, temp);
                break;
            }
        }

        long longVal = Long.parseLong(str.toString());
        if (longVal>Integer.MAX_VALUE){
            return -1;
        }

        return (int) longVal;
    }
}

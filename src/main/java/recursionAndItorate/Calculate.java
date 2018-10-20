package recursionAndItorate;

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/21.
 */
public class Calculate {
    public static void main(String[] args) {
        Assert.assertEquals(calculate("(1+(4+5+2)-3)+(6+8)"), 23);
        Assert.assertEquals(calculate("1 + 1"), 2);
        Assert.assertEquals(calculate(" 2-1 + 2 "), 3);
    }

    public static int calculate(String s) {
        int idx = 0;
        Object[] helper = helper(s, idx);
        return (int) helper[1];
    }

    private static Object[] helper(String s, int idx) {
        int num = 0;
        boolean isAdd = true;
        while (idx <= s.length() - 1) {
            Object[] token = token(s, idx);
            int type = (int) token[0];

            switch (type) {
                case 3:
                    token = helper(s, (int) token[2]);
                case 1:
                    num = isAdd ? num + (int) token[1] : num - (int) token[1];
                    break;

                case 2:
                    isAdd = (char) token[1] == '+';
                    break;

            }
            idx = (int) token[2];

            if (type == 0 || type == 4) { break; }
        }

        return new Object[]{1, num, idx};
    }

    //空格 数字 符合 括号
    private static Object[] token(String s, int idx) {
        while (idx != s.length() && s.charAt(idx) == ' ') {
            idx++;
        }

        Object[] result = new Object[]{0, 0, idx};
        if (idx == s.length()) { return result; }

        char c = s.charAt(idx++);
        if (c >= '0' && c <= '9') {
            int num = c - '0';
            while (idx != s.length() && (s.charAt(idx) >= '0' && s.charAt(idx) <= '9')) {
                num = num * 10 + s.charAt(idx) - '0';
                idx++;
            }
            result = new Object[]{1, num, idx};
        } else if (c == '+' || c == '-') {
            result = new Object[]{2, c, idx};
        } else if (c == '(') {
            result = new Object[]{3, c, idx};
        } else if (c == ')') {
            result = new Object[]{4, c, idx};
        }

        return result;
    }
}
package stack;

import org.junit.Assert;

import java.util.Stack;

/**
 * Created by hulei on 2018/9/11.
 */
public class IsValidSerialization {
    public static void main(String[] args) {
        Assert.assertEquals(isValidSerialization("1,#,#"), true);
        Assert.assertEquals(isValidSerialization("1"), false);
        Assert.assertEquals(isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"), true);
        Assert.assertEquals(isValidSerialization("1,#"), false);
        Assert.assertEquals(isValidSerialization("9,#,#,1"), false);
    }

    //找规律
    public static boolean isValidSerialization(String preorder) {
        String[] list = preorder.split(",");
        Stack<String> stack = new Stack<>();
        for (int idx = 0; idx <= list.length - 1; idx++) {
            if (list[idx].equals("#")) {
                while (!stack.isEmpty() && stack.peek().equals("#")) {
                    stack.pop();
                    if (stack.isEmpty()) {
                        return false;
                    }
                    stack.pop();
                }
            }
            stack.push(list[idx]);
        }

        return stack.size() == 1 && stack.peek().equals("#");
    }
}

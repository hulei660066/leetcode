package stack;

import org.junit.Assert;

import java.util.Stack;

/**
 * Created by hulei on 2018/9/5.
 */
public class DailyTemperatures {
    public static void main(String[] args) {
        Assert.assertEquals(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}), new int[]{1, 1, 4, 2, 1, 1, 0, 0});
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] results = new int[temperatures.length];
        for (int idx = 0; idx <= temperatures.length - 1; idx++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[idx]) {
                Integer pop = stack.pop();
                results[pop] = idx - pop;
            }

            stack.push(idx);
        }

        return results;
    }
}

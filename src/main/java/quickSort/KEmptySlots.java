package quickSort;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by hulei on 2018/8/17.
 */
public class KEmptySlots {
    public static void main(String[] args) {
        Assert.assertEquals(kEmptySlots(new int[]{1, 3, 2}, 1), 2);
        Assert.assertEquals(kEmptySlots(new int[]{1, 2, 3, 4}, 1), -1);
        Assert.assertEquals(kEmptySlots(new int[]{}, 1), -1);
        Assert.assertEquals(kEmptySlots(new int[]{5, 39, 25, 28, 16, 58, 70, 29, 67, 24, 78, 13, 9, 64, 98, 38, 44, 96, 27, 88, 75, 84, 69, 34, 55,
                12, 47, 33, 77, 15, 31, 74, 2, 26, 76, 10, 17, 72, 100, 36, 6, 90, 4, 95, 49, 21, 94, 79, 62, 32, 1, 35, 60, 18, 3, 53, 82, 48, 54,
                30, 19, 87, 40, 85, 68, 57, 11, 42, 92, 61, 71, 37, 14, 51, 50, 83, 22, 93, 91, 65, 99, 52, 7, 46, 89, 80, 20, 8, 97, 86, 23, 66,
                81, 59, 56, 63, 43, 41, 73, 45}, 4), 17);
    }

    public static int kEmptySlots(int[] flowers, int k) {
        int[] days = new int[flowers.length];
        for (int i = 0; i <= flowers.length - 1; i++) {
            days[flowers[i]-1] = i+1 ;
        }

        int result = Integer.MAX_VALUE;
        Stack<Integer> stackPos = new Stack<>();
        for (int curPos = 0; curPos <= days.length - 1; curPos++) {
            while (!stackPos.isEmpty()) {
                if (curPos - stackPos.peek() == k + 1) {
                    int temp = Math.max(days[curPos], days[stackPos.peek()]);
                    result = Math.min(result, temp);
                }
                //如果栈顶比当前早开花，则保留,因为要等右边那个时间点到来。
                if (days[stackPos.peek()] < days[curPos]){break ;}

                //如果栈顶比当前迟开花，则有俩种情况，要不就是答案，要不就是中间k个花之一，所以出栈。
                stackPos.pop();
            }

            stackPos.push(curPos);
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}

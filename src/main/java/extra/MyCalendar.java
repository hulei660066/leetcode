package extra;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hulei on 2018/9/3.
 */
public class MyCalendar {
    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        Assert.assertEquals(myCalendar.book(48, 50), true);
        Assert.assertEquals(myCalendar.book(0, 6), true);
        Assert.assertEquals(myCalendar.book(6, 13), true);

        Assert.assertEquals(myCalendar.book(10, 20), true);
        Assert.assertEquals(myCalendar.book(15, 25), false);
        Assert.assertEquals(myCalendar.book(20, 30), true);
    }

    List<int[]> intervals;

    public MyCalendar() {
        intervals = new LinkedList<>();
    }

    //考虑所有可能插入的点：0到len，从0遍历到len-1。
    //这里是有序的
    public boolean book(int start, int end) {
        int[] newInterval = {start, end};
        int idx;
        for (idx = 0; idx <= intervals.size() - 1; idx++) {
            if (intervals.get(idx)[0] >= end) { break; }
            if (intervals.get(idx)[1] > start) { return false; }
        }

        intervals.add(idx, newInterval);

        return true;
    }
}

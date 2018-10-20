package dynamicProgram;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hulei on 2018/8/30.
 */

//要不要排序？太复杂了
public class MyCalendarTwo {
    public static void main(String[] args) {
        MyCalendarTwo myCalendar = new MyCalendarTwo();
        Assert.assertEquals(myCalendar.book(10, 20), true);
        Assert.assertEquals(myCalendar.book(50, 60), true);
        Assert.assertEquals(myCalendar.book(10, 40), true);
        Assert.assertEquals(myCalendar.book(5, 15), false);
        Assert.assertEquals(myCalendar.book(5, 10), true);
        Assert.assertEquals(myCalendar.book(25, 55), true);
    }

    List<Integer> begins;
    List<Integer> ends;

    public MyCalendarTwo() {
        begins = new ArrayList<>();
        ends = new ArrayList<>();
    }

    //start重复；start end 重复；end 重复
    public boolean book(int start, int end) {
        return true;
    }
}

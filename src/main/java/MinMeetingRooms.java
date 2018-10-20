import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hulei on 2018/8/29.
 */
class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class MinMeetingRooms {

    public static void main(String[] args) {
        List<Interval> list = new LinkedList<>();
        list.add(new Interval(0, 30));
        list.add(new Interval(5, 10));
        list.add(new Interval(15, 20));
        Assert.assertEquals(minMeetingRooms(list), 2);
    }

    public static int minMeetingRooms(List<Interval> intervals) {
        List<Integer> line = new LinkedList<>();
        for (Interval interval:intervals){
            boolean isFindRoom=false;
            for (int i=0;i<=line.size()-1;i++){
                if (line.get(i)<=interval.end){
                    line.set(i,interval.end);
                    isFindRoom=true;
                    break;
                }
            }
            if (!isFindRoom){
                line.add(interval.end);
            }
        }
        return line.size();
    }
}

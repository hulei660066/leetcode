import org.junit.Assert;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import jdk.nashorn.internal.ir.IfNode;

/**
 * Created by hulei on 2018/8/4.
 */
public class RearrangeString {
    //又一个贪心算法,设计到优先队列.
    public static void main(String[] args) {
        Assert.assertEquals(rearrangeString("aabbcc",3), "abcabc");
        Assert.assertEquals(rearrangeString("aaabc",3), "");
        Assert.assertEquals(rearrangeString("aaadbbcc",2), "abacabcd");
    }

    static String rearrangeString(String str, int k) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if (b[0] - a[0] == 0) return a[1] - b[1];
            else return b[0] - a[0];
        }
        );
        int maxLength=0;
        int pre=0;
        int cur=0;
        while (true){
            if (cur > str.length() - 1) { break; }

            while (cur < str.length() - 1 && str.charAt(cur + 1) == str.charAt(pre)) {
                cur++;
            }
            int count = cur - pre + 1;
            maxLength=Math.max(maxLength,count);
            queue.add(new int[]{count,str.charAt(pre)});
            cur = pre = cur + 1;
        }

        if (maxLength * k > str.length()) { return ""; }
        StringBuilder sb = new StringBuilder();
        LinkedList<int[]> temp = new LinkedList<>();
        while (!queue.isEmpty()||temp.size()>0){
            if (temp.size()==k){
                queue.addAll(temp);
                temp=new LinkedList<>();
            }

            int[] poll = queue.poll();
            sb.append(Character.toChars(poll[1]));
            if (poll[0] - 1>0){
                temp.add(new int[]{poll[0] - 1, poll[1]});
            }
        }

        return sb.toString();
    }
}


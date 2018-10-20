import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hulei on 2018/9/5.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) { val = x; }
}

public class NumComponents {
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        Assert.assertEquals(numComponents(head, new int[]{0, 1, 3}), 2);

        head.next.next.next.next = new ListNode(4);
        Assert.assertEquals(numComponents(head, new int[]{0, 3, 1, 4}), 2);
    }

    public static int numComponents(ListNode head, int[] G) {
        Set<Integer> set = new HashSet<>();
        for (int g : G) { set.add(g); }

        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            if (!set.contains(temp.val)) {
                temp = temp.next;
                continue;
            }

            count++;
            while (temp != null && set.contains(temp.val)) {
                temp = temp.next;
            }
        }

        return count;
    }
}

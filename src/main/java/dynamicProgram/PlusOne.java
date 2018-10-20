package dynamicProgram;

import org.junit.Assert;

import java.util.Stack;

/**
 * Created by hulei on 2018/8/30.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class PlusOne {
    public static void main(String[] args) {
        Assert.assertEquals(plusOne(new ListNode(0)), true);
    }

    public static ListNode plusOne(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        int remain = 1;
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            pop.val += remain;
            if (pop.val == 10) {
                pop.val = 0;
            } else {
                remain = 0;
            }
        }
        if (remain == 1) {
            ListNode newNode = new ListNode(1);
            newNode.next = head;
            head = newNode;
        }
        return head;
    }
}

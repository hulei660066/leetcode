package relationshipChain;

import org.junit.Assert;

import java.util.Stack;

/**
 * Created by hulei on 2018/9/15.
 */
public class KthGrammar {
    public static void main(String[] args) {
        Assert.assertEquals(kthGrammar(1, 1), 0);
        Assert.assertEquals(kthGrammar(2, 1), 0);
        Assert.assertEquals(kthGrammar(4, 5), 1);
    }

    //这道题的关键是找到当前遍历中的那个点。
    //就像进化中的分支
    public static int kthGrammar(int N, int K) {
        Stack<Integer> stack = new Stack<>();
        int newN = N;
        while (newN-- > 0) {
            stack.push(K);
            K = (K + 1) / 2;
        }

        int cur = 0;
        //充1到N遍历,每次遍历的时候确定是找第一个还是第二个.
        for (int i = 1; i <= N; i++) {
            Integer pos = stack.pop();
            cur = pos % 2 == 1 ? cur : (cur + 1) % 2;
        }
        return cur;
    }

    public static int kthGrammar2(int N, int K) {
        StringBuilder sb = new StringBuilder("0");
        for (int idx = 1; idx <= N; idx++) {
            StringBuilder newSB = new StringBuilder();
            for (char c : sb.toString().toCharArray()) {
                String addition = c == '0' ? "01" : "10";
                newSB.append(addition);
            }
            sb = newSB;
        }
        return sb.charAt(K - 1) - '0';
    }
}

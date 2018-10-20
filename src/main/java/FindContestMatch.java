import org.junit.Assert;

/**
 * Created by hulei on 2018/8/31.
 */
public class FindContestMatch {
    public static void main(String[] args) {
        Assert.assertEquals(findContestMatch(2), "(1,2)");
        Assert.assertEquals(findContestMatch(4), "((1,4),(2,3))");
    }

    //数学知识推导？
    //迭代？递归？
    public static String findContestMatch(int n) {
        String[] nums = new String[n];
        for (int i = 0; i <= nums.length - 1; i++) {
            nums[i] = "" + (i + 1);
        }
        while (n > 1) {
            int first = 0;
            int second = n - 1;
            int mid = n >> 1;
            while (first < mid) {
                nums[first] = "(" + nums[first++] + "," + nums[second--] + ")";
            }
            n = mid;
        }
        return nums[0];
    }
}

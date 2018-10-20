import com.sun.deploy.resources.Deployment_it;

import org.junit.Assert;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by hulei on 2018/8/4.
 */
public class MaxNumber {
    //ugly code...
    public static void main(String[] args) {
        Assert.assertEquals(maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5), new int[]{9, 8, 6, 5, 3});
        Assert.assertEquals(maxNumber(new int[]{8, 5, 9, 5, 1, 6, 9}, new int[]{2, 6, 4, 3, 8, 4, 1, 0, 7, 2, 9, 2, 8}, 20), new int[]{8, 5, 9, 5,
                2, 6, 4, 3, 8, 4, 1, 6, 9, 1, 0, 7, 2, 9, 2, 8});
        Assert.assertEquals(maxNumber(new int[]{6, 7}, new int[]{6, 0, 4}, 5), new int[]{6, 7, 6, 0, 4});
        Assert.assertEquals(maxNumber(new int[]{3, 9}, new int[]{8, 9}, 3), new int[]{9, 8, 9});
        Assert.assertEquals(maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5), new int[]{9, 8, 6, 5, 3});
    }

    //1.Create the maximum number of one array
    //2.Create the maximum number of two array using all of their digits.
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        String[] dp1 = helper(nums1, k);
        String[] dp2 = helper(nums2, k);

        int[] results = new int[k];
        for (int len1 = 0; len1 <= Math.min(k, nums1.length); len1++) {
            int[] temp = new int[k];
            int len2 = k - len1;
            if (len2 > dp2.length - 1) { continue; }

            String s1 = dp1[len1];
            String s2 = dp2[len2];
            int idx1 = 0;
            int idx2 = 0;
            while (idx1 <= s1.length() - 1 || idx2 <= s2.length() - 1) {
                if (idx1 > s1.length() - 1) {
                    temp[idx1 + idx2] = s2.charAt(idx2++) - '0';
                    continue;
                }
                if (idx2 > s2.length() - 1) {
                    temp[idx1 + idx2] = s1.charAt(idx1++) - '0';
                    continue;
                }

                if (s1.charAt(idx1) == s2.charAt(idx2)) {
                    int theWinner = 1;
                    int temp1 = idx1;
                    int temp2 = idx2;
                    while (temp1 <= s1.length() - 1 || temp2 <= s2.length() - 1) {
                        if (temp2 > s2.length() - 1) { break; }
                        if (temp1 > s1.length() - 1) {
                            theWinner = 2;
                            break;
                        }

                        if (s1.charAt(temp1) == s2.charAt(temp2)) {
                            temp1++;
                            temp2++;
                            continue;
                        } else if (s1.charAt(temp1) > s2.charAt(temp2)) {
                            break;
                        } else {
                            theWinner = 2;
                            break;
                        }
                    }

                    if (theWinner == 1) {
                        temp[idx1 + idx2] = s1.charAt(idx1++) - '0';
                    } else {
                        temp[idx1 + idx2] = s2.charAt(idx2++) - '0';
                    }
                } else if (s1.charAt(idx1) > s2.charAt(idx2)) {
                    temp[idx1 + idx2] = s1.charAt(idx1++) - '0';
                } else {
                    temp[idx1 + idx2] = s2.charAt(idx2++) - '0';
                }
            }

            for (int i = 0; i <= k - 1; i++) {
                if (temp[i] > results[i]) {
                    results = temp;
                    break;
                } else if (temp[i] < results[i]) {
                    break;
                }
            }
        }

        return results;
    }

    private static String[] helper(int[] nums1, int k) {
        List<String> list = new LinkedList<String>() {{add("");}};
        for (int nums1Idx = 0; nums1Idx <= nums1.length - 1; nums1Idx++) {
            int cur = nums1[nums1Idx];
            list.add(list.get(list.size() - 1) + "" + cur);
            for (int i = list.size() - 1; i >= 1; i--) {
                String s = list.get(i - 1) + "" + cur;
                if (s.compareTo(list.get(i)) > 0) {
                    list.set(i, s);
                }
            }
        }

        return list.toArray(new String[list.size()]);
    }
}

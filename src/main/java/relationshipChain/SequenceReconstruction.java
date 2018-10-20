package relationshipChain;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hulei on 2018/9/7.
 */
public class SequenceReconstruction {
    public static void main(String[] args) {
        Assert.assertTrue(sequenceReconstruction(new int[]{4, 1, 5, 2, 6, 3}, new int[][]{{5, 2, 6, 3}, {4, 1, 5, 2}}));
        Assert.assertFalse(sequenceReconstruction(new int[]{1}, new int[][]{{}}));
        Assert.assertTrue(sequenceReconstruction(new int[]{1, 2, 3}, new int[][]{{1, 2}, {1, 3}, {2, 3}}));
        Assert.assertTrue(sequenceReconstruction(new int[]{1}, new int[][]{{1}}));
        Assert.assertTrue(sequenceReconstruction(new int[]{}, new int[][]{{}, {}}));
        Assert.assertFalse(sequenceReconstruction(new int[]{1, 2, 3}, new int[][]{{1, 2}, {1, 3}}));
    }

    //规律：每个元素至少出现一次；并且需要至少n-1个关系链.
    //corner case：0个元素；1个元素
    //本质是关系至少需要俩个元素
    public static boolean sequenceReconstruction(int[] org, int[][] seqs) {
        if (org.length == 0) { return true; }
        int[] oneByOne = new int[org.length + 1];
        for (int idx = 0; idx <= org.length - 2; idx++) {
            oneByOne[org[idx]] = org[idx + 1];
        }

        int[] counts = new int[org.length + 1];
        Set<Integer> set = new HashSet<>();
        for (int[] seg : seqs) {
            for (int idx = 0; idx <= seg.length - 1; idx++) {
                set.add(seg[idx]);
                if (idx != seg.length - 1 && oneByOne[seg[idx]] == seg[idx + 1]) {
                    counts[seg[idx + 1]] = 1;
                }
            }
        }

        int result = 0;
        for (int count : counts) {
            result += count;
        }

        boolean isEveryOneHere = set.size() == org.length;
        boolean isEnough = result + 1 == org.length;
        return isEnough && isEveryOneHere;
    }
}

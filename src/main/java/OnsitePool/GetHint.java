package OnsitePool;

import org.junit.Assert;

/**
 * Created by hulei on 2018/9/1.
 */
public class GetHint {
    public static void main(String[] args) {
        Assert.assertEquals(getHint("1807", "7810"), "1A3B");
        Assert.assertEquals(getHint("1123", "0111"), "1A1B");
    }

    public static String getHint(String secret, String guess) {
        int ACount = 0;
        int BCount = 0;
        int[] Nums = new int[10];
        for (int idx = 0; idx <= secret.length() - 1; idx++) {
            if (secret.charAt(idx) == guess.charAt(idx)) {
                ACount++;
                continue;
            }
            Nums[secret.charAt(idx) - '0']++;
            if (Nums[secret.charAt(idx) - '0'] <= 0) {
                BCount++;
            }

            Nums[guess.charAt(idx) - '0']--;
            if (Nums[guess.charAt(idx) - '0'] >= 0) {
                BCount++;
            }
        }

        return ACount+"A"+BCount+"B";
    }
}

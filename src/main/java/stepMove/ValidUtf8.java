package stepMove;

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/23.
 */
public class ValidUtf8 {
    public static void main(String[] args) {
        Assert.assertTrue(validUtf8(new int[]{240,162,138,147}));
        Assert.assertFalse(validUtf8(new int[]{237}));
        Assert.assertTrue(validUtf8(new int[]{230, 136, 145}));
        Assert.assertTrue(validUtf8(new int[]{197, 130, 1}));
        Assert.assertFalse(validUtf8(new int[]{235, 140, 4}));
    }

    public static boolean validUtf8(int[] data) {
        boolean isValid = true;
        int count = 0;
        begin:
        for (int idx = 0; idx <= data.length - 1; idx++) {
            int i = data[idx] >>> 3;
            switch (count) {
                case 0:
                    if (data[idx] >>> 3 == 0x1e) {
                        count = 3;
                    } else if (data[idx] >>> 4 == 0xe) {
                        count = 2;
                    } else if (data[idx] >>> 5 == 0x6) {
                        count = 1;
                    } else if (data[idx] >>> 7 == 0x0) {
                        count = 0;
                    } else {
                        isValid = false;
                        idx = data.length;
                        break begin;
                    }
                    break;

                case 1:
                case 2:
                case 3:
                    if (data[idx] >>> 6 == 0x2) {
                        count--;
                    } else {
                        isValid = false;
                        idx = data.length;
                        break begin;
                    }
            }
        }

        return isValid && count == 0;
    }
}

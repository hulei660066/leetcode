import org.junit.Assert;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hulei on 2018/8/3.
 */
public class IsRectangleCover {
    //纯数学
    public static void main(String[] args) {
        Assert.assertEquals(isRectangleCover(new int[][]{new int[]{0, 0, 1, 1}, new int[]{1,0,2,2}, new int[]{0,1,3,2}}), false);
        Assert.assertEquals(isRectangleCover(new int[][]{new int[]{1, 1, 3, 3}, new int[]{3, 1, 4, 2}, new int[]{3, 2, 4, 4}, new int[]{1, 3, 2,
                4}, new int[]{2, 3, 3, 4}}), true);
        Assert.assertEquals(isRectangleCover(new int[][]{new int[]{1, 1, 2, 3}, new int[]{1,3,2,4}, new int[]{3,1,4,2}, new int[]{3, 2, 4, 4}}), false);
        Assert.assertEquals(isRectangleCover(new int[][]{new int[]{1, 1, 3, 3}, new int[]{3,1,4,2}, new int[]{1,3,2,4}, new int[]{3, 2, 4, 4}}), false);
        Assert.assertEquals(isRectangleCover(new int[][]{new int[]{1, 1, 3, 3}, new int[]{3,1,4,2}, new int[]{1,3,2,4}, new int[]{2, 2, 4, 4}}), false);
    }

    static Map<String, Integer> map = new HashMap<String, Integer>();
    public static boolean isRectangleCover(int[][] rectangles) {
        if (rectangles.length == 0 || rectangles[0].length == 0) return false;
        int lx = Integer.MAX_VALUE, ly = lx, rx = Integer.MIN_VALUE, ry = rx, sum = 0;
        for (int[] rec : rectangles) {
            lx = Math.min(lx, rec[0]);
            ly = Math.min(ly, rec[1]);
            rx = Math.max(rx, rec[2]);
            ry = Math.max(ry, rec[3]);
            sum += (rec[2] - rec[0]) * (rec[3] - rec[1]);
            //bottom-left
            if (overlap(rec[0] + " " + rec[1], 1)) return false;
            //top-left
            if (overlap(rec[0] + " " + rec[3], 2)) return false;
            //bottom-right
            if (overlap(rec[2] + " " + rec[1], 4)) return false;
            //top-right
            if (overlap(rec[2] + " " + rec[3], 8)) return false;
        }
        int count = 0;
        Iterator<Integer> iter = map.values().iterator();
        while (iter.hasNext()) {
            Integer i = iter.next();
            if (i != 15 && i != 12 && i != 10 && i != 9 && i != 6 && i != 5 && i != 3) count++;
        }
        return count == 4 && sum == (rx - lx) * (ry - ly);
    }

    private static boolean overlap(String corner, Integer type) {
        Integer temp = map.get(corner);
        if (temp == null) temp = type;
        else if ((temp & type) != 0) return true;
        else temp |= type;
        map.put(corner, temp);
        return false;
    }

/*    the large rectangle area should be equal to the sum of small rectangles
    count of all the points should be even, and that of all the four Corner points should be one*/
//    public static boolean isRectangleCover(int[][] rectangles) {
//        int addSum = 0;
//        long[] theBig=new long[]{Integer.MAX_VALUE, Integer.MAX_VALUE,Integer.MIN_VALUE, Integer.MIN_VALUE};
//        for (int[] rectangle : rectangles) {
//            addSum += (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);
//            if (theBig[0] + theBig[1] > rectangle[0] + rectangle[1]) {
//                theBig[0] = rectangle[0];
//                theBig[1] = rectangle[1];
//            }
//
//            if (theBig[2] + theBig[3] < rectangle[2] + rectangle[3]) {
//                theBig[2] = rectangle[2];
//                theBig[3] = rectangle[3];
//            }
//        }
//
//        long sum = (theBig[2] - theBig[0]) * (theBig[3] - theBig[1]);
//        boolean isRectangleCover=false;
//        if (sum==addSum){
//           isRectangleCover=true;
//        }
//
//        return isRectangleCover;
//    }
}

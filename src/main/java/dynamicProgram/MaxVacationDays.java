package dynamicProgram;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hulei on 2018/8/13.
 */
public class MaxVacationDays {
    public static void main(String[] args) {
        Assert.assertEquals(maxVacationDays(new int[][]{new int[]{0, 1, 1}, new int[]{1, 0, 1}, new int[]{1, 1, 0}}, new int[][]{new int[]{7, 0,
                0}, new int[]{0, 7, 0}, new int[]{0, 0, 7}}), 21);
        Assert.assertEquals(maxVacationDays(new int[][]{new int[]{0, 0, 0}, new int[]{0, 0, 0}, new int[]{0, 0, 0}}, new int[][]{new int[]{1, 1,
                1}, new int[]{7, 7, 7}, new int[]{7, 7, 7}}), 3);
        Assert.assertEquals(maxVacationDays(new int[][]{new int[]{0, 1, 1}, new int[]{1, 0, 1}, new int[]{1, 1, 0}}, new int[][]{new int[]{1, 3,
                1}, new int[]{6, 0, 3}, new int[]{3, 3, 3}}), 12);
    }

    public static int maxVacationDays(int[][] flights, int[][] days) {
        int cityNum=flights.length;
        int weekNum=days[0].length;

        long max = 0;
        long[] dp = new long[weekNum];
        //有俩种思路,1.把start点保存再说数组中;2.是初始化dp为 min_value
        Set<Integer> starts = new HashSet<Integer>() {{add(0);}};
        for (int kThWeek = 0; kThWeek <= weekNum - 1; kThWeek++) {
            long[] tempDp = new long[weekNum];
            Set<Integer> tempCities = new HashSet<>();

            for (int start : starts) {
                //next跟start通航,或者就是start本身
                for (int next=0;next<=cityNum-1;next++){
                    int isConnect = flights[start][next];
                    if (isConnect==1||next==start){
                       tempDp[next] = Math.max(tempDp[next], days[next][kThWeek] + dp[start]);
                       max = Math.max(tempDp[next], max);
                       tempCities.add(next);
                   }
                }

            }

            starts=tempCities;
            dp=tempDp;
        }

        return (int) max;
//        return (int) dfs(flights, days, 0, 0);
    }

    private static long dfs(int[][] flights, int[][] days, int city, int kThWeek) {
        if (kThWeek == days[0].length) { return 0; }
        long result = days[0][kThWeek] + dfs(flights, days, 0, kThWeek + 1);
        for (int neighbor : flights[city]) {
            long temp = days[neighbor][kThWeek] + dfs(flights, days, neighbor, kThWeek + 1);
            result = Math.max(result, temp);
        }

        return result;
    }
}

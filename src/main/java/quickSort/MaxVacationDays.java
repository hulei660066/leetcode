package quickSort;

import org.junit.Assert;

/**
 * Created by hulei on 2018/8/18.
 */
public class MaxVacationDays {
    public static void main(String[] args) {
        Assert.assertEquals(maxVacationDays(new int[][]{new int[]{0, 1, 1}, new int[]{1, 0, 1}, new int[]{1, 1, 0}}, new int[][]{new int[]{1, 3,
                1}, new int[]{6, 0, 3}, new int[]{3, 3, 3}}), 12);
        Assert.assertEquals(maxVacationDays(new int[][]{new int[]{0, 0, 0}, new int[]{0, 0, 0}, new int[]{0, 0, 0}}, new int[][]{new int[]{1, 1,
                1}, new int[]{7, 7, 7}, new int[]{3, 3, 3}}), 3);

    }

    //反向变量更简单些，原因未知
    public static int maxVacationDays(int[][] flights, int[][] days) {
        int[][] dp = new int[days.length + 1][flights.length];
        int max=Integer.MIN_VALUE;
        for (int day = days[0].length - 1; day >= 0; day--) {
            for (int from = 0; from <= flights.length-1; from++) {
                for (int to = 0; to <= flights.length - 1; to++) {
                    if (from == to || flights[from][to] == 1) {
                        int vacation = dp[day + 1][to] + days[from][day];
                        dp[day][from] = Math.max(dp[day][from], vacation);
                    }
                }
                if (day==0&&(flights[0][from]==1||from==0)){
                    max=Math.max(max,dp[0][from]);
                }
            }
        }

        return max;
    }

    public static int maxVacationDays2(int[][] flights, int[][] days) {
        int cityNum = days.length;
        if (cityNum == 0) { return 0; }

        int weekNum = days[0].length;
        if (weekNum == 0) return 0;

        int result = 0;
        int[][] dp = new int[weekNum][cityNum];
        for (int row = 0; row <= dp.length - 1; row++) {
            for (int col = 0; col <= dp[0].length - 1; col++) {
                dp[row][col] = -1;
            }
        }

        //zeroArray
        for (int to = 0; to <= flights[0].length - 1; to++) {
            if (flights[0][to] == 1 || to == 0) {
                dp[0][to] = days[to][0];
                result = Math.max(result, dp[0][to]);
            }
        }

        for (int curWeek = 1; curWeek <= weekNum - 1; curWeek++) {
            int preWeek = curWeek - 1;
            for (int from = 0; from <= cityNum - 1; from++) {
                if (dp[preWeek][from] == -1) { continue; }

                for (int to = 0; to <= flights[from].length - 1; to++) {
                    if (flights[from][to] == 1 || to == from) {
                        int vacation = dp[preWeek][from] + days[to][curWeek];
                        dp[curWeek][to] = Math.max(dp[curWeek][to], vacation);
                        result = Math.max(dp[curWeek][to], result);
                    }
                }

            }
        }

        return result;
    }
}

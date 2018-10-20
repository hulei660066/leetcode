package binarySearch;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hulei on 2018/8/22.
 */
public class MinArea {
    public static void main(String[] args) {
        Assert.assertEquals(minArea(new char[][]{{'0', '0', '1', '0'}}, 0, 2), 1);
        Assert.assertEquals(minArea(new char[][]{}, 0, 2), 0);
        Assert.assertEquals(minArea(new char[][]{{'0'}, {'1'}, {'1'}}, 1, 0), 2);
        Assert.assertEquals(minArea(new char[][]{{'0', '0', '1', '0'}, {'0', '1', '1', '0'}, {'0', '1', '0', '0'}}, 0, 2), 6);
    }

    //之前认为low永远指向带插入点,但还是没有跟随题意组织逻辑，思维定式害死人.
    public static int minArea(char[][] image, int x, int y) {
        if (image.length == 0 || image[0].length == 0) { return 0; }

        int[] upDownRightLeft = new int[4];
        boolean[] isNegative = new boolean[]{false, true, true, false};
        boolean[] isRow = new boolean[]{true, true, false, false};
        int[][] beginEnds = new int[][]{{0, x}, {x, image.length - 1}, {y, image[0].length - 1}, {0, y}};

        for (int i = 0; i <= upDownRightLeft.length - 1; i++) {
            int first = beginEnds[i][0];
            int second = beginEnds[i][1];

            while (first <= second) {
                int mid = (first + second) >>> 1;
                boolean isOneExsit = check(image, isRow[i], mid);
                if (isNegative[i]) { isOneExsit = !isOneExsit; }

                if (isOneExsit) {
                    second = mid - 1;
                } else {
                    first = mid + 1;
                }
            }

            upDownRightLeft[i] = first;
        }

        return (upDownRightLeft[1] - upDownRightLeft[0]) * (upDownRightLeft[2] - upDownRightLeft[3]);
    }

    private static boolean check(char[][] image, boolean isRow, int idx) {
        boolean isOneExsit = false;
        if (isRow) {
            for (int col = 0; col <= image[idx].length - 1; col++) {
                if (image[idx][col] == '1') {
                    isOneExsit = true;
                    break;
                }
            }
        } else {
            for (int row = 0; row <= image.length - 1; row++) {
                if (image[row][idx] == '1') {
                    isOneExsit = true;
                    break;
                }
            }
        }

        return isOneExsit;
    }

    public static int minArea2(char[][] image, int x, int y) {
        if (image.length == 0 || image[0].length == 0) { return 0; }

        int[] upRightDownLeft = {Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE};
        boolean[][] isVisited = new boolean[image.length][image[0].length];
        BFS(image, x, y, upRightDownLeft, isVisited);

        return (upRightDownLeft[2] - upRightDownLeft[0] + 1) * (upRightDownLeft[1] - upRightDownLeft[3] + 1);
    }

    private static void BFS(char[][] image, int x, int y, int[] upRightDownLeft, boolean[][] isVisited) {
        isVisited[x][y] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (upRightDownLeft[0] > cur[0]) { upRightDownLeft[0] = cur[0]; }
            if (upRightDownLeft[1] < cur[1]) { upRightDownLeft[1] = cur[1]; }
            if (upRightDownLeft[2] < cur[0]) { upRightDownLeft[2] = cur[0]; }
            if (upRightDownLeft[3] > cur[1]) { upRightDownLeft[3] = cur[1]; }

            int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dir : dirs) {
                int row = cur[0] + dir[0];
                int col = cur[1] + dir[1];
                boolean isInRange = row >= 0 && row <= image.length - 1 && col >= 0 && col < image[0].length;
                if (isInRange && image[row][col] == '1' && !isVisited[row][col]) {
                    isVisited[row][col] = true;
                    queue.add(new int[]{row, col});

                }
            }
        }
    }

    private static void DFS(char[][] image, int x, int y, int[] upRightDownLeft, boolean[][] isVisited) {
        if (upRightDownLeft[0] > x) { upRightDownLeft[0] = x; }
        if (upRightDownLeft[1] < y) { upRightDownLeft[1] = y; }
        if (upRightDownLeft[2] < x) { upRightDownLeft[2] = x; }
        if (upRightDownLeft[3] > y) { upRightDownLeft[3] = y; }

        isVisited[x][y] = true;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : dirs) {
            int row = x + dir[0];
            int col = y + dir[1];
            boolean isInRange = row >= 0 && row <= image.length - 1 && col >= 0 && col < image[0].length;
            if (isInRange && image[row][col] == '1' && !isVisited[row][col]) {
                DFS(image, row, col, upRightDownLeft, isVisited);
            }
        }
    }
}

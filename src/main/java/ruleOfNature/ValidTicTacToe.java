package ruleOfNature;

import org.junit.Assert;

/**
 * Created by hulei on 2018/9/5.
 */
public class ValidTicTacToe {
    public static void main(String[] args) {
        Assert.assertEquals(validTicTacToe(new String[]{"XXX", "XOO", "OO "}), false);
        Assert.assertEquals(validTicTacToe(new String[]{"O  ", "   ", "   "}), false);
        Assert.assertEquals(validTicTacToe(new String[]{"XOX", "O O", "XOX"}), true);
        Assert.assertEquals(validTicTacToe(new String[]{"XXX", "   ", "OOO"}), false);
        Assert.assertEquals(validTicTacToe(new String[]{"XOX", " X ", "   "}), false);
    }

    public static boolean validTicTacToe(String[] board) {
        int countO = 0;
        int countX = 0;
        int isOWin = 0;
        int isXWin = 0;
        StringBuilder[] sb = new StringBuilder[5];
        for (int idx = 0; idx <= sb.length - 1; idx++) {
            sb[idx] = new StringBuilder();
        }
        for (int row = 0; row <= 2; row++) {
            String str = board[row];
            if (str.equals("OOO")) {
                isOWin = 1;
            } else if (str.equals("XXX")) {
                isXWin = 1;
            }

            for (int col = 0; col <= 2; col++) {
                char c = str.charAt(col);
                if (c == 'O') {
                    countO++;
                } else if (c == 'X') {
                    countX++;
                }

                sb[col].append(c);
                if (row == col) { sb[3].append(c); }
                if (row + col == 2) { sb[4].append(c); }
            }
        }
        for (StringBuilder s : sb) {
            if (s.toString().equals("OOO")) {
                isOWin = 1;
            } else if (s.toString().equals("XXX")) {
                isXWin = 1;
            }
        }

        if (isOWin + isXWin > 1) {
            return false;
        }
        boolean isFull = countO == 5 && countX == 4;
        boolean isWinO = isOWin == 1 && countO == countX;
        boolean iswinX = isXWin == 1 && countO == countX - 1;
        boolean isRunning = isOWin + isXWin == 0 && (countO == countX || countX == countO + 1);
        //规则：同一时刻只能有一个玩家连成3个;如果O赢则多一个；如果X赢则相等
        if (isFull || iswinX || isWinO || isRunning) {
            return true;
        }

        return false;
    }
}

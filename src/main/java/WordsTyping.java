import org.junit.Assert;

/**
 * Created by hulei on 2018/8/23.
 */
public class WordsTyping {
    public static void main(String[] args) {
        Assert.assertEquals(wordsTyping(new String[]{"og", "xjabeo", "cvicde", "pn", "csxl", "uub", "tkjvy", "qxsopxnmy", "srnmtd", "fim",
                "btsjubvxf", "k", "vazvzprk", "qpzlxxiips", "oqbubigenf", "blj", "zqxzsaa", "xrmkwm", "brfksd", "ybipvpu", "gzmpv", "crcnyi",
                "dti", "kpc", "dfxxozht", "dpirx", "ffxw", "qglqwse", "nfesihc", "jifybbuwht", "seby", "tlx", "huct", "gnxgy", "syklj", "h", "qtb",
                "sujg", "s", "kespvpa", "dfnggc", "wrvqo", "ufc", "ejwk", "jtf", "y", "smghn", "fmfnknbkq", "kakz", "detu", "bzjrmqdrot",
                "mtmhoyvnl", "xdvk", "tul", "lroncickxy", "widnjlp", "ghwje", "bbkzshx", "mawxjcjrx", "jqhstuejda", "izlwbir", "mk", "qaz",
                "csmpgpirdy", "yzvkpn", "uosrvbw", "ekliocxuy", "nuenyxo", "mwsj", "zjsruor", "q", "gtiooatpz", "klsoaawpm", "e", "ftqnypslk",
                "ykplolii", "p", "b", "opquuk", "ufmfvpn", "teik", "wdsjadjy", "yx", "ivmv", "fiuib", "bfxojhmk", "qzxmjco", "sck", "kqvh",
                "vdfztd", "ipnksbw", "kjxv", "gkqnyo", "uiv", "c", "v", "mcya", "ocwfsx", "xdjiwvgjwg", "iyinch"}, 20000, 20000), 625882);
        Assert.assertEquals(wordsTyping(new String[]{"I", "had", "apple", "pie"}, 4, 5), 1);
        Assert.assertEquals(wordsTyping(new String[]{"hello", "world"}, 2, 8), 1);
        Assert.assertEquals(wordsTyping(new String[]{"a", "bcd", "e"}, 3, 6), 2);
        Assert.assertEquals(wordsTyping(new String[]{"hello", "world"}, 2, 4), 0);
    }

    public static int wordsTyping(String[] sentence, int rows, int cols) {
        int count = 0;
        int row = 0;
        int col = 0;
        while (row <= rows - 1) {
            for (int i = 0; i <= sentence.length - 1; i++) {
                if (row >= rows) { break; }
                if (sentence[i].length() > cols) {
                    row = rows;
                    break;
                }

                if (cols >= col + sentence[i].length()) {
                    col += sentence[i].length();
                } else {
                    row++;
                    col = sentence[i].length();
                }

                if (i == sentence.length - 1 && row <= rows - 1) {
                    count++;
                }

                col++;
                if (col >= cols) {
                    row++;
                    col = 0;
                }
            }
        }

        return count;
    }
}

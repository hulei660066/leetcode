import org.junit.Assert;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import jdk.nashorn.internal.ir.IfNode;

/**
 * Created by hulei on 2018/8/7.
 */
class Trie {
    boolean isEnd;
    Trie[] children = new Trie[26];
}
public class FindWords {
    //这道题本意是让用trie,其他数据结构会超时.
    public static void main(String[] args) {
        Assert.assertEquals(findWords(new char[][]{new char[]{'a', 'a'}}, new String[]{"a"}), new LinkedList<String>());

        Assert.assertEquals(findWords(new char[][]{new char[]{'o', 'a', 'a', 'n'}, new char[]{'e', 't', 'a', 'e'}, new char[]{'i', 'h', 'k', 'r'},
                new char[]{'i', 'f', 'l', 'v'}}, new String[]{"oath", "pea", "eat", "rain"}), new LinkedList<String>());
       //"eat","oath"
    }

    static Set<String> results;
    public static List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            Trie itor = trie;
            for (int i = 0; i <= word.length() - 1; i++) {
                char c=word.charAt(i);
                if (itor.children[c-'a']==null){
                    itor.children[c-'a']=new Trie();
                }

                itor = itor.children[c-'a'];
                if (i == word.length() - 1) itor.isEnd = true;
            }
        }

        results = new HashSet<>();
        boolean[][] isVisited = new boolean[board.length][board[0].length];
        for (int row = 0; row <= board.length - 1; row++) {
            for (int col = 0; col <= board[0].length - 1; col++) {
                dfs(board, row, col, trie, "", isVisited);

            }
        }

        return new ArrayList(results);
    }

    private static void dfs(char[][] board, int row, int col, Trie trie, String s, boolean[][] isVisited) {
        Trie trieChild = trie.children[board[row][col] - 'a'];
        if (trieChild == null) return;

        String newS = s + board[row][col];
        if (trieChild.isEnd) { results.add(newS); }

        isVisited[row][col]=true;
        int[][] neighbors = {new int[]{row - 1, col}, new int[]{row, col + 1}, new int[]{row + 1, col}, new int[]{row, col - 1}};
        for (int[] nb : neighbors) {
            if (nb[0] >= 0 && nb[0] <= board.length - 1 && nb[1] >= 0 && nb[1] <= board[0].length - 1 && !isVisited[nb[0]][nb[1]]) {
                dfs(board, nb[0], nb[1], trieChild, newS, isVisited);
            }

        }
        isVisited[row][col]=false;
    }
}

/**
 * Created by hulei on 2018/6/7.
 */
class PalindromePartition {
    private static void dp(String s, char[][] palindrome_map) {
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (i == j) {
                    palindrome_map[i][j] = 1;
                } else {
                    if (s.charAt(i) == s.charAt(j)) {
                        if (j == i + 1 || palindrome_map[i + 1][j - 1] == 1) {
                            palindrome_map[i][j] = 1;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        dp("abbb", new char[4][4]);
    }
}

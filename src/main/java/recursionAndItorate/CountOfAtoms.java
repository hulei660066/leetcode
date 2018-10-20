package recursionAndItorate;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hulei on 2018/8/14.
 */
public class CountOfAtoms {

    public static void main(String[] args) {
        Assert.assertEquals(countOfAtoms("H2O"), "H2O");
        Assert.assertEquals(countOfAtoms("Mg(OH)2"), "H2MgO2");
        Assert.assertEquals(countOfAtoms("K4(ON(SO3)2)2"), "K4N2O14S4");

    }

    enum Token {ATOM, DIGIT, LEFT, RIGHT, NOTHING;}
    public static String countOfAtoms(String formula) {
        Map<String, Integer> map = new HashMap<>();
        helper(formula,0,map);
        StringBuilder sb = new StringBuilder();
        List<String> sortedKeys= new ArrayList<>(map.keySet());
        Collections.sort(sortedKeys);
        for (String sortedKey : sortedKeys) {
            sb.append(sortedKey);
            if (map.get(sortedKey) > 1) { sb.append(map.get(sortedKey)); }
        }

        return sb.toString();
    }

    private static int helper(String formula, int idx, Map<String, Integer> map) {
        int curIdx=idx;
        while (curIdx <= formula.length() - 1 && formula.charAt(curIdx) != ')') {
            Object[] cur = getNext(formula, curIdx);
            Token token = (Token) cur[2];
            Map<String, Integer> contents = new HashMap<>();

            int nextIdx = (int) cur[0];
            if (token==Token.ATOM){
                contents.put((String) cur[1], 1);
            }else if (token==Token.LEFT){
                nextIdx=helper(formula,nextIdx,contents);
            }

            int count=1;
            Object[] next = getNext(formula, nextIdx);
            if (next[2] == Token.DIGIT) {
                count = Integer.parseInt((String) next[1]);
                nextIdx = (int) next[0];
            }

            for (Map.Entry<String,Integer> content:contents.entrySet()){
                if (!map.containsKey(content.getKey())){
                    map.put(content.getKey(), 0);
                }
                map.put(content.getKey(), map.get(content.getKey()) + content.getValue() * count);
            }

            curIdx=nextIdx;
        }

        return curIdx+1;
    }


    private static Object[] getNext(String formula, int idx) {
        if (idx > formula.length() - 1) { return new Object[]{idx, "", Token.NOTHING}; }

        char c = formula.charAt(idx);
        int nextIdx = idx + 1;
        Token token = Token.ATOM;
        if (c >= '1' && c <= '9') {
            while (nextIdx <= formula.length() - 1 && formula.charAt(nextIdx) >= '0' && formula.charAt(nextIdx) <= '9') {
                nextIdx++;
            }
            token=Token.DIGIT;
        } else if (c>='A'&&c<='Z') {
            while (nextIdx <= formula.length() - 1 && formula.charAt(nextIdx) >= 'a' && formula.charAt(nextIdx) <= 'z') {
                nextIdx++;
            }
            token=Token.ATOM;
        }else if (c=='('){
            token=Token.LEFT;
        }else if (c==')'){
            token=Token.RIGHT;
        }

        String content = formula.substring(idx, nextIdx);
        return new Object[]{nextIdx, content, token};
    }

}

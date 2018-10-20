package extra;

import java.util.Iterator;
import java.util.List;

/**
 * Created by hulei on 2018/9/1.
 */
public class Vector2D implements Iterator<Integer> {

    int outIdx = 0;
    int inIdx = -1;
    Integer cur;
    List<List<Integer>> vec2d;

    public Vector2D(List<List<Integer>> vec2d) {
        this.vec2d = vec2d;
        cur = getNext();
    }

    private Integer getNext() {
        if (outIdx <= vec2d.size() - 1 && inIdx + 1 <= vec2d.get(outIdx).size() - 1) {
            inIdx++;
            return vec2d.get(outIdx).get(inIdx);
        }

        outIdx++;
        while (outIdx <= vec2d.size() - 1) {
            List<Integer> sons = vec2d.get(outIdx);
            if (sons.size() != 0) {
                inIdx = 0;
                return sons.get(inIdx);
            }
            outIdx++;
        }

        return null;
    }

    @Override
    public Integer next() {
        int temp = cur;
        cur = getNext();

        return temp;
    }

    @Override
    public boolean hasNext() {
        return cur != null;
    }

    @Override
    public void remove() {}
}

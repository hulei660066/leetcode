import java.util.LinkedList;
import java.util.List;

/**
 * Created by hulei on 2018/8/13.
 */

public class OuterTrees {
    public List<Point> outerTrees(Point[] points) {

        int minIdx = 0;
        //choose downmost point as start point
        for (int i = 1; i <= points.length - 1; i++) {
            if (points[i].y < points[minIdx].y) {
                minIdx = i;
            }
        }

        List<Point> result = new LinkedList<>();
        result.add(points[minIdx]);

        int curIdx = minIdx;
        do {
            int nextIdx = 0;
            if (nextIdx == curIdx) { nextIdx = points.length - 1; }
            for (int candidateIdx = 0; candidateIdx <= points.length - 1; candidateIdx++) {
                if (candidateIdx == curIdx || candidateIdx == nextIdx) { continue; }
                long cross = cross(points[curIdx], points[candidateIdx], points[nextIdx]);
                if (cross > 0) {
                    nextIdx = candidateIdx;
                } else if (cross == 0) {
                    long distanceC = distance(points[curIdx], points[candidateIdx]);
                    long distanceN = distance(points[curIdx], points[nextIdx]);
                    if (distanceC > distanceN) {
                        nextIdx = candidateIdx;
                    }
                }
            }

            for (int candidateIdx = 0; candidateIdx <= points.length - 1; candidateIdx++) {
                if (candidateIdx == curIdx || candidateIdx == nextIdx) { continue; }
                long cross = cross(points[curIdx], points[candidateIdx], points[nextIdx]);
                if (cross == 0) { result.add(points[candidateIdx]); }
            }

            curIdx = nextIdx;
        } while (curIdx != minIdx);


        return result;
    }

    private long distance(Point point1, Point point2) {
        return (point1.x - point2.x) * (point1.x - point2.x) + (point1.y - point2.y) * (point1.y - point2.y);

    }

    //计算公式：n1×n2=|n1|*|n2|*sinθ.
    public long cross(Point cur, Point candidate, Point next) {
        return (cur.x - candidate.x) * (next.y - candidate.y) - (next.x - candidate.x) * (cur.y - candidate.y);
    }
}

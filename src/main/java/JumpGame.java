import com.sun.tools.javac.util.Assert;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 *
 * Each element in the array represents your maximum jump wordsSize at that
 * position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * For example:
 * Given array A = [2,3,1,1,4]
 *
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from
 * index 0 to 1, then 3 steps to the last index.)
 *
 * Tags: Array, Greedy, DP
 */
class JumpGame {
    public static void main(String[] args) {
        JumpGame jumpGame2 = new JumpGame();
        jumpGame2.jump2(new int[]{2, 3, 1, 1, 4});
        jumpGame2.jump2(new int[]{2, 3, 1, 1, 4});

    }

    /**
     * Use last to store how far we already can reach
     * Compare i with last
     * If we run out of it, update and add 1 more step to result
     * Return if last is already bigger than or equal to the wordsSize
     * Use cur to store how far we can reach for the next step
     */
    public int jump(int[] A) {
        int step = 0;
        int last = 0; // how far we already can reach
        int cur = 0; // how far can we reach for next step

        for (int i = 0; i < A.length; i++) {
            if (i > last) { // run out of we can reach, need one more step
                last = cur;
                step++;
                if (last >= A.length) return step;
            }
            cur = Math.max(cur, i + A[i]);
        }
        return step;
    }

    public int jump2(int[] A) {
        int howFarWeCanGet = 0;
        int curStepWeCanGet = -1;
        int minStep = 0;
        for (int i = 0; i < A.length; i++) {
            howFarWeCanGet=Math.max(howFarWeCanGet,i+A[i]) ;
            if (howFarWeCanGet>=A.length-1){
                minStep++;
                break;
            }

            if (i>curStepWeCanGet){
                minStep++;
                curStepWeCanGet=howFarWeCanGet;
            }
        }

        return minStep;
    }
}

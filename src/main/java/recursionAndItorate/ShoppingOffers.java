package recursionAndItorate;

import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hulei on 9/19/18.
 */
public class ShoppingOffers {
    public static void main(String[] args) {
        Assert.assertEquals(shoppingOffers(Arrays.asList(3, 10, 5, 7, 7, 3), Arrays.asList(Arrays.asList(3, 2, 2, 6, 2, 5, 30), Arrays.asList(4, 6,
                5, 2, 3, 2, 12), Arrays.asList(1, 0, 4, 6, 3, 3, 23), Arrays.asList(0, 5, 3, 2, 4, 2, 8), Arrays.asList(4, 1, 2, 6, 3, 2, 33),
                Arrays.asList(3, 0, 6, 5, 5, 2, 33), Arrays.asList(1, 4, 4, 0, 2, 6, 10), Arrays.asList(5, 1, 0, 5, 6, 3, 16), Arrays.asList(3, 4,
                        5, 6, 3, 5, 5), Arrays.asList(6, 0, 6, 0, 5, 5, 22), Arrays.asList(3, 0, 4, 4, 2, 6, 15), Arrays.asList(5, 4, 2, 3, 5, 0,
                        9), Arrays.asList(4, 4, 5, 5, 6, 1, 23), Arrays.asList(2, 6, 5, 6, 6, 4, 4), Arrays.asList(5, 0, 2, 1, 0, 4, 21), Arrays
                        .asList(1, 4, 6, 6, 1, 6, 4), Arrays.asList(4, 0, 5, 6, 2, 2, 17), Arrays.asList(1, 3, 5, 1, 3, 3, 25), Arrays.asList(0, 1,
                        4, 5, 6, 4, 7), Arrays.asList(0, 2, 0, 6, 4, 4, 17), Arrays.asList(3, 2, 3, 5, 2, 2, 27), Arrays.asList(2, 2, 6, 1, 5, 4,
                        31), Arrays.asList(6, 6, 0, 0, 5, 2, 6), Arrays.asList(4, 6, 6, 2, 6, 6, 2), Arrays.asList(1, 2, 4, 4, 4, 6, 22), Arrays
                        .asList(4, 6, 4, 4, 1, 4, 2), Arrays.asList(6, 6, 5, 6, 3, 5, 31), Arrays.asList(6, 5, 0, 4, 2, 5, 8), Arrays.asList(2, 2,
                        4, 3, 4, 6, 24), Arrays.asList(6, 5, 6, 1, 4, 4, 25), Arrays.asList(5, 6, 1, 3, 3, 6, 2), Arrays.asList(5, 2, 4, 4, 0, 2,
                        6), Arrays.asList(3, 0, 4, 3, 3, 6, 27), Arrays.asList(5, 5, 3, 3, 4, 3, 10), Arrays.asList(1, 5, 1, 5, 2, 2, 9), Arrays
                        .asList(3, 2, 1, 1, 4, 5, 8), Arrays.asList(3, 5, 3, 6, 6, 5, 13), Arrays.asList(0, 4, 6, 5, 3, 1, 17), Arrays.asList(5, 2,
                        4, 6, 5, 5, 23), Arrays.asList(0, 5, 3, 1, 0, 4, 20), Arrays.asList(3, 0, 0, 4, 6, 1, 28), Arrays.asList(3, 0, 1, 3, 4, 0,
                        3), Arrays.asList(5, 6, 6, 3, 1, 1, 22), Arrays.asList(6, 4, 3, 4, 2, 0, 10), Arrays.asList(1, 2, 0, 2, 1, 5, 28), Arrays
                        .asList(5, 6, 5, 6, 6, 6, 8), Arrays.asList(2, 4, 6, 0, 4, 3, 4), Arrays.asList(3, 2, 5, 1, 6, 1, 15), Arrays.asList(0, 6,
                        2, 1, 2, 2, 7), Arrays.asList(1, 0, 5, 4, 5, 3, 21), Arrays.asList(4, 2, 3, 6, 4, 2, 29), Arrays.asList(1, 4, 2, 3, 6, 0,
                        28), Arrays.asList(4, 1, 0, 4, 1, 3, 22), Arrays.asList(1, 3, 4, 0, 2, 2, 27), Arrays.asList(0, 3, 0, 2, 3, 3, 18), Arrays
                        .asList(0, 3, 5, 0, 5, 0, 31), Arrays.asList(5, 0, 0, 0, 6, 5, 33), Arrays.asList(5, 1, 0, 6, 3, 2, 32), Arrays.asList(3,
                        4, 1, 1, 5, 6, 29), Arrays.asList(1, 2, 1, 1, 4, 6, 34), Arrays.asList(2, 6, 4, 5, 4, 2, 24), Arrays.asList(4, 1, 2, 2, 0,
                        0, 22), Arrays.asList(3, 2, 4, 4, 4, 4, 7), Arrays.asList(2, 3, 2, 3, 4, 1, 8), Arrays.asList(1, 1, 2, 3, 5, 5, 25), Arrays
                        .asList(3, 0, 5, 0, 6, 1, 17), Arrays.asList(0, 1, 5, 1, 2, 3, 16), Arrays.asList(6, 5, 2, 0, 6, 2, 32), Arrays.asList(3,
                        4, 4, 2, 5, 0, 23), Arrays.asList(3, 3, 4, 6, 3, 2, 17), Arrays.asList(1, 3, 3, 1, 3, 1, 14), Arrays.asList(5, 2, 4, 2, 0,
                        0, 11), Arrays.asList(0, 2, 4, 1, 6, 1, 16), Arrays.asList(2, 6, 1, 4, 2, 5, 20), Arrays.asList(4, 0, 6, 5, 2, 0, 19),
                Arrays.asList(3, 1, 2, 1, 4, 0, 8), Arrays.asList(2, 5, 4, 0, 0, 1, 19), Arrays.asList(5, 0, 4, 5, 5, 5, 23), Arrays.asList(5, 1,
                        4, 2, 1, 1, 34), Arrays.asList(1, 1, 2, 4, 0, 3, 3), Arrays.asList(2, 3, 5, 5, 1, 2, 24), Arrays.asList(6, 1, 5, 2, 1, 2,
                        18), Arrays.asList(4, 5, 3, 3, 6, 5, 26), Arrays.asList(6, 6, 2, 0, 0, 2, 32), Arrays.asList(6, 5, 6, 1, 6, 4, 15), Arrays
                        .asList(0, 6, 3, 5, 5, 6, 5), Arrays.asList(5, 2, 6, 2, 5, 3, 24), Arrays.asList(5, 6, 0, 4, 6, 2, 7), Arrays.asList(4, 2,
                        5, 3, 1, 5, 8), Arrays.asList(5, 2, 2, 6, 0, 3, 34), Arrays.asList(5, 3, 0, 3, 3, 0, 5), Arrays.asList(1, 1, 0, 6, 0, 0,
                        21), Arrays.asList(6, 0, 4, 2, 3, 2, 13), Arrays.asList(2, 0, 1, 0, 1, 4, 32), Arrays.asList(1, 0, 4, 4, 1, 5, 2), Arrays
                        .asList(4, 5, 3, 6, 6, 1, 34), Arrays.asList(6, 2, 5, 4, 2, 2, 12), Arrays.asList(3, 2, 2, 3, 3, 4, 28), Arrays.asList(2,
                        6, 4, 1, 4, 2, 25), Arrays.asList(6, 0, 2, 1, 3, 3, 11)), Arrays.asList(2, 5, 4, 6, 5, 2)), 54);
        Assert.assertEquals(shoppingOffers(Arrays.asList(2, 3, 4), Arrays.asList(Arrays.asList(1, 1, 0, 4), Arrays.asList(2, 2, 1, 9)), Arrays
                .asList(1, 2, 1)), 11);
        Assert.assertEquals(shoppingOffers(Arrays.asList(2, 5), Arrays.asList(Arrays.asList(3, 0, 5), Arrays.asList(1, 2, 10)), Arrays.asList(3, 2)
        ), 14);
    }

    //本来想用背包的。
    static int minPayment;

    public static int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        minPayment = Integer.MAX_VALUE;
        helper(price, special, needs, 0);
        return minPayment;
    }

    private static void helper(List<Integer> prices, List<List<Integer>> specials, List<Integer> needs, int payment) {
        int newPayment = payment;
        for (int idx = 0; idx <= prices.size() - 1; idx++) {
            newPayment += prices.get(idx) * needs.get(idx);
        }
        minPayment = Math.min(minPayment, newPayment);

        //这块如果是0，0，0.。。0，自动会停止
        for (List<Integer> special : specials) {
            List<Integer> newNeeds = new LinkedList<>();
            for (int idx = 0; idx <= needs.size() - 1; idx++) {
                if (special.get(idx) > needs.get(idx)) { break; }
                int num = needs.get(idx) - special.get(idx);
                newNeeds.add(num);
            }
            if (needs.size() != newNeeds.size()) { continue; }
            helper(prices, specials, newNeeds, payment + special.get(special.size() - 1));
        }
    }
}

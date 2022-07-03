package tixi.p18dp1;

/**
 * @author: jzh
 * @date: created in 2022/2/16
 * @description: 背包问题
 * @version: 1.0
 */
public class Code03_Knapsack {

//    给定两个长度都为N的数组weights和values，
//weights[i]和values[i]分别代表 i号物品的重量和价值。
//给定一个正数bag，表示一个载重bag的袋子，
//你装的物品不能超过这个重量。
//返回你能装下最多的价值是多少?

    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        return process(w, v, 0, bag);
    }

    public static int process(int[] w, int[] v, int index, int bag) {
        if (bag < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        //有货，index位置的货
        //bag有空间，0
        //不要当前的货物
        int p1 = process(w, v, index + 1, bag);
        //要当前的货物
        int p2 = 0;
        int next = process(w, v, index + 1, bag - w[index]);
        if (next != -1) {//出现-1代表装不下
            p2 += v[index];
        }
        return Math.max(p1, p2);
    }


    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int N = w.length;
        //index可变范围 0-N
        //bag       负数-bag
        int[][] dp = new int[N + 1][bag + 1];//动态规划表
        //可以理解为一张表
        //    0    1   2   3     4      bag
        //  1 0    6   9   9     12     max
        //  2 0    3   6   6     10      10
        //  3 0    0   6   6     6       6   他的意义是取货物时，依次把剩余重量从0-bag情况都算一遍，货物N-2那行的结果依赖货N-1的结果
        //  4 0    0   0   0     0       0   运算次数比递归的少
     //index                  dp[2][4] 用公式表示就是f[2, 4] = max{f[2+1, 4 - w[2] ] + v[2] , f[3, 4]}
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index+1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1) {//出现-1代表装不下
                    p2 = v[index] + next;
                }
                dp[index][rest]= Math.max(p1,p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
//        System.out.println();
    }
}

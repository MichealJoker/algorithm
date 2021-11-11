package saomang.bitmap;

/**
 * @author: jzh
 * @date: created in 2021/11/9
 * @description:
 * @version: 1.0
 */
public class BitMap2 {


    //异或运算等于两数 无进位的相加

    public static void main(String[] args) {
        int a = 46,b=20;


        System.out.println(add(a,b));
        System.out.println(minus(a,b));
        System.out.println(multi(a,b));
        System.out.println(divide(a,b));
        System.out.println(qumo(a,b));
    }

    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            // 异或得到无进位的相加
            b = a & b;
            b = b << 1;
            // 与运算左移一位得到进位信息， 一直算到进位信息等于0就加完了
            a = sum;
        }
        return sum;
    }

    public static int minus(int a, int b) {
        int add = add(a, add(~b, 1));
        return add;
    }

    /**
     *
     *     0110 ;
     *  x  0111
     * =  0110  +  01100 +  011000
     */
    public static int multi(int a, int b) {
        int sum = 0;
        while (b != 0) {
            int c = b & 1;
            if (c != 0) {
                sum = add(sum, a);
            }
            b >>= 1;
            a <<= 1;
        }
        return sum;
    }

    public static boolean isNeg(int n) {
        return n < 0;
    }

    /**
     *   11000
     * / 00011
     */
    public static int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    /**
     * https://leetcode.com/problems/divide-two-integers
     * @param a
     * @param b
     * @return
     */
    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            if (b == negNum(1)) {
                return Integer.MAX_VALUE;
            } else {
                int c = div(add(a, 1), b);
                return add(c, div(minus(a, multi(c, b)), b));
            }
        } else {
            return div(a, b);
        }
    }


    public static int qumo(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return x;
    }


    /**
     * 取反值
     * @param n
     * @return
     */
    public static int negNum(int n) {
        return add(~n, 1);
    }

}

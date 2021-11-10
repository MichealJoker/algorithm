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
        System.out.println(mult(a,b));
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
    public static int mult(int a, int b) {
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
}

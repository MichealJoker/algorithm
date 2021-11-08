package saomang.bitmap;

/**
 * @author: jzh
 * @date: created in 2021/11/7
 * @description:
 * @version: 1.0
 */
public class BitMap {


    private long[] bits;


    public BitMap(int max) {
        bits = new long[(max + 64) >> 6];
    }

    public void add(int num) {
        // num % 64 和  num & 63 是一样 而且 &运算比%运算快 2的倍数相关的%运算都可以用&运算替代
        bits[num >> 6] |= (1L << (num & 63));
    }

    public void delete(int num) {
        bits[num >> 6] &= ~(1L << (num & 63));
    }

    public boolean contain(int num) {
        return (bits[num >> 6] & (1L << (num & 63))) != 0;
    }

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(Integer.MAX_VALUE - 64);
        System.out.println(bitMap.contain(123123));
        bitMap.add(123123);
        System.out.println(bitMap.contain(123123));
        bitMap.delete(123123);
        System.out.println(bitMap.contain(123123));
    }
}

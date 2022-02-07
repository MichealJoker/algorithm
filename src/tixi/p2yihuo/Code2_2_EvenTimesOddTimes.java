package tixi.p2yihuo;

/**
 * @description: 找出数组中出现奇数次的  数组中只有一个数出现奇数次的，其他的都是偶数次
 * @author: 姜志豪
 * @date: 2021/12/15-11:44
 * @Version: 1.0.0
 */
public class Code2_2_EvenTimesOddTimes {


    public static void main(String[] args) {
        int[] arr={1,2,444,444,22,22,33,33,2,1,7,7,8,9,10,10,8,13};
        printOddTimesNum2(arr);
    }

    //数组中只有一个数出现奇数次的，其他的都是偶数次
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];//出现偶数次的都会异或成0，最后就是 0 ^ x = x
        }
        System.out.println(eor);
    }

    //拿到 一个数二进制最右边的1
    public static int getMostRightOne(int a){
        //自己 与上 自己取反+1 即可拿到最右边的1的值
        return a & (-a);
    }

    //有两种数出现奇数次，其他数出现偶数次
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        int a=0;
        int b=0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        //  此时 eor = a^b  假设为二进制   100
        int mostRightOne = getMostRightOne(eor);
        //得到这个数最右边的1  ，100  代表a和b在这一位上必定不相等
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & mostRightOne) == 0) { //分流
                a ^= arr[i];
            } else {
                b ^= arr[i];
            }
        }
        System.out.println(a);
        System.out.println(b);
    }

    //有一个数出现K次，其他出现M次  k<M ,M>1 要求额外空间复杂度O(1)
    public static void printOddTimesNum3(int[] arr) {

    }


}
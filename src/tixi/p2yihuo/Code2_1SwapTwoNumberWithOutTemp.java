package tixi.p2yihuo;

/**
 * @description: 不用额外变量交换两数
 * @author: 姜志豪
 * @date: 2021/12/14-17:14
 * @Version: 1.0.0
 */
public class Code2_1SwapTwoNumberWithOutTemp {


    public static void swap(int a,int b){
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }

    // a 和 b不能是一个位置 ，不然会报错，会把自己刷成0
    public static void swapArr(int[] arr,int a,int b){
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }

    public static void main(String[] args) {
        int a = 6;
        int b= 6;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);
        int []arr = {6,6};
        swapArr(arr,0,0);
        System.out.println(arr[0]);
    }
}
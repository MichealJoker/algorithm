package tixi.p8Tree;

import java.util.*;

/**
 * @description: 基数排序  复杂度 N * log10 Max  一般情况下算作 O（N）
 * @author: 姜志豪
 * @date: 2022/1/17-12:23
 * @Version: 1.0.0
 */
public class Code04RadixSort {


    //一般情况下，要求是正数，范围10^10之内
    //思路：
    //1准备10个桶
    //2遍历数组，找出最大值，得到它是几位数，循环将重复N次
    //3遍历数组，元素个/十/百/N位数 为几就塞进那个桶
    //4挨个取出桶中元素，然后开始下一轮

    public static void radixSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        radixSort(arr,0,arr.length-1,maxbits(arr));
    }

    public static int maxbits(int[]arr){
        int max = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            max = Math.max(max,arr[i]);
        }
        int res = 0;
        while (max!=0){
            res++;
            max/=10;
        }
        return res;
    }

    // arr[L..R]排序  ,  最大值的十进制位数digit
    public static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        // 有多少个数准备多少个辅助空间
        int[] help = new int[R - L + 1];
        for (int d = 1; d <= digit; d++) { // 有多少位就进出几次
            // 10个空间
            // count[0] 当前位(d位)是0的数字有多少个
            // count[1] 当前位(d位)是(0和1)的数字有多少个
            // count[2] 当前位(d位)是(0、1和2)的数字有多少个
            // count[i] 当前位(d位)是(0~i)的数字有多少个
            int[] count = new int[radix]; // count[0..9]
            for (i = L; i <= R; i++) {
                // 103  1   3
                // 209  1   9
                j = getDigit(arr[i], d);
                count[j]++;
            }
            for (i = 1; i < radix; i++) {//这一步是做出一  X位数累加和的数组 x位小于等于0的有1个 小于等于1的有4个，小于等于2的有5个
                count[i] = count[i] + count[i - 1];//[1,4,5,5,5,5,5,5,5]
            }
            for (i = R; i >= L; i--) { //从右向左，第一个找到的个位数比0小的应该塞第1个，第一个找到的个位数比1小的应该塞第4个
                j = getDigit(arr[i], d);
                help[count[j] - 1] = arr[i];
                count[j]--;
            }
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = help[j];
            }

        }
    }

    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }


    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);

    }





    // arr[L..R]排序  ,  最大值的十进制位数digit
    public static void radixSort2(int[] arr, int L, int R, int digit) {
        final int radix = 10;
        List<Queue> list = new ArrayList<>();
        for (int i = 0; i < radix; i++) {
            list.add(new LinkedList());
        }
        for (int d = 1; d <= digit; d++) { // 有多少位就进出几次
            for (int i = 0; i < R - L + 1; i++) {
                int digit1 = getDigit(arr[i],d);
                list.get(digit1).add(arr[i]);
            }
            int start = 0;
            for (int i = 0; i < radix; i++) {
                Queue queue = list.get(i);
                while (!queue.isEmpty()){
                    arr[start++]= (int) queue.poll();
                }
            }
        }
    }
}
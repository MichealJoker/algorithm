package tixi.p5QuickSort;

import java.util.Arrays;

/**
 * @description: 荷兰国旗改快排   时间复杂度还是O(n^2)
 * @author: 姜志豪
 * @date: 2021/12/27-17:29
 * @Version: 1.0.0
 */
public class Code01_2helanQuickSort {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] helanguoqi(int[]arr,int left,int right){
        if(left>right){
            return new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE};
        }
        if(left==right){
            return new int[]{left,left};
        }
        int windowL = left-1;
        int R = right;
        int windowR = right;
        int index = left;
        while (index<windowR){
            if(arr[index]<arr[R]){
                swap(arr,index++,++windowL);
            }else if(arr[index]>arr[R]){
                swap(arr,index,--windowR);
            }else{
                index++;
            }
        }
        swap(arr,right,windowR);
        return new int[]{windowL+1,windowR};//返回等于区的左边界和右边界
    }

    public static void process(int[]arr,int left,int right){
        if(left>=right){
            return;
        }
        int[] helanguoqi = helanguoqi(arr, left, right);//等于区
        process(arr,left,helanguoqi[0]-1);//arr[0]-1 = 小于区右界
        process(arr,helanguoqi[1]+1,right);//arr[1]+1 = 大于区左界
    }

    public static void quickSort2(int[]arr){
        if(arr==null||arr.length<2){
            return;
        }
        process(arr,0,arr.length-1);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            quickSort2(arr1);
            // printArray(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
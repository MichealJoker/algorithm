package saomang.sort;

import java.util.Arrays;

/**
 * @author: jzh
 * @date: created in 2021/10/31
 * @description: 快速排序
 *  时间复杂度：平均情况 N * logN   最坏的时候是N^2
 *  空间复杂度   logN
 * @version: 1.0
 */
public class QuickSort {


    public static void quickSort(int[] arr,int leftBound, int rightBound){
        if(leftBound>=rightBound){
            return;
        }
        int mid = partiton(arr,leftBound,rightBound);
        quickSort(arr,leftBound,mid-1);
        quickSort(arr,mid+1,rightBound);
    }

    /**
     * 分区 返回轴的位置
     * @param arr
     * @param leftBound
     * @param rightBound
     */
    public static int partiton(int[] arr,int leftBound, int rightBound){
        int pivot = arr[rightBound];
        int left = leftBound;
        int right = rightBound - 1;
        while (left <= right) {
            while (left <= right && arr[left] <= pivot) left++;
            while (left <= right && arr[right] > pivot) right--;
            if (left < right) {
                swap(arr, left, right);
            }
        }
        swap(arr, left, rightBound);
        return left;
    }

    // i和j,数交换
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random() ->  [0,1) 所有的小数，等概率返回一个
        // Math.random() * N -> [0,N) 所有小数，等概率返回一个
        // (int)(Math.random() * N) -> [0,N-1] 所有的整数，等概率返回一个
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())]; // 长度随机
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random())
                    - (int) (maxValue * Math.random());
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
        int maxSize = 100; // 随机数组的长度0～100
        int maxValue = 100;// 值：-100～100
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            quickSort(arr1,0,arr1.length-1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                // 打印arr1
                // 打印arr2
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        quickSort(arr,0,arr.length-1);
        printArray(arr);
    }
}

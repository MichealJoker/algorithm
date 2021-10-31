package saomang.sort;

import java.util.Arrays;

/**
 * @author: jzh
 *  希尔排序 - 改进的插入排序
 *   不稳定   间隔大时移动少  ，间隔少时移动短
 *   空间复杂度没用到额外空间： O(1)
 *   时间复杂度：最优情况：O(n^1.3)
 * @date: created in 2021/10/26
 * @description:
 * @version: 1.0
 */
public class ShellSort {

    public static void insertionSort(int[] arr) {
        int gap = 4;

        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) { // 0 ~ i 做到有序
            for (int j = i - 1; j >= 0 ; j--) {
                if(arr[j] > arr[j + 1]){
                    swap(arr, j, j + 1);
                }

            }
        }
    }

    /**
     * 希尔初稿
     * @param arr
     */
    public static void shellSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int gap = arr.length / 2;
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) { // 0 ~ i 做到有序
                for (int j = i; j > gap - 1; j -= gap) {
                    if (arr[j - gap] > arr[j]) {
                        swap(arr, j, j - gap);
                    }
                }
            }
            gap /= 2;
        }
    }

    /**
     *  3n+1 序列是最高效的
     * @param arr
     */
    public static void shellSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int h = 1;
        while (h <= arr.length / 3) {
            h = h * 3 + 1;
        }
        int gap = h;
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) { // 0 ~ i 做到有序
                for (int j = i; j > gap - 1; j -= gap) {
                    if (arr[j - gap] > arr[j]) {
                        swap(arr, j, j - gap);
                    }
                }
            }
            gap = (gap-1)/3;
        }
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
            shellSort2(arr1);
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
        insertionSort(arr);
        printArray(arr);
    }
}

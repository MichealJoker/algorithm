package saomang.sort;

import java.util.Arrays;

/**
 * @author: jzh
 * @date: created in 2021/12/8
 * @description:
 * @version: 1.0
 */
public class QuickSort2 {

//    public static void main(String[] args) {
//        int []arr = {1,2,3,4,62,3,5,2,7,9,12,4};
//        splitNum2(arr);
//        printArray(arr);
//    }


    //在 arr[L...R]范围上，拿arr[R]做划分值
    public static int[] partition(int[]arr,int L,int R){
        int lessR = L-1;
        int moreL = R;
        int index = L;
        while (index < moreL) {
            if (arr[index] < arr[R]) {
//                swap(arr, index, lessEqualR + 1);
//                lessEqualR++;
//                index++;
                swap(arr, ++lessR, index++);
            } else if (arr[index]>arr[R]) {
                swap(arr,index,--moreL);//大于区换过来没判断过大小，不用让index++
            } else {
                index++;
            }
        }
        swap(arr,moreL,R);
        return new int[]{lessR+1,moreL};//返回等于区域的左边界和右边界
    }

    public static void process(int[]arr,int L,int R){
        if(L>=R){
            return;
        }
        int [] equalE = partition(arr,L,R);
        //equalE[0] 等于区域第一个数
        //equalE[1] 等于区域最后一个数
        process(arr,L,equalE[0]-1);
        process(arr,equalE[1]+1,R);
    }


    public static void quickSort(int []arr){
        if(arr==null||arr.length<2){
            return;
        }
        process(arr,0,arr.length-1);
    }

    //拿数组最右侧值作为基准，小的放它左边，大的放右边
    public static void splitNum1(int[]arr){
        int lessEqualR = -1;//小于等于区 边界
        int index = 0;
        int mostR = arr.length-1;
        while (index < arr.length) {
            if (arr[index] <= arr[mostR]) {
//                swap(arr, index, lessEqualR + 1);
//                lessEqualR++;
//                index++;
                swap(arr, ++lessEqualR, index++);
            } else {
                index++;
            }
        }
    }

    //双向扩容
    public static void splitNum2(int[]arr){
        int lessEqualR = -1;//小于区 右边界
        int index = 0;
        int N = arr.length-1;
        int moreL = arr.length-1;//大于区左边界
        while (index < moreL) {
            if (arr[index] < arr[N]) {
//                swap(arr, index, lessEqualR + 1);
//                lessEqualR++;
//                index++;
                swap(arr, ++lessEqualR, index++);
            } else if (arr[index]>arr[N]) {
                swap(arr,index,--moreL);//大于区换过来没判断过大小，不用让index++
            } else {
                index++;
            }
        }
        swap(arr,moreL,N);
    }


    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index2];
        arr[index2] = arr[index1];
        arr[index1] = temp;
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
            quickSort(arr1);
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
        quickSort(arr);
        printArray(arr);
    }
}

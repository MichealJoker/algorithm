package tixi.p5QuickSort;

import java.util.Arrays;

/**
 * @author: jzh
 * @date: created in 2022/7/2
 * @description:
 * @version: 1.0
 */
public class Test {


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
            quickSort3(arr1);
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


    public static void quickSort3(int []arr){
        if(arr==null||arr.length<2){
            return;
        }
        process(arr,0,arr.length-1);
    }

    public static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        swap(arr,right, (int) (Math.random()*(right-left+1)) +left );
        int[] partition = partition(arr, left, right);
        process(arr,left,partition[0]);
        process(arr,partition[1],right);
    }


    public static int[] partition(int[] arr,int left,int right){
        if(left>=right){
            return new int[]{left,left};
        }
        int windowL = left-1;
        int windowR = right;
        int index = left;
        while (index<windowR){
            if(arr[index]<arr[right]){
                windowL++;
                swap(arr,windowL,index);
                // 为啥这里要swap
                //）4  1   1 （4
                // 1 ）4   1 （4
                // 1   1） 4 （4
                index++;
            }else if(arr[index]>arr[right]){
                swap(arr,index,windowR-1);
                windowR--;
            }else{
                index++;
            }
        }
        swap(arr,right,windowR);
        return new int[]{windowL,windowR+1};
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}

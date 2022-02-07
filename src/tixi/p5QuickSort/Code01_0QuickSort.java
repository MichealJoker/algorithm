package tixi.p5QuickSort;

/**
 * @description: 快排,快排没有稳定性
 * @author: 姜志豪
 * @date: 2021/12/22-18:10
 * @Version: 1.0.0
 */
public class Code01_0QuickSort {

    //荷兰问题 单向分区
    public static int partition1(int[]arr,int left,int right){
        // if(left>right){
        //     return -1;
        // }
        // if(left==right){
        //     return left;
        // }
        int windowL = left-1;
        int R = right;
        int index = left;
        while (index<R){
            if(arr[index]<=arr[R]){
                swap(arr,++windowL,index);
            }
            index++;
        }
        swap(arr,R,++windowL);
        return windowL;
    }

    public static void process1(int []arr,int left ,int right){
        if(left>=right){
            return;
        }
        int i = partition1(arr, left, right);
        process1(arr,left,i-1);
        process1(arr,i+1,right);
    }

    //荷兰国旗问题，快排的基础  双向分区
    public static void partition2(int[] arr) {
        int windowL = -1;
        int windowR = arr.length - 1;
        int index = 0;
        while (index < windowR) {
            if (arr[index] < arr[arr.length - 1]) {
                swap(arr, ++windowL, index++);
            } else if (arr[index] > arr[arr.length - 1]) {
                swap(arr, --windowR, index);
            } else {
                index++;
            }
        }
        swap(arr, windowR, arr.length - 1);
        System.out.println(windowL);
        System.out.println(windowR);
    }

    //快排使用
    public static int[] partition3(int[] arr,int left,int right) {
        if(left==right){
            return new int[]{left,left};
        }
        int windowL = left-1;
        int windowR = right;
        int index = left;
        while (index < windowR) {
            if (arr[index] < arr[arr.length - 1]) {
                swap(arr, ++windowL, index++);
            } else if (arr[index] > arr[arr.length - 1]) {
                swap(arr, --windowR, index);
            } else {
                index++;
            }
        }
        swap(arr, windowR, arr.length - 1);
        int[] equalRange =  {windowL+1,windowR};
        return equalRange;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void quickSort1(int[]arr){
        if(arr==null&&arr.length<2){
            return;
        }
        process1(arr,0,arr.length-1);
    }

    public static void quickSort3(int[]arr){
        if(arr==null&&arr.length<2){
            return;
        }
        process3(arr,0,arr.length-1);
    }

    public static void process3(int []arr,int left,int right){
        if(left>=right){
            return;
        }
        int[] ints = partition3(arr, left, right);
        process3(arr,left,ints[0]-1);
        process3(arr,ints[1]+1,right);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 7, 8, 9, 2, 5};
        // partition2(arr);
        quickSort1(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }

}
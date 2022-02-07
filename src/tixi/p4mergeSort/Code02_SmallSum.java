package tixi.p4mergeSort;

/**
 * @description: 小和问题
 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
 *
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1、3
 * 2左边比2小的数：1
 * 5左边比5小的数：1、3、4、 2
 * 所以数组的小和为1+1+3+1+1+3+4+2=16
 *   // arr[L..R]既要排好序，也要求小和返回
 * 	// 所有merge时，产生的小和，累加
 * @author: 姜志豪
 * @date: 2021/12/20-16:51
 * @Version: 1.0.0
 */
public class Code02_SmallSum {


    public static int smallSum(int[] arr){
        if(arr==null||arr.length<2){
            return 0;
        }
        return process(arr,0,arr.length-1);
    }

    public static int process(int[] arr, int left, int right) {
        if(left>=right){
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return process(arr, left, mid) + process(arr, mid + 1, right) + merge(arr, left, mid, right);
    }



    public static int merge(int[]arr,int left,int mid,int right){
        int L1 = left;
        int L2 = mid +1;
        int index = 0 ;
        int ans = 0;
        int[]temp =  new int[right-left+1];
        while (L1 <= mid && L2 <= right) {
            if (arr[L1] < arr[L2]) {
                temp[index++] = arr[L1];
                ans += arr[L1++]*(right-L2+1);
                //拿到的左组一定是计算小和完成的
                //所以只考虑右组的，右组剩余的比自己大的数有 right-L2 + 1个
            } else {
                temp[index++] = arr[L2++];
            }
        }
        while (L1 <= mid) {
            temp[index++] = arr[L1++];
        }
        while (L2 <= right) {
            temp[index++] = arr[L2++];
        }
        for (index = 0; index < temp.length; index++) {
            arr[left + index] = temp[index];
        }
        return ans;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
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
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
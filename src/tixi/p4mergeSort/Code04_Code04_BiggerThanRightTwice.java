package tixi.p4mergeSort;

/**
 * @description:
 * 在一个数组中，
 * 对于每个数num，求有多少个后面的数 * 2 依然<num，求总个数
 * 比如：[3,1,7,0,2]
 * 3的后面有：1，0
 * 1的后面有：0
 * 7的后面有：0，2
 * 0的后面没有
 * 2的后面没有
 * 所以总共有5个
 *
 * 总结，求一个数右边 XXXXX的，都可以套归并
 * @author: 姜志豪
 * @date: 2021/12/21-10:42
 * @Version: 1.0.0
 */
public class Code04_Code04_BiggerThanRightTwice {


    public static int biggerTwice(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0 ;
        }
       return  process(arr, 0, arr.length-1);
    }

    public static int process(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return process(arr, left, mid)+
        process(arr, mid + 1, right)+
        merge(arr, left, mid, right);
    }


    public static int merge(int[] arr, int L, int mid, int R) {
        int ans = 0;
        int windowR = mid + 1;
        for (int i = L; i <= mid; i++) { //考察左组
            while (windowR <= R && arr[i] > (arr[windowR] * 2)) {
                windowR++;
                // 符合条件的数有[mid+1,WindowR);
            }
            ans += windowR - mid - 1;
        }

        int L2 = mid + 1;
        int L1 = L;
        int index = 0;
        int[] temp = new int[R - L + 1];
        while (L1 <= mid && L2 <= R) {
            temp[index++] = arr[L1] <= arr[L2] ? arr[L1++] : arr[L2++];
        }
        while (L1 <= mid) {
            temp[index++] = arr[L1++];
        }
        while (L2 <= R) {
            temp[index++] = arr[L2++];
        }
        for (index = 0; index < temp.length; index++) {
            arr[L + index] = temp[index];
        }
        return ans;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
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
            if (biggerTwice(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
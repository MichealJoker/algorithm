package tixi.p4mergeSort;

/**
 * @description: 逆序对
 * 在一个数组中，
 * 任何一个前面的数a，和任何一个后面的数b，
 * 如果(a,b)是降序的，就称为逆序对
 * 返回数组中所有的逆序对
 *
 * @author: 姜志豪
 * @date: 2021/12/21-10:18
 * @Version: 1.0.0
 */
public class Code03_ReversePair {




    public static int reverPairNumber(int[] arr){
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


    //本质是求一个数右边有多少个数比它小
    //经典方法是从右向左归并
    public static int merge(int[]arr,int left,int mid,int right){
        int L1 = left;
        int L2 = mid +1;
        int R1 = mid;
        int R2 = right;
        int index = 0 ;
        int tmpInxdex = right-left+1;
        int ans = 0;
        int[]temp =  new int[right-left+1];
        while (L1 <= R1 && L2 <= R2) {
            if (arr[R2] >= arr[R1]) {
                temp[--tmpInxdex] = arr[R2--];
            } else {
                temp[--tmpInxdex] = arr[R1--];
                ans+= (R2-L2)+1;
            }
        }
        // while (p1 >= L && p2 > m) {
        //     res += arr[p1] > arr[p2] ? (p2 - m) : 0;
        //     help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        // }
        while (L1 <= R1) {
            temp[--tmpInxdex] = arr[R1--];
        }
        while (L2 <= R2) {
            temp[--tmpInxdex] = arr[R2--];
        }
        for (index = 0; index < temp.length; index++) {
            arr[left + index] = temp[index];
        }
        return ans;
    }


    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
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
            if (reverPairNumber(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
package tixi.p5QuickSort;

import java.util.Arrays;

/**
 * @description: 正统的快速排序
 *   随机快排才是正统的快速排序
 *   快排没有稳定性
 * @author: 姜志豪
 * @date: 2021/12/28-15:10
 * @Version: 1.0.0
 */
public class Code01_03RandomQuickSort {


    /**
     * 在arr[L..R]范围上，进行快速排序的过程：
     * 1）在这个范围上，随机选一个数记为num，
     * 1）用num对该范围做partition，< num的数在左部分，== num的数中间，>num的数在右部分。假设== num的数所在范围是[a,b]
     * 2）对arr[L..a-1]进行快速排序(递归)
     * 3）对arr[b+1..R]进行快速排序(递归)
     * 因为每一次partition都会搞定一批数的位置且不会再变动，所以排序能完成
     *
     * 1）通过分析知道，划分值越靠近中间，性能越好；越靠近两边，性能越差
     * 2）随机选一个数进行划分的目的就是让好情况和差情况都变成概率事件
     * 3）把每一种情况都列出来，会有每种情况下的时间复杂度，但概率都是1/N
     * 4）那么所有情况都考虑，时间复杂度就是这种概率模型下的长期期望！
     * 时间复杂度O(N*logN)，额外空间复杂度O(logN)都是这么来的。
     */

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
    //Master公式 ，算递归复杂度的,要求子问题规模一致
    //  T(N)= a*T(N/b)+O(N^d)  a,b,d为常数
    //  logb a <d O(N^d)
    //  logb a <d O(N^logb a)
    //  logb a =d O(N^d *logN)
    //递归复杂度  T(N) = 2 * T(N/2) +O(1)        a=2  b=2 d=0
    //                 2个子 * 子过程数据量 + 其他操作复杂度

    public static void process(int[]arr,int left,int right){
        if(left>=right){
            return;
        }
        //在[L,R]上随机选个数，把他和边界值兑换，划分值最大时，复杂度最差O(N^2)
        // 如果抽到边界时正好抽到 中位数，则复杂度 = O（N) + 2*T(N/2)   d = 1 a =2 b =2
        // 求得复杂度 NlogN
        //所以边界值取得好，接近 NlogN ,取的差 N^2
        //但是没法次次命中最好边界值
        //所以把取值做成随机行为，取随机  1/N
        //然后数学家靠概率论算出来，把所有1/N加起来 证明他
        // 复杂度收敛于O(N*logN)  额外空间复杂度 O(logN)
        swap(arr,right,(int)Math.random()*(right-left+1) +left);
        int[] helanguoqi = helanguoqi(arr, left, right);//等于区
        process(arr,left,helanguoqi[0]-1);//arr[0]-1 = 小于区右界
        process(arr,helanguoqi[1]+1,right);//arr[1]+1 = 大于区左界
    }

    public static void quickSort3(int[]arr){
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

}
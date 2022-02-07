package tixi.p4mergeSort;

/**
 *
 * https://leetcode.com/problems/count-of-range-sum/
 * 给定一个数组arr，两个整数lower和upper，
 * 返回arr中有多少个子数组的累加和在[lower,upper]范围上
 * 思路：用原数组 做出 前缀和数组
 *      然后拿前缀和数组去归并
 * @author: 姜志豪
 * @date: 2021/12/21-13:17
 * @Version: 1.0.0
 */
public class Code05_CountOfRangeSum {

    public static int countRangeSum(int[]arr, int lower, int upper){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        long[] sum = new long[arr.length];
        sum[0] = arr[0];
        for(int i =1;i<arr.length;i++){
            sum[i] = sum[i-1] + arr[i];
        }
        return process(sum,0,arr.length-1,lower,upper);
    }


    public static int process(long[] sum,int left ,int right, int lower, int upper){
        if(left==right){
            if(sum[left]<=upper&&sum[left]>=lower){
                return 1;
            }else{
                return 0;
            }
        }
        int mid = left + ((right - left) >> 1);
        return process(sum,left,mid,lower,upper)
                +process(sum,mid+1,right,lower,upper)
                +merge(sum,left,mid,right,lower,upper);

    }


    public static int merge(long[] arr,int left, int mid ,int right,int lower,int upper){
        int ans = 0;

        long[] help = new long[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
        return ans;

    }



    public static int count(long[] sum,int left ,int right, int lower, int upper){
        if(left==right){
            if(sum[left]<=upper&&sum[left]>=lower){
                return 1;
            }else{
                return 0;
            }
        }
        int mid = left + ((right - left) >> 1);
        return count(sum,left,mid,lower,upper)
                +count(sum,mid+1,right,lower,upper)
                +merge2(sum,left,mid,right,lower,upper);

    }


    public static int merge2(long[] arr,int left, int mid ,int right,int lower,int upper){
        //1 不merge  右组自己X，求左组 在 [X-lower,X-upper]范围的数
        // 能出现在右组，就代表该区间的子数组已经算完
        int ans = 0;
        int windowL = left ;
        int windowR = left ;
        for(int i = mid+1;i<=right;i++){
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            while ( arr[windowR]<=max&&windowR<=mid ){
                windowR++;
            }
            while (arr[windowL]< min && windowL<=mid){
                windowL++;
            }
            ans += (windowR - windowL);
        }


        //2正常merge
        long[] help = new long[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
        return ans;


    }
}
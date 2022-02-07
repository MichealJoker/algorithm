package tixi.p3shujujiegou;

/**
 * @description:  求数组中的最大值，但用递归实现
 * @author: 姜志豪
 * @date: 2021/12/17-13:32
 * @Version: 1.0.0
 */
public class Code08_GetMax {


    //任何递归 都可以 改成非递归
    //因为系统上递归的实现为把方法压进系统栈
    //  f(arr,0,0)f(arr,1,1)  f(arr,2,2)f(arr,3,3)
    //      f(arr,0,1)            f(arr,2,3)
    //                 f(arr,0,3)
    // 求arr中的最大值
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]范围上求最大值  L ... R   N
    public static int process(int[] arr, int L, int R) {
        // arr[L..R]范围上只有一个数，直接返回，base case
        if (L == R) {
            return arr[L];
        }
        // L...R 不只一个数
        // mid = (L + R) / 2
        int mid = L + ((R - L) >> 1); // 中点   	1
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }
    //Master公式 ，算递归复杂度的,要求子问题规模一致
    //  T(N)= a*T(N/b)+O(N^d)  a,b,d为常数
    //  logb a <d O(N^d)
    //  logb a <d O(N^logb a)
    //  logb a =d O(N^d *logN)
    //递归复杂度  T(N) = 2 * T(N/2) +O(1)        a=2  b=2 d=0
    //                 2个子 * 子过程数据量 + 其他操作复杂度
}
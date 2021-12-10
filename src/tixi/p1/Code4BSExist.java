package tixi.p1;

/**
 * @author: jzh
 * @date: created in 2021/12/11
 * @description: 二分
 * @version: 1.0
 */
public class Code4BSExist {


    public boolean exist(int[] arr, int target) {
        if (arr == null || arr.length < 1) {
            return false;
        }
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left < right) {//该条件要求数组至少有2个元素
            mid = left + (right - left) >> 1;
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return arr[left] == target;//数组只有一个元素不进上面while直接到这
    }
}

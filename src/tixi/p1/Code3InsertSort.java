package tixi.p1;

/**
 * @author: jzh
 * @date: created in 2021/12/10
 * @description: 最烂情况复杂度O(N ^2), 数据情况好的时候 O（N）
 * @version: 1.0
 */
public class Code3InsertSort extends BaseCase {


    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {// 0~i上做到有序
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                //相较于冒泡排序，在数组略微有点顺序的情况下，表现良好
                swap(arr, j, j + 1);
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Code3InsertSort c3 = new Code3InsertSort();
        c3.testSort(c3);
    }
}

package tixi.p1;

/**
 * @author: jzh
 * @date: created in 2021/12/10
 * @description:
 * @version: 1.0
 */
public class Code1SelectSort extends BaseCase {


    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, min, i);
        }
    }


    public static void main(String[] args) throws Exception {
        Code1SelectSort code1SelectSort = new Code1SelectSort();
        code1SelectSort.testSort(code1SelectSort);
    }
}

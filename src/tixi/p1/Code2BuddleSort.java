package tixi.p1;

/**
 * @author: jzh
 * @date: created in 2021/12/10
 * @description:
 * @version: 1.0
 */
public class Code2BuddleSort extends BaseCase {

    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j >= 1; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Code2BuddleSort c2 = new Code2BuddleSort();
        c2.testSort(c2);
    }
}

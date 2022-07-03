package tixi.p6Heap;

/**
 * @author: jzh
 * @date: created in 2022/7/3
 * @description:
 * @version: 1.0
 */
public class TestHeap {


    /**
     * 堆排序：
     * 1数组转大根堆
     * 2不断把堆顶塞数组 ，从数尾塞到数组头
     */
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 6, 2, 87, 2, 1, 6, 2, 6, 23, 46};
        heartSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


    public static void heartSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int N = arr.length ;
        //不断地
        // 1把大根堆堆顶的元素换到最后，
        // 2然后堆size-1
        // 3然后换上来的下沉
        swap(arr, 0, --N);
        while (N > 0) {
            heapify(arr, 0, N);
            swap(arr, 0, --N);
        }
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            int son = (index - 1) / 2;
            swap(arr, index, son);
            index = son;
        }
    }

    public static void heapify(int[] arr, int index, int heapsize) {
        int left = index * 2 + 1;
        while (left < heapsize) {
            int right = left + 1;
            int maxSon = left;
            if (right < heapsize && arr[right] > arr[left]) {
                maxSon = right;
            }
            if (arr[maxSon] > arr[index]) {
                swap(arr, index, maxSon);
                index = maxSon;
                left = index *2 +1;
            }else{
                return;
            }
        }
    }


    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}

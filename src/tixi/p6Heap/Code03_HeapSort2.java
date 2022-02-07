package tixi.p6Heap;

/**
 * @description:
 * @author: 姜志豪
 * @date: 2021/12/31-18:39
 * @Version: 1.0.0
 */
public class Code03_HeapSort2 {

    /**
     * 堆排序：
     * 1数组转大根堆
     * 2不断把堆顶塞数组 ，从数尾塞到数组头
     *
     */
    public static void heartSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 正向，复杂度O(N*logN)
        // for (int i = 0; i < arr.length; i++) {
        //     heapInsert(arr, i);
        // }
        //反向 从尾向上 复杂度 O(N)
        for(int i=arr.length-1;i>=0;i--){
            heapify(arr,i,arr.length);
        }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    //插尾，如果比父大，不断上浮
    private static void heapInsert(int[] arr, int index) {
        while (arr[index]>arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index = (index-1)/2;
        }
    }

    // 从index位置，往下看，不断的下沉
    // 停：较大的孩子都不再比index位置的数大；已经没孩子了
    private static void heapify(int[]arr,int index,int heapsize){
        int left = index*2 +1;
        while (left<heapsize){
            int bigest = left;
            if(left+1<heapsize){
                int right = left+1;
                bigest = arr[left]>=arr[right]?left:right;
            }
            bigest = arr[index]<arr[bigest]?bigest:index;
            if(bigest==index){
                break;
            }
            swap(arr,index,bigest);
            index = bigest;
            left = index*2+1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,6,2,87,2,1,6,2,6,23,46};
        heartSort(arr);
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
}
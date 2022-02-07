package tixi.p6Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @description: 优先级队列=堆
 * @author: 姜志豪
 * @date: 2021/12/29-15:28
 * @Version: 1.0.0
 */
public class Code02_Heap {
    /**
     * 1）堆结构就是用数组实现的完全二叉树结构
     * 完全二叉树：叶子满的，或者在叶子满的路上
     * 也就是插入新元素永远按 头左右（前序）的顺序插，不管大小
     * 元素下标满足 左： 2*i +1 右2*i +2  头(i-1)/2
     * 9                  0      [0123456........]
     * 7   3              1   2
     * 6 5 2 0            3 4 5 6
     * <p>
     * 2）完全二叉树中如果每棵子树的最大值都在顶部就是大根堆
     * 3）完全二叉树中如果每棵子树的最小值都在顶部就是小根堆
     * 4）堆结构的heapInsert与heapify操作
     * 5）堆结构的增大和减少
     * 6）优先级队列结构，就是堆结构    PriorityQueue是个小根堆
     */


    //新加来的数，先加在index位置，然后不停的和父节点比较
    //大根堆 直到移到0位置，或者比父节点小
    private void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    // 从index位置，往下看，不断的下沉
    // 停：较大的孩子都不再比index位置的数大；已经没孩子了
    private void heapify(int[]arr,int index,int heapsize){
        int left = index * 2 + 1;
        while (left < heapsize) {
            int right = left + 1;
            int largest = left;
            if (right < heapsize && arr[right] > arr[left]) {
                largest = right; //选大的子节点
            }
            largest = arr[largest] > arr[index] ? largest : index; //子和头谁大
            if (largest == index) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }


    // public static void main(String[] args) {
    //     PriorityQueue<Integer> priorityQueue = new PriorityQueue(new Comparator<Integer>() {
    //         @Override
    //         public int compare(Integer o1, Integer o2) {
    //             return o2-o1;
    //         }
    //     });
    //     priorityQueue.add(1);
    //     priorityQueue.add(3);
    //     priorityQueue.add(5);
    //     priorityQueue.add(3);
    //     while (!priorityQueue.isEmpty()){
    //         System.out.println(priorityQueue.poll());
    //     }
    // }



}
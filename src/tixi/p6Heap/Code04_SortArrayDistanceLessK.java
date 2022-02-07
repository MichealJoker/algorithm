package tixi.p6Heap;

import java.util.PriorityQueue;

/**
 *  已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
 *
 * 请选择一个合适的排序策略，对这个数组进行排序。
 * @author: 姜志豪
 * @date: 2022/1/4-18:24
 * @Version: 1.0.0
 */
public class Code04_SortArrayDistanceLessK {


    //首先把[0,k)上的数加进小跟堆  arr[0]的数肯定就进堆了，并且是小根堆，那么堆顶就是最小数
    //然后重复 1取走堆顶，加入数组；2朝堆新加一个元素
    //直到堆不够k个元素，然后依次把堆剩余元素加进数组，结束
    //复杂度 N*logk
    public static void sortedArrDistanceLessK(int[] arr, int k) {
        if (k == 0) {
            return;
        }
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        // 0...K-1
        for (; index <= Math.min(arr.length - 1, k - 1); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

}
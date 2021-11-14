package saomang.comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: jzh
 * @date: created in 2021/11/12
 * @description:
 * @version: 1.0
 */
public class ShowComparator2 {


    public static class MyCompartor implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            if(o1>o2){
                return -1;
            }else {
                return 1;
            }
        }
    }

    public static void main(String[] args) {
        //优先级队列（堆），logN级别的排序，默认小的在前面,
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(6);
        heap.add(2);
        heap.add(3);
        heap.add(1);
        heap.add(7);
        System.out.println(heap.peek());
        heap.add(0);
        System.out.println(heap.peek());
        while (!heap.isEmpty()){
            System.out.println(heap.poll());
        }
        System.out.println("============");
        PriorityQueue<Integer> heap2 = new PriorityQueue<>(new MyCompartor());
        heap2.add(6);
        heap2.add(2);
        heap2.add(3);
        heap2.add(1);
        heap2.add(7);
        System.out.println(heap2.peek());
        heap2.add(0);
        System.out.println(heap2.peek());
        while (!heap2.isEmpty()){
            System.out.println(heap2.poll());
        }
    }
}

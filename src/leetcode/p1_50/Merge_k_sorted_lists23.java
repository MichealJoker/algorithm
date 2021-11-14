package leetcode.p1_50;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: jzh
 * @date: created in 2021/11/14
 * @description: https://leetcode.com/problems/merge-k-sorted-lists/  23. 合并K个升序链表
 * @version: 1.0
 */

public class Merge_k_sorted_lists23 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() { }
        ListNode(int val) {this.val = val; }
        ListNode(int val, ListNode next) { this.val = val;this.next = next; }
    }

    /**
     * 实现一个比较器给优先级队列比大小
     */
    public static class ListNodeComparator implements Comparator<ListNode>{
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

    public static ListNode mergeKList(ListNode[] lists) {
        if(lists == null){
            return null;
        }
        /**
         * 使用java的优先级队列把每个数组的头节点装入
         */
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
        for(int i=0;i<lists.length;i++){
            if(lists[i]!=null){
                heap.add(lists[i]);
            }
        }
        if(heap.isEmpty()){
            return null;
        }
        ListNode head = heap.poll();
        ListNode pre = head;
        if(pre.next!=null){
            heap.add(pre.next);
        }
        while (!heap.isEmpty()){
            ListNode cur = heap.poll();
            pre.next = cur;
            pre = cur;
            if(cur.next!=null){
                heap.add(cur.next);
            }
        }
        return head;
    }
}

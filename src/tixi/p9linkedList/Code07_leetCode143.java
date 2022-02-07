package tixi.p9linkedList;

/**
 * @description: https://leetcode-cn.com/problems/reorder-list/  重排链表
 * @author: 姜志豪
 * @date: 2022/1/19-15:24
 * @Version: 1.0.0
 */
public class Code07_leetCode143 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    public void reorderList(ListNode head) {
        if(head==null||head.next==null||head.next.next==null){
            return;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while(fast.next!=null&&fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode rightStart = slow.next;
        slow.next = null;
        ListNode next = null;
        ListNode pre = null;
        while(rightStart!=null){
            next = rightStart.next;
            rightStart.next = pre;
            pre = rightStart;
            rightStart = next;
        }
        slow = head;
        while(slow!=null&&pre!=null){
            next = slow.next;
            slow.next = pre;
            pre = pre.next;
            slow.next.next = next;
            slow = next;
        }

    }
}
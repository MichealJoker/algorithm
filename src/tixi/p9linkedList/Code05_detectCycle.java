package tixi.p9linkedList;

/**
 * @description: https://leetcode-cn.com/problems/linked-list-cycle-ii/submissions/
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 不允许修改 链表。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 姜志豪
 * @date: 2022/1/18-13:37
 * @Version: 1.0.0
 */
public class Code05_detectCycle {

   static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode detectCycle(ListNode head) {
        if(head==null||head.next==null||head.next.next==null){
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        ListNode tmp = null;
        while(fast.next!=null&&fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow.val == fast.val){
                tmp = head;
                while(tmp.val != slow.val){
                    tmp = tmp.next;
                    slow = slow.next;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(0);
        ListNode listNode4 = new ListNode(-4);
        listNode1.next=listNode2;
        listNode2.next=listNode3;
        listNode3.next=listNode4;
        listNode4.next=listNode2;
        detectCycle(listNode1);
    }
}
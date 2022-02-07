package tixi.p10binaryTree;

import tixi.p9linkedList.Code07_leetCode143;

/**
 * @description:
 * @author: 姜志豪
 * @date: 2022/1/20-13:30
 * @Version: 1.0.0
 */
public class Code01_bugOfRemoveLinkedList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    //这样投机取巧的删除链表节点，看似复杂度低，但是有bug
    //就是无法正确的删除尾巴节点
    //1->2->3->4->5->null;
    //该方法无法让4指向null;
    // 并且如果删的是4，如果有外部引用指向4，该方法只能值替换，无法让外部引用指向新4
    //想正确删除一个节点，必须从头开始遍历，详细参考LinkedList
    public static void remove(ListNode node){
        ListNode next = node.next;
        if (next == null) {
            node = null;
            return;
        }
        node.val = next.val;
        if (next.next != null) {
            node.next = next.next;
        } else {
            node.next = null;
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode.next = listNode2;
        listNode2.next = listNode3;
        remove(listNode2);
        System.out.println(listNode.val);
        System.out.println(listNode.next.val);
        System.out.println(listNode.next.next.val);
    }
}
package tixi.p9linkedList;

/**
 * @description: 单链表顶级题
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
 * 【要求】
 * 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
 *
 * 能不能不给单链表的头节点，只给想要删除的节点，就能做到在链表上把这个点删掉？
 * @author: 姜志豪
 * @date: 2022/1/19-11:22
 * @Version: 1.0.0
 */
public class Code06_FindFirstIntersectNode {


    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    //一、首先判断链表有没有环，找到入环节点
    //1。无脑方法，用set
    //2 快慢指针 2步指针和1步指针能相交就有环
    //  然后起点来个新的1步指针，旧1步指针从交点出发，两者同时行进，交点必然是入环节点
// 找到链表第一个入环节点，如果无环，返回null
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        // n1 慢  n2 快
        Node slow = head.next; // n1 -> slow
        Node fast = head.next.next; // n2 -> fast
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        // slow fast  相遇
        fast = head; // n2 -> walk again from head
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //二
    //链表一无环 链表二无环
    //2.1：Set，先把一个链表全塞进去，之后循环判断第二个
    //2.2 直接去比链表的最后一个节点，如果相等就相交，这个过程同时记下两个链表的长度 l1  l2
    // 如果l1>=l2 , 让链表1先走（l1-l2)步，然后两链表一起走，就能得到第一个交点
    public static Node noLoop(Node head1,Node head2){
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            cur1 = cur1.next;
            n++;
        }
        while (cur2.next != null) {
            cur2 = cur2.next;
            n--;
        }
        if (cur1 != cur2) {//尾节点
            return null;
        }
        cur1 = n >= 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n > 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    //三一个链表有环，另外一个链表无环
    //不可能相交


    //四两个都有环
    //4.1不相交
    //4.2交点在环外面，入环节点是一个 loop1==loop2
    //4.3交点在环里面
    //4.1和4.3区分法，loop1出发转1圈，看看能不能碰到loop2
    // 两个有环链表，返回第一个相交节点，如果不想交返回null
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }
}
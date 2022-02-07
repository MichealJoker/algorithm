package tixi.p9linkedList;

/**
 * @description:
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 *
 * 1）把链表放入数组里，在数组上做partition（笔试用）
 *
 * 2）分成小、中、大三部分，再把各个部分之间串起来（面试用）
 *
 * @author: 姜志豪
 * @date: 2022/1/18-16:19
 * @Version: 1.0.0
 */
public class Code03_SmallerEqualBigger {

    //
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node listPartition2(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node smallHead = null;
        Node smallTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node bigHead = null;
        Node bigTail = null;
        Node next = null;
        while (head!=null){
            next = head.next;
            head.next=null;
            if (head.value < pivot) {
                if (smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                }else{
                    smallTail.next = head;
                    smallTail = smallTail.next;
                }
            }else if(head.value == pivot){
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                }else{
                    equalTail.next = head;
                    equalTail=equalTail.next;
                }
            }else{
                if (bigHead == null) {
                    bigHead = head;
                    bigTail = head;
                }else{
                    bigTail.next = head;
                    bigTail = bigTail.next;
                }
            }
            head = next;

        }
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        if (smallTail != null) { // 如果有小于区域
            smallTail.next = equalHead;
            equalTail = equalTail == null ? smallTail : equalTail; // 下一步，谁去连大于区域的头，谁就变成eT
        }
        // 下一步，一定是需要用eT 去接 大于区域的头
        // 有等于区域，eT -> 等于区域的尾结点
        // 无等于区域，eT -> 小于区域的尾结点
        // eT 尽量不为空的尾巴节点
        if (equalTail != null) { // 如果小于区域和等于区域，不是都没有
            equalTail.next = bigHead;
        }
        return smallHead != null ? smallHead : (equalHead != null ? equalHead : bigHead);
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        // head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }



    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    public static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].value == pivot) {
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }
}
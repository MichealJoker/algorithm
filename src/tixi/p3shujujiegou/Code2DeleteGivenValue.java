package tixi.p3shujujiegou;

/**
 * @description: 把给定的值都删除
 * https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/submissions/
 * @author: 姜志豪
 * @date: 2021/12/16-15:23
 * @Version: 1.0.0
 */
public class Code2DeleteGivenValue {


    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    //有可能刚好头是要删的
    public static Node removeValue(Node head, int num) {
        //找到第一个不需要删的
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur!=null){
            if(cur.value ==num){
                pre.next = cur.next;
            }else{
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
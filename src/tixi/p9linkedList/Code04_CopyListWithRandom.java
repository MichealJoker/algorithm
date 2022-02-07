package tixi.p9linkedList;

import java.util.HashMap;

/**
 * @description:
 * 一种特殊的单链表节点类描述如下
 * class Node {
 * int value;
 * Node next;
 * Node rand;
 * Node(int val) { value = val; }
 * }
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 * 【要求】
 * 时间复杂度O(N)，额外空间复杂度O(1)
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 * @author: 姜志豪
 * @date: 2022/1/18-17:23
 * @Version: 1.0.0
 */
public class Code04_CopyListWithRandom {
    public static class Node {
        int val;
        Node next;
        Node random;
        Node(int val) { val = val; }
    }

    //拷贝链表
    //1无脑方法，hashMap<老节点，新节点> 循环两遍
    public static Node copyRandomList1(Node head) {
        // key 老节点
        // value 新节点
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            // cur 老
            // map.get(cur) 新
            // 新.next ->  cur.next克隆节点找到
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
}
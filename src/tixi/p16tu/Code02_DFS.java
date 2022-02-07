package tixi.p16tu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @description:
 * @author: 姜志豪
 * @date: 2022/1/30-13:25
 * @Version: 1.0.0
 */
public class Code02_DFS {


    //深度优先遍历，一条路没走完就走到死
    // 1，利用栈实现
    // 2，从源节点开始把节点按照深度放入栈，然后弹出
    // 3，每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈
    // 4，直到栈变空

    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;//这个break是必须的，它实现了 3，每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈
                }
            }
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        head.nexts.add(node2);
        head.nexts.add(node3);
        head.nexts.add(node4);
        node2.nexts.add(node5);
        dfs(head);
        System.out.println("-====");
        dfs2(head, new HashSet<>());
    }


    public static void dfs2(Node node, Set<Node> set) {
        if (node == null) {
            return;
        }
        System.out.println(node.value);
        for (Node next : node.nexts) {
            if (!set.contains(next)) {
                set.add(next);
                dfs2(next, set);
            }
        }
    }
}
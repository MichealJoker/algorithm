package tixi.p16tu;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @description:
 * @author: 姜志豪
 * @date: 2022/1/30-13:24
 * @Version: 1.0.0
 */
public class Code01_BFS {

    //宽度优先遍历 (BFS
    //  就是用队列来实现二叉树先序打印的那个结构
    //  图结构的BFS要额外多一个set，因为节点可能转回来，需要用set记录下节点来过了没
    // 1，利用队列实现
    // 2，从源节点开始依次按照宽度进队列，然后弹出
    // 3，每弹出一个点，把该节点所有没有进过队列的邻接点放入队列
    // 4，直到队列变空

    public static void bfs(Node node){
        if(node==null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
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
        bfs(head);
    }

}
package tixi.p12binaryTree3;

/**
 * @description: 是不是满二叉树
 * @author: 姜志豪
 * @date: 2022/1/21-18:39
 * @Version: 1.0.0
 */
public class Code04_IsFull {


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    //收集最大高度，总结点数，看看满不满足 2^h -1 = nodes
    public static class Info1 {
        public int height;
        public int nodes;

        public Info1(int h, int n) {
            height = h;
            nodes = n;
        }
    }

    public static boolean isFull(Node head) {
        Info1 info1 = process(head);
        return (1 << info1.height - 1) == info1.nodes;
    }

    public static Info1 process(Node head){
        if(head==null){
            return new Info1(0,0);
        }
        Info1 leftInfo = process(head.left);
        Info1 rightInfo = process(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info1(height, nodes);
    }
}
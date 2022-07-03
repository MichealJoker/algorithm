package tixi.p12binaryTree3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 是不是完全二叉树
 * @author: 姜志豪
 * @date: 2022/1/21-11:28
 * @Version: 1.0.0
 */
public class Code01_IsCBT {



    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    //判断二叉树是否是完全二叉树
    //1 如果一个节点有右子，但无左子， 铁定不是
    //2 如果第一次发现一个节点 左右子不双全，那么之后的所有节点都必须是叶子节点 ，否则就不是完全二叉树
    public static boolean isCBT1(Node head) {
        if(head==null){
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        boolean flag = false;
        Node left = null;
        Node right = null;
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            left = poll.left;
            right = poll.right;
            if (right != null && left == null) { //1 如果一个节点有右子，但无左子， 铁定不是
                return false;
            }
            if (flag && (left != null || right != null)) { //如果第一次发现一个节点 左右子不双全，那么之后的所有节点都必须是叶子节点 ，否则就不是完全二叉树
                return false;
            }
            if(left!=null){
                queue.add(left);
            }
            if(right!=null){
                queue.add(right);
            }
            if(left==null||right==null){
                flag =true;
            }
        }
        return true;
    }

    public static boolean isCBT2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    // 对每一棵子树，是否是满二叉树、是否是完全二叉树、高度
    public static class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean full, boolean cbt, int h) {
            isFull = full;
            isCBT = cbt;
            height = h;
        }
    }

    public static Info process(Node X) {
        if (X == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull
                &&
                rightInfo.isFull
                && leftInfo.height == rightInfo.height;


        boolean isCBT = false;
        if (isFull) {
            isCBT = true;
        } else { // 以x为头整棵树，不满
            if (leftInfo.isCBT && rightInfo.isCBT) {


                if (leftInfo.isCBT
                        && rightInfo.isFull
                        && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                if (leftInfo.isFull
                        &&
                        rightInfo.isFull
                        && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                if (leftInfo.isFull
                        && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
                    isCBT = true;
                }


            }
        }
        return new Info(isFull, isCBT, height);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
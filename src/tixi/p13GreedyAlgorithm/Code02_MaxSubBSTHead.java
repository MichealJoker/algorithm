package tixi.p13GreedyAlgorithm;

import java.util.ArrayList;

/**
 * @description:
 * @author: 姜志豪
 * @date: 2022/1/25-10:12
 * @Version: 1.0.0
 */
public class Code02_MaxSubBSTHead {

    //给定一棵二叉树的头节点head，
    // 返回这颗二叉树中最大的二叉搜索子树的头节点

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static Node maxSubBSTHead1(Node head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        Node leftAns = maxSubBSTHead1(head.left);
        Node rightAns = maxSubBSTHead1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
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
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTHead1(head) != maxSubBSTHead2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    public static Node maxSubBSTHead2(Node head){
        if(head==null){
            return null;
        }
        return process(head).head;
    }

    public static class Info{
        public int max;
        public int min;
        public Node head;
        public int maxBSTSubtreeSize;

        public Info(int max,int min,Node head,int maxBSTSubtreeSize){
            this.max = max;
            this.min = min;
            this.head = head;
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
        }
    }

    public static Info process(Node head){
        if(head == null){
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int max = head.value;
        int min = head.value;
        int maxBSTSubtreeSize = 0;
        Node top = head;
        if(leftInfo!=null){
            max = Math.max(max,leftInfo.max);
            min = Math.min(min,leftInfo.min);
            maxBSTSubtreeSize = leftInfo.maxBSTSubtreeSize;
            top = leftInfo.head;
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            if (rightInfo.maxBSTSubtreeSize > maxBSTSubtreeSize) {
                top = rightInfo.head;
                maxBSTSubtreeSize = rightInfo.maxBSTSubtreeSize;
            }
        }
        boolean leftBST = leftInfo == null ? true : (leftInfo.head == head.left && head.value > leftInfo.max);
        boolean rightBST = rightInfo == null ? true : (rightInfo.head == head.right && head.value < rightInfo.min);
        if (leftBST && rightBST) {
            top = head;
            maxBSTSubtreeSize = 1;
            maxBSTSubtreeSize += leftInfo == null ? 0 : leftInfo.maxBSTSubtreeSize;
            maxBSTSubtreeSize += rightInfo == null ? 0 : rightInfo.maxBSTSubtreeSize;
        }
        return new Info(max, min, top, maxBSTSubtreeSize);
    }
}
package tixi.p12binaryTree3;

import java.util.ArrayList;

/**
 * @description: 是不是搜索二叉树
 * @author: 姜志豪
 * @date: 2022/1/21-14:03
 * @Version: 1.0.0
 */
public class Code02_IsBST {


    //每一颗子树，左树的值都比头小，右树的值都比头大，
    //解答：
    //1左树是搜索二叉树
    //2右树是搜索二叉树
    //3自己比左树最大值大
    //4自己比右树最小值小
    public static boolean isBST2(Node head) {
        if(head == null){
            return true;
        }
        return process(head).isBST;
    }

    public static Info process(Node head){
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int max = head.value;
        int min = head.value;
        boolean isBST = true;
        if(leftInfo!=null){
            max = Math.max(leftInfo.maxValue,max);
        }
        if(rightInfo!=null){
            max = Math.max(rightInfo.maxValue,max);
        }
        if(leftInfo!=null){
            min = Math.min(leftInfo.minValue,min);
        }
        if(rightInfo!=null){
            min = Math.min(rightInfo.minValue,min);
        }
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        if (leftInfo != null && leftInfo.maxValue >= head.value) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.minValue <= head.value) {
            isBST = false;
        }
        return new Info(isBST,min,max);
    }

    public static class Info{
        public boolean isBST;
        public int maxValue;
        public int minValue;
        public Info(boolean isBST,int minValue,int maxValue){
            this.isBST = isBST;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //解答：中序遍历，看看是不是递增的
    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
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
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
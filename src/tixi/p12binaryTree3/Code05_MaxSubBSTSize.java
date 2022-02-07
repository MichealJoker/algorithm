package tixi.p12binaryTree3;

import java.util.ArrayList;

/**
 * @description:
 * @author: 姜志豪
 * @date: 2022/1/24-14:03
 * @Version: 1.0.0
 */
public class Code05_MaxSubBSTSize {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //给定一棵二叉树的头节点head，
    // 返回这颗二叉树中最大的二叉搜索子树的大小
    // 整个树不一定是，但子树是搜索二叉树，从里面找出最大的，看看它有多少节点


    //1左数是不是搜索二叉树
    //2右数是不是搜索二叉树
    //3左max<x
    //4右min>x
    //5左节点数+右节点数+1

    public static class Info{
        public int maxBSTSubtreeSize;
        public int allSize;
        public int max;
        public int min;
        // public boolean isBST;  可以靠参数1==参数2求得
        public Info(int m, int a, int ma, int mi) {
            maxBSTSubtreeSize = m;
            allSize = a;
            max = ma;
            min = mi;
        }
    }


    public static int maxSubBSTSize2(Node head){
        if(head==null){
            return 0;
        }
        return process(head).maxBSTSubtreeSize;
    }


    public static Info process(Node x){
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int max = x.value;
        int min = x.value;
        int allSize = 1;
        int maxBSTSubtreeSize = 1;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            allSize += leftInfo.allSize;
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            allSize += rightInfo.allSize;
        }
        int p1 = -1;
        if (leftInfo != null) {
            p1 = leftInfo.maxBSTSubtreeSize;
        }
        int p2 = -1;
        if (rightInfo != null) {
            p2 = rightInfo.maxBSTSubtreeSize;
        }
        int p3 = -1;
        boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
        boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
        if (leftBST && rightBST) {
            boolean leftMaxLessX = leftInfo == null ? true : (x.value > leftInfo.max);
            boolean rightMinMoreX = rightInfo == null ? true : (x.value < rightInfo.min);
            if (leftMaxLessX && rightMinMoreX) {
                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                p3 = 1 + leftSize + rightSize;
            }
        }
        maxBSTSubtreeSize = Math.max(Math.max(p1, p2), p3);
        return new Info(maxBSTSubtreeSize, allSize, max, min);
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
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
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

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
    }

}
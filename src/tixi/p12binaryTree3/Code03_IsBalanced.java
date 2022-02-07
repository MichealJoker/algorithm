package tixi.p12binaryTree3;

/**
 * @description: 判断是不是 平衡二叉树
 * @author: 姜志豪
 * @date: 2022/1/21-14:15
 * @Version: 1.0.0
 */
public class Code03_IsBalanced {

    //每一棵子树，左树最大高度和右树最大高度差值不超过1

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isBalanced(Node head){
        return process(head).isBalanced;
    }

    public static class Info{
        public boolean isBalanced;
        public int height;

        public Info(boolean i,int h){
            isBalanced = i;
            height = h;
        }
    }

    public static Info process(Node head){
        if (head == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = true;
        if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalanced = false;
        }
        if (!leftInfo.isBalanced) {
            isBalanced = false;
        }
        if (!rightInfo.isBalanced) {
            isBalanced = false;
        }
        return new Info(isBalanced, height);
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
            if (isBalanced1(head) != isBalanced(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

}
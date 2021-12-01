package leetcode.p51_100;

/**
 * @author: jzh
 * @date: created in 2021/12/1
 * @description: 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * @version: 1.0
 */
public class P110 {


    public boolean isBalanced(TreeNode root) {
        Info info = process(root);
        return info.isBalanced;
    }

    public class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean i, int h) {
            isBalanced = i;
            height = h;
        }
    }

    public Info process(TreeNode x) {
        if (x == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBanlance = leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height - rightInfo.height) < 2;
        return new Info(isBanlance, height);
    }
}

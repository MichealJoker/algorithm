package leetcode.p51_100;

/**
 * @author: jzh
 * @date: created in 2021/11/14
 * @description: 100. 相同的树  https://leetcode-cn.com/problems/same-tree
 * @version: 1.0
 *  二叉树  先序：头左右
 *          中序：左头右
 *          后序：左右头
 */
public class SameTree100 {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right) && p.val == q.val;
    }
}

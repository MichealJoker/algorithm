package leetcode.p51_100;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: jzh 二叉树题 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * @date: created in 2021/11/20
 * @description: https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 * @version: 1.0
 */
public class P107 {



    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curAns = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                curAns.add(curNode.val);
                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }
            }
            ans.add(0, curAns);
        }
        return ans;
    }
}

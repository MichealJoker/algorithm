package leetcode.p51_100;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: leetcode 102 二叉树的层序遍历   https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * @author: 姜志豪
 * @date: 2021/11/17-15:13
 * @Version: 1.0.0
 */
public class L102 {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        viewNode(root,0,list);
        return list;
    }

    public void viewNode(TreeNode node, int level,List<List<Integer>> list ) {
        if (node == null) {
            return;
        }
        List<Integer> levelList  ;
        if(list.size()<level+1) {
            levelList = new ArrayList<>();
            list.add(levelList);
        }else {
            levelList = list.get(level);
        }
        levelList.add(node.val);
        viewNode(node.left,++level,list);
        viewNode(node.right,level,list);
    }

}


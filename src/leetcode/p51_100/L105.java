package leetcode.p51_100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jzh  二叉树题
 * @date: created in 2021/1 1/20
 * @description:  https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 *   给前序数组和中序数组 然后建树
 * @version: 1.0
 */
public class L105 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder==null || inorder == null || preorder.length != inorder.length){
            return null;
        }
        return f(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }

    /**
     * 优化版 空间换时间
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if(preorder==null || inorder == null || preorder.length != inorder.length){
            return null;
        }
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        return f2(preorder,0,preorder.length-1,inorder,0,inorder.length-1,map);
    }

    //一棵树 先序结果是pre(L1-R1) 中序结果是in(L2-R2)
    public TreeNode f(int[] pre, int L1, int R1, int[] in, int L2, int R2) {
        if(L1>R1){
            return null;
        }
        TreeNode head = new TreeNode(pre[L1]);
        if(L1 == R1){
            return head;
        }
        int find = L2;
        while (in[find]!=pre[L1]){//找头节点的值  在中序数组里找到头节点后，代表左边的L2~find-1都是该节点的左树
            find++;
        }
        // preorder = [3,9,20,15,7], order =  [9,3,15,20,7]
        head.left = f(pre,L1+1,L1+find-L2,in,L2,find-1);
        head.right = f(pre,L1+find-L2+1,R1,in,find+1,R2);
        return head;
    }

    public TreeNode f2(int[] pre, int L1, int R1, int[] in, int L2, int R2, Map<Integer,Integer> map ) {
        if(L1>R1){
            return null;
        }
        TreeNode head = new TreeNode(pre[L1]);
        if(L1 == R1){
            return head;
        }
        int find = map.get(pre[L1]);//改用map 直接从里找出头节点在中序数组里的索引
        head.left = f2(pre,L1+1,L1+find-L2,in,L2,find-1,map);
        head.right = f2(pre,L1+find-L2+1,R1,in,find+1,R2,map);
        return head;
    }
}

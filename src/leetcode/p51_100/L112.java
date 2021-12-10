package leetcode.p51_100;

import java.util.ArrayList;

/**
 * @author: jzh  https://leetcode-cn.com/problems/path-sum/
 * @date: created in 2021/12/2
 * @description:
 * @version: 1.0
 */
public class L112 {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return sumIt(root,0,targetSum);
    }

    public boolean isSum = false;

    public boolean sumIt(TreeNode node,int now,int target){
        if(isSum){
            return isSum;
        }
        if(node != null){
            now += node.val;
            if(node.left==null&&node.right==null){
                isSum = (now == target);
                return isSum;
            }
            sumIt(node.left,now,target);
            sumIt(node.right,now,target);
        }

        return isSum;


    }

}

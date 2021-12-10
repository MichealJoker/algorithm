package leetcode.p51_100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: jzh  https://leetcode-cn.com/problems/path-sum-ii/
 * @date: created in 2021/12/2
 * @description:
 * @version: 1.0
 */
public class L113 {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }
        sumIt(root,0,targetSum,new ArrayList<Integer>(),ans);
        return ans;
    }



    public void sumIt(TreeNode root,int now,int targetSum,List<Integer>level , List<List<Integer>> ans){
        if(root.left==null&&root.right == null){
            if(now + root.val == targetSum){
                level.add(root.val);

                ans.add(level);
            }
            return;
        }
        now += root.val;
        level.add(root.val);
        if(root.left!=null){
            sumIt(root.left,now,targetSum,new ArrayList(level),ans);
        }
        if(root.right!=null){
            sumIt(root.right,now,targetSum,new ArrayList(level),ans);
        }

    }


    /**
     * 更快，更省空间
     * @param root
     * @param now
     * @param targetSum
     * @param level
     * @param ans
     */
    public void sumIt2(TreeNode root,int now,int targetSum,List<Integer>level , List<List<Integer>> ans){
        if(root.left==null&&root.right == null){
            if(now + root.val == targetSum){
                level.add(root.val);
                ans.add(new ArrayList(level));
                level.remove(level.size()-1);
            }
            return;
        }
        now += root.val;
        level.add(root.val);
        if(root.left!=null){
            sumIt(root.left,now,targetSum,level,ans);
        }
        if(root.right!=null){
            sumIt(root.right,now,targetSum,level,ans);
        }
        level.remove(level.size()-1);
    }
}

package tixi.p17recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: jzh
 * @date: created in 2022/2/11
 * @description:
 * @version: 1.0
 */
public class Code04_PrintAllPermutations {

    //打印字符串的全排列
    // 给你n个字母，n个字母全要，但是顺序不同

    public static List<String> permutation(String s){
        List<String> ans = new ArrayList<>();
        if(s == null || s.length() ==0) {
            return ans;
        }
        char[] str = s.toCharArray();
        List<Character> rest = new ArrayList<>();
        for(char cha:str){
            rest.add(cha);
        }
        String path = "";
        f(rest,path,ans);
        return ans;
    }

    public static void f(List<Character> rest,String path,List<String> ans){
        if(rest.isEmpty()){
            ans.add(path);
        }else{
            int size = rest.size();
            for(int i=0;i<size;i++){
                Character cur = rest.get(i);
                rest.remove(i);
                f(rest,path+cur,ans);
                rest.add(i,cur);//走完支路递归后恢复现场
            }
        }
    }

    public static void main(String[] args) {
//        List<String> abcd = permutation("abc");
//        for(int i=0;i<abcd.size();i++){
//            System.out.println(abcd.get(i));
//        }
//        List<String> abcd2 = permutation2("acc");
//        for(int i=0;i<abcd2.size();i++){
//            System.out.println(abcd2.get(i));
//        }

        List<String> abcd3 = permutation3("acc");
        for(int i=0;i<abcd3.size();i++){
            System.out.println(abcd3.get(i));
        }
    }

    //这个更好
    public static List<String> permutation2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g(str,0,ans);
        return ans;
    }

    public static void g(char[] str, int index,List<String> ans){
        if(index == str.length){
            ans.add(String.valueOf(str));
        }else{
            for(int i = index;i<str.length;i++){
                swap(str,index,i);
                g(str,index+1,ans);//把这递归想象成二叉树中序遍历
                swap(str,index,i);//恢复现场
            }
        }
    }

    public static void swap(char[] chs,int i,int j){
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    public static List<String> permutation3(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g2(str, 0, ans);
        return ans;
    }

    public static void g2(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            boolean[] visited = new boolean[256];//这个去重效率比 set高，因为减支>过滤
            for (int i = index; i < str.length; i++) {
                if (!visited[str[i]]) {
                    visited[str[i]] = true;
                    swap(str, index, i);
                    g2(str, index + 1, ans);
                    swap(str, index, i);
                }
            }
        }
          //     触发
//             /    \     \
        // acc      cac     墙
//         /  \     /  \
//        acc 墙   cac cca



    }
}

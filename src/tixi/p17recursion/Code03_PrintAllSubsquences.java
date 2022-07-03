package tixi.p17recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author: jzh
 * @date: created in 2022/2/11
 * @description:
 * @version: 1.0
 */
public class Code03_PrintAllSubsquences {


    //打印一个字符串的全部子序列

    public static List<String> subs(String s){
        char[] str = s.toCharArray();
        List<String> list = new ArrayList<>();
        process1(str,0,list,"");
        return list;
    }


    //之前的决定都在path上，可能是要，也可能是不要
    // 其实就是二叉树，每次都是对于一个节点要和不要
    public static void process1(char[]str,int index,List<String> list,String path){
        if (index == str.length) {
            list.add(path);
            return;
        }
        String no = path;
        process1(str,index+1,list,no);
        String yes = path + str[index];
        process1(str,index+1,list,yes);

    }

    public static void main(String[] args) {
        String test = "acccc";
        List<String> subs = subs(test);
        subs.forEach(item->{
            System.out.println(item);
        });
    }


    //打印一个字符串的全部子序列，要求不要出现重复字面值的子序列

    public static List<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        HashSet<String> set = new HashSet<>();
        process2(str, 0, set, "");
        List<String> ans = new ArrayList<>();
        for (String cur : set) {
            ans.add(cur);
        }
        return ans;
    }

    public static void process2(char[] str, int index, HashSet<String> set, String path) {
        if (index == str.length) {
            set.add(path);
            return;
        }
        String no = path;
        process2(str, index + 1, set, no);
        String yes = path + String.valueOf(str[index]);
        process2(str, index + 1, set, yes);
    }
}

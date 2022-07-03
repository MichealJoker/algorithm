package tixi.p17recursion;

import javax.sound.midi.Soundbank;

/**
 * @author: jzh
 * @date: created in 2022/2/10
 * @description:
 * @version: 1.0
 */
public class Code02_Hanoi {

    // |   |    |
    // 左  中   右
    //N层汉诺塔 ，一个圆盘原始按 从上到下， 从小到大排列，都在左边那个柱子上
    //要求排成 从上到下，从小到大排列，移到右那根柱子上，
    // 每次只能动一个盘，上面的盘不拿走，下面的盘不能动


    //第一步，除了最后一个盘，全移到中
    //第二部，最后一个盘去右
    //第三部，中间盘去右

    //非常暴力，缺啥写啥，baseCase写对了，剩下的就是相互依赖
    public static void hanoi1(int n) {
        leftToRight(n);
    }

    // 请把1~N层圆盘 从左 -> 右
    public static void leftToRight(int n) {
        if (n == 1) { // base case
            System.out.println("Move 1 from left to right");
            return;
        }
        leftToMid(n - 1);
        System.out.println("Move " + n + " from left to right");
        midToRight(n - 1);
    }

    // 请把1~N层圆盘 从左 -> 中
    public static void leftToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to mid");
            return;
        }
        leftToRight(n - 1);
        System.out.println("Move " + n + " from left to mid");
        rightToMid(n - 1);
    }

    public static void rightToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("Move " + n + " from right to mid");
        leftToMid(n - 1);
    }

    public static void midToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to right");
            return;
        }
        midToLeft(n - 1);
        System.out.println("Move " + n + " from mid to right");
        leftToRight(n - 1);
    }

    public static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to left");
            return;
        }
        midToRight(n - 1);
        System.out.println("Move " + n + " from mid to left");
        rightToLeft(n - 1);
    }

    public static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to left");
            return;
        }
        rightToMid(n - 1);
        System.out.println("Move " + n + " from right to left");
        midToLeft(n - 1);
    }

    public static void hanoi2(int n) {
        if (n > 0) {
            process(n, "left", "right", "mid");
        }
    }
    //6合1
    public static void func(int N,String from,String to,String other){
        if(N==1){  //第一个直接从左扔右
            System.out.println("move 1 from "+from + " to " + to);
        }else {
            func(N-1,from,other,to); //1到 n-1 从左扔中
            System.out.println("Move " + N + " from " + from + " to " + to);
            func(N - 1, other, to, from);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        hanoi1(n);
        System.out.println("============");
        hanoi2(n);
////		System.out.println("============");
//		hanoi3(n);
    }








    //  1 2 3
    public static void process(int N,String from,String to,String other){
        if(N==1){
            System.out.println("move 1 from " + from + " to "+ to);
        } else {
            process(N - 1, from, other, to);// 先执行 把左全塞中
            System.out.println("move " + N + " from " + from + " to " + to);
            process(N - 1, other, to, from); //后执行 把中全塞右
        }
    }

    // 2 l m r
    // sout  2 l m
    // sout
}

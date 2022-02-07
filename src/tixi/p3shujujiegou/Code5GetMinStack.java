package tixi.p3shujujiegou;

import java.util.Stack;

/**
 * @description: 能得到最小元素的栈
 * @author: 姜志豪
 * @date: 2021/12/17-10:36
 * @Version: 1.0.0
 */
public class Code5GetMinStack {


    //一个数据栈  一个最小栈 ，同步压，同步弹出
    //    5         2
    //    2         2
    //    3         3
    public static class MyStack {
        Stack<Integer> logStack;
        Stack<Integer> minStack;

        public MyStack() {
            logStack = new Stack<>();
            minStack = new Stack<>();
        }


        public Integer getMin() {
            return minStack.peek() != null ? minStack.peek() : null;
        }

        public void push(int num) {
            if (logStack.isEmpty()) {
                minStack.push(num);
            } else if (num < getMin()) {
                minStack.push(num);
            } else {
                minStack.push(getMin());
            }
            logStack.push(num);
        }

        public Integer pop() {
            minStack.pop();
            return logStack.pop();
        }
    }

    public static void main(String[] args) {
        // MyStack1 stack1 = new MyStack1();
        // stack1.push(3);
        // System.out.println(stack1.getmin());
        // stack1.push(4);
        // System.out.println(stack1.getmin());
        // stack1.push(1);
        // System.out.println(stack1.getmin());
        // System.out.println(stack1.pop());
        // System.out.println(stack1.getmin());

        System.out.println("=============");

        MyStack stack2 = new MyStack();
        stack2.push(3);
        System.out.println(stack2.getMin());
        stack2.push(4);
        System.out.println(stack2.getMin());
        stack2.push(1);
        System.out.println(stack2.getMin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getMin());
    }
}
package tixi.p3shujujiegou;

import java.util.Stack;

/**
 * @description: 两个栈来实现队列
 * @author: 姜志豪
 * @date: 2021/12/17-11:18
 * @Version: 1.0.0
 */
public class Code06_TwoStacksImplementQueue {

    //逻辑为
    //2个栈 1个负责接收，1个负责出数据
    //当且仅当 出栈 为空时，把 接受栈 的数据导进 出栈 ，即可保证先进先出

    public static class TwoStacksQueue<T> {
        public Stack<T> stackPush;
        public Stack<T> stackPop;

        public TwoStacksQueue() {
            stackPush = new Stack<T>();
            stackPop = new Stack<T>();
        }

        private void pushToPop() {
            if (stackPop.empty()) {
                while (!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void push(T num) {
            stackPush.push(num);
            pushToPop();
        }


        public T poll() {
            if (stackPop.isEmpty() && stackPush.isEmpty()) {
                throw new RuntimeException("队列空");
            }
            pushToPop();
            return stackPop.pop();
        }

        public T peek(){
            if (stackPop.isEmpty() && stackPush.isEmpty()) {
                throw new RuntimeException("队列空");
            }
            pushToPop();
            return stackPop.peek();
        }
    }

    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.push(1);
        test.push(2);
        test.push(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}
package tixi.p17recursion;

import java.util.Stack;

//给你一个栈，请你逆序这个栈，
//不能申请额外的数据结构，
//只能使用递归函数。 如何实现?
public class Code05_ReverseStackUsingRecursive {

	//无限取出栈顶然后递归，最后一次递归开始压栈，然后逆处理
	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int i = f(stack);  //   i = f1  = 3
		reverse(stack);    //   i = f2  = 2
		stack.push(i);     //   i = f3  = 1  push 1 ,push 2,push 3
	}

	// 栈底元素移除掉
	// 上面的元素盖下来
	// 返回移除掉的栈底元素
	public static int f(Stack<Integer> stack) {
		int result = stack.pop(); // f1 : result 1   last = f2   =3  push 1
		if (stack.isEmpty()) {   //  f2 : result 2   last = f3   =3  push 2
			return result;       //  f3 : result 3
		} else {
			int last = f(stack);
			stack.push(result);
			return last;
		}
	}

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}

}

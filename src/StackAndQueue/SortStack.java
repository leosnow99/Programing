package StackAndQueue;

import java.util.Iterator;
import java.util.Stack;

public class SortStack {
	public static void sortStackByStack(Stack<Integer> stack) {
		Stack<Integer> help = new Stack<>();
		while (!stack.isEmpty()) {
			Integer cur = stack.pop();
			while (!help.isEmpty() && help.peek() < cur) {
				stack.push(help.pop());
			}
			help.push(cur);
		}
		while (!help.isEmpty()) {
			stack.push(help.pop());
		}
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(9);
		stack.push(3);
		stack.push(5);
		stack.push(7);
		sortStackByStack(stack);
		for (Integer integer : stack) {
			System.out.println(integer);
		}
	}
}

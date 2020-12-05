package StackAndQueue;

import java.util.Stack;

/**
 * 接雨水
 *
 * @author LEOSNOW
 * @leetcode 42
 */
public class AfterRain {
	/**
	 * 单调栈解法
	 */
	public int trap(int[] height) {
		if (height == null || height.length < 3) {
			return 0;
		}
		Stack<Integer> stack = new Stack<>();
		int result = 0;
		for (int i = 0; i < height.length; i++) {
			while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
				int curIndex = stack.pop();
				while (!stack.isEmpty() && height[stack.peek()] == height[curIndex]) {
					stack.pop();
				}
				if (!stack.isEmpty()) {
					int stackTop = stack.peek();
					result += (Math.min(height[stackTop], height[i]) - height[curIndex]) * (i - stackTop - 1);
				}
			}
			stack.push(i);
		}
		return result;
	}
	
	/**
	 * 双指针解法
	 */
	public int trapByTwoArrow(int[] height) {
		if (height == null || height.length < 3) {
			return 0;
		}
		int leftIndex = 0;
		int rightIndex = height.length - 1;
		int leftMax = 0;
		int rightMax = 0;
		int result = 0;
		while (leftIndex < rightIndex) {
			if (height[leftIndex] < height[rightIndex]) {
				if (height[leftIndex] > leftMax) {
					leftMax = height[leftIndex];
				} else {
					result += leftMax - height[leftIndex];
				}
				++leftIndex;
			} else {
				if (height[rightIndex] > rightMax) {
					rightMax = height[rightIndex];
				} else {
					result += rightMax - height[rightIndex];
				}
				--rightIndex;
			}
		}
		return result;
	}
}

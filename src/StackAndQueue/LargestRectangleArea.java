package StackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author LEOSNOW
 */
public class LargestRectangleArea {
	public int largestRectangleArea(int[] heights) {
		int len = heights.length;
		if (len == 0) {
			return 0;
		}
		if (len == 1) {
			return heights[0];
		}
		
		int result = 0;
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < len; i++) {
			while (!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
				final Integer index = stack.pop();
				int lastIndex = stack.isEmpty() ? -1 : stack.peek();
				result = Math.max(result, (i - lastIndex - 1) * heights[index]);
			}
			stack.push(i);
		}
		
		while (!stack.isEmpty()) {
			final Integer index = stack.pop();
			int lastIndex = stack.isEmpty() ? -1 : stack.peek();
			result = Math.max(result, (len - lastIndex - 1) * heights[index]);
		}
		return result;
	}
}

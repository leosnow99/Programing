package other;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 表达式顺序正确性校验
 * @author LEOSNOW
 */
public class ValidExpression {
	public static boolean isValid(String expression) {
		final char[] chars = expression.toCharArray();
		Deque<Character> stack = new ArrayDeque<>();
		for (char aChar : chars) {
			if (aChar == '{' || aChar == '[' || aChar == '(') {
				stack.push(aChar);
			}
			if (aChar == '}' || aChar == ']' || aChar == ')') {
				char match = aChar == '}' ? '}' : aChar == ']' ? ']' : ')';
				if (stack.isEmpty() || match != stack.pop()) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
}

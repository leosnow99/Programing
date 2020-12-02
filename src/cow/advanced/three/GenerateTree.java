package cow.advanced.three;

import tree.Node;

/**
 * 给定一个有序数组, 转化为树
 *
 * @author LEOSNOW
 */
public class GenerateTree {
	public static Node generateTree(int[] sortedArr) {
		if (sortedArr == null || sortedArr.length < 1) {
			return null;
		}
		return generate(sortedArr, 0, sortedArr.length - 1);
	}
	
	public static Node generate(int[] sortedArr, int left, int right) {
		if (left > right) {
			return null;
		}
		int mid = (left + right) / 2;
		final Node head = new Node(sortedArr[mid]);
		head.left = generate(sortedArr, left, mid - 1);
		head.right = generate(sortedArr, mid + 1, right);
		
		return head;
	}
}

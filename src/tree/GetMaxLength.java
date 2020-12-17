package tree;

import java.util.HashMap;

/**
 * 给定一棵二叉树的头节点 head 和一个 32 位整数 sum，二叉树节点值类型为整型，求累加 和为 sum 的最长路径长度。
 * 路径是指从某个节点往下，每次最多选择一个孩子节点或者不选所 形成的节点链。
 *
 * @author leosnow
 */
public class GetMaxLength {
	public static int getMaxLength(Node head, int target) {
		//map中保证到当前sum最短路径
		final HashMap<Integer, Integer> sumMap = new HashMap<>(target);
		sumMap.put(0, 0);
		return preOrder(head, target, 0, 1, 0, sumMap);
	}
	
	private static int preOrder(Node head, int target, int preSum, int level, int maxLen, HashMap<Integer, Integer> map) {
		if (head == null) {
			return maxLen;
		}
		int curSum = preSum + head.value;
		if (!map.containsKey(curSum)) {
			map.put(curSum, level);
		}
		if (map.containsKey(curSum - target)) {
			maxLen = Math.max(maxLen, level - map.get(curSum - target));
		}
		maxLen = preOrder(head.left, target, curSum, level + 1, maxLen, map);
		maxLen = preOrder(head.right, target, curSum, level + 1, maxLen, map);
		//防止左右树误判
		if (level == map.get(curSum)) {
			map.remove(curSum);
		}
		return maxLen;
	}
}

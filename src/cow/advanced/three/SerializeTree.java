package cow.advanced.three;

import tree.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LEOSNOW
 */
public class SerializeTree {
	public static String serialByPre(Node head) {
		if (head == null) {
			return "#_";
		}
		String res = head.value + "_";
		res += serialByPre(head.left);
		res += serialByPre(head.right);
		
		return res;
	}
	
	public static Node reconByPreSerial(String serialStr) {
		final String[] values = serialStr.split("_");
		final LinkedList<String> queue = new LinkedList<>();
		for (String value : values) {
			queue.offer(value);
		}
		return reconPreOrder(queue);
	}
	
	public static Node reconPreOrder(Queue<String> queue) {
		if (queue.isEmpty()) {
			return null;
		}
		final String value = queue.poll();
		if ("#".equals(value)) {
			return null;
		}
		final Node head = new Node(Integer.parseInt(value));
		head.left = reconPreOrder(queue);
		head.right = reconPreOrder(queue);
		
		return head;
	}

	//层序序列化
	public static String serialByLevel(Node head) {
		if (head == null) {
			return "#_";
		}
		StringBuilder res = new StringBuilder(head.value + "_");
		final Queue<Node> queue = new LinkedList<>();
		while (!queue.isEmpty()) {
			head = queue.poll();
			if (head.left != null) {
				res.append(head.left.value).append("_");
				queue.offer(head.left);
			} else {
				res.append("#_");
			}
			if (head.right != null) {
				res.append(head.right.value).append("_");
				queue.offer(head.right);
			} else {
				res.append("#_");
			}
		}
		return res.toString();
	}

	public static Node reconByLevelSerial(String serialStr) {
		if (serialStr == null || "".equals(serialStr)) {
			return null;
		}
		final String[] values = serialStr.split("_");
		int index = 0;
		Node head = generateNodeByString(values[index++]);
		Queue<Node> queue = new LinkedList<>();
		if (head != null) {
			queue.offer(head);
		}

		while (!queue.isEmpty()) {
			head = queue.poll();
			head.left = generateNodeByString(values[index++]);
			head.right = generateNodeByString(values[index++]);
			if (head.left != null) {
				queue.offer(head.left);
			}
			if (head.right != null) {
				queue.offer(head.right);
			}
		}
		return head;
	}

	public static Node generateNodeByString(String value) {
		if (value == null || "#".equals(value)) {
			return null;
		}
		return new Node(Integer.parseInt(value));
	}
}

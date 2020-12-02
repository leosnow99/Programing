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

}

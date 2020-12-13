package linked;

/**
 * @author LEOSNOW
 */
public class ReverseOrderLinked {
	
	public static void reverse(MyLinkedList list) {
		MyLinkedList.Node newHead = null;
		MyLinkedList.Node current = list.getHead();
		while (current != null) {
			MyLinkedList.Node next = current.next;
			current.next = newHead;
			newHead = current;
			current = next;
		}
		list.setHead(newHead);
	}
	
	//递归解法
	public static void reverseRecursion(MyLinkedList list) {
		final MyLinkedList.Node recursion = recursion(list.getHead());
		list.setHead(recursion);
	}
	
	private static MyLinkedList.Node recursion(MyLinkedList.Node header) {
		if (header == null || header.next == null) {
			return header;
		}
		final MyLinkedList.Node reverseHead = recursion(header.next);
		header.next.next = header;
		header.next = null;
		return reverseHead;
	}
}

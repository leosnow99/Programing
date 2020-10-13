package linked;

public class MyLinkedList {
	public static class Node {
		int value;
		Node next;
		
		public Node(int value, Node node) {
			this.value = value;
			this.next = node;
		}
		
		public Node(int value) {
			this.value = value;
			this.next = null;
		}
	}
	
	private Node head;
	
	private Node last;
	
	private int size;
	
	public Node getHead() {
		return head;
	}
	
	public void setHead(Node head) {
		this.head = head;
	}
	
	public MyLinkedList(int value) {
		this.head = new Node(value, null);
		this.last = this.head;
		this.size = 1;
	}
	
	public MyLinkedList() {
		this.size = 0;
	}
	
	//头插法插入结点
	public void listAddNodeHead(int value) {
		final Node node = new Node(value);
		this.size++;
		if (last == null) {
			this.last = node;
		}
		node.next = head;
		head = node;
	}
	
	//尾插法插入节点
	public void listAddNodeTail(int value) {
		final Node node = new Node(value);
		if (last == null) {
			this.head = node;
			this.last = node;
			return;
		}
		last.next = node;
		last = node;
		this.size += 1;
	}
	
	//从链表删除值为value的结点
	public void listDelNode(int value) {
		Node node = head;
		if (node.value == value) {
			head = node.next;
			this.size -= 1;
		}
		while (node.next != null) {
			if (node.next.value == value) {
				node.next = node.next.next;
				this.size -= 1;
			}
			node = node.next;
		}
	}
	
	//链表遍历
	public void string() {
		if (head == null) {
			System.out.println("空链表!");
		}
		Node current = head;
		StringBuilder stringBuilder = new StringBuilder();
		while (current != null) {
			stringBuilder.append(current.value).append(" -> ");
			current = current.next;
		}
		stringBuilder.replace(stringBuilder.lastIndexOf(" -> "), stringBuilder.length(), "");
		System.out.println(stringBuilder.toString());
	}
	
	//使用数组初始化一个链表，共len个元素
	public static MyLinkedList initLinked(int[] values) {
		
		final MyLinkedList myLinkedList = new MyLinkedList();
		if (values == null || values.length == 0) {
			return myLinkedList;
		}
		for (int value : values) {
			myLinkedList.listAddNodeTail(value);
		}
		return myLinkedList;
	}
	
	//链表长度函数
	public int length() {
		return this.size;
	}
	
	public static void main(String[] args) {
		final MyLinkedList list = MyLinkedList.initLinked(new int[]{1, 2, 3, 45, 1, 3});
		list.listAddNodeHead(1);
//		ReverseOrderLinked.reverse(list);
		ReverseOrderLinked.reverseRecursion(list);
		list.string();
		
	}
}

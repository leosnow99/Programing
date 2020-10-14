package linked;

public class MyLinkedList {
	public static class Node {
		int value;
		Node next;
		
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
	
	//链表拷贝
	public static MyLinkedList listCopy(MyLinkedList list) {
		final MyLinkedList newList = new MyLinkedList();
		Node current = list.head;
		while (current != null) {
			newList.listAddNodeTail(current.value);
			current = current.next;
		}
		return newList;
	}
	
	//链表合并
	public static MyLinkedList listMerge(MyLinkedList firstList, MyLinkedList secondList) {
		final MyLinkedList resultList = new MyLinkedList();
		
		if (firstList == null) {
			return secondList;
		} else if (secondList == null) {
			return firstList;
		}
		
		Node firstHead = firstList.head;
		Node secondHead = secondList.head;
		
		while (firstHead != null && secondHead != null) {
			if (firstHead.value < secondHead.value) {
				resultList.listAddNodeTail(firstHead.value);
				firstHead = firstHead.next;
			} else {
				resultList.listAddNodeTail(secondHead.value);
				secondHead = secondHead.next;
			}
		}
		
		if (firstHead != null) {
			resultList.last.next = firstHead;
			resultList.last = firstList.last;
		} else {
			resultList.last.next = secondHead;
			resultList.last = secondList.last;
		}
		resultList.size = firstList.size + secondList.size;
		
		return resultList;
	}
	
	//链表相交判断，如果相交返回相交的结点，否则返回NULL。
	public static Node listIntersect(MyLinkedList firstList, MyLinkedList secondList) {
		final int firstLen = firstList.length();
		final int secondLen = secondList.length();
		final int delta = Math.abs(firstLen - secondLen);
		
		Node longList = firstList.getHead();
		Node shortList = secondList.getHead();
		
		if (firstLen < secondLen) {
			longList = secondList.getHead();
			shortList = firstList.getHead();
		}
		
		for (int i = 0; i < delta; i++) {
			longList = longList.next;
		}
		
		while (longList != null && shortList != null) {
			if (longList == shortList) {
				return longList;
			}
			longList = longList.next;
			shortList = shortList.next;
		}
		
		return null;
	}
	
	//检测链表是否有环-Floyd判圈算法
	// 若存在环，返回相遇结点，否则返回NULL
	public Node listDetectLoop() {
		Node fast = head;
		Node slow = head;
		
		while (slow != null && fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				System.out.println("Found Loop");
				return slow;
			}
		}
		return null;
	}
	
	//查找链表中环入口
	public Node findLoopNode() {
		Node meetNode = listDetectLoop();
		if (meetNode == null) {
			return null;
		}
		
		Node current = head;
		while (current != meetNode) {
			current = current.next;
			meetNode = meetNode.next;
		}
		return meetNode;
	}
	
	//链表模拟加法
	public MyLinkedList listEnumerateAdd(MyLinkedList firstList, MyLinkedList secondList) {
		int carry = 0;
		final MyLinkedList result = new MyLinkedList();
		head = new Node(0);
		Node firstNode = firstList.getHead();
		Node secondNode = secondList.getHead();
		
		while (firstNode != null || secondNode != null || head != null) {
			int value = carry;
			if (firstNode != null) {
				value += firstNode.value;
				firstNode = firstNode.next;
			}
			
			if (secondNode != null) {
				value += secondNode.value;
				secondNode = secondNode.next;
			}
			
			result.listAddNodeTail(value % 10);
			carry = (value >= 10 ? 1 : 0);
		}
		return result;
	}
	
	/***
	 * prev->value ≤ value ≤ current->value：
	 * 插入到prev和current之间。
	 *
	 *  value为最大值或者最小值：
	 * 插入到首尾交接处，如果是最小值重新设置head值。
	 * @param list 链表
	 */
	public void sortedLoopListAddNode(MyLinkedList list, int value) {
		final Node node = new Node(value);
		Node prev = list.getHead();
		Node current = prev.next;
		
		while (current != list.getHead()) {
			if (prev.value <= value && current.value >= value) {
				break;
			}
			current = current.next;
			prev = prev.next;
		}
		prev.next = node;
		node.next = current;
		
		if (current == head && value <= current.value) {
			head = node;
		}
	}
	
	//输出链表倒数第K个结点
	//先p1和p2都指向head，然后p2向前走k步，这样p1和p2之间就间隔k个节点。最后p1和p2同时向前移动，p2走到链表末尾的时候p1刚好指向倒数第K个结点。
	public Node getLastKthNode(int k) {
		Node fast = head;
		Node last = head;
		for (; k > 0; k--) {
			if (fast == null) {
				return null;
			}
			fast = fast.next;
		}
		while (fast != null) {
			fast = fast.next;
			last = last.next;
		}
		return last;
	}
	
	
	public static void main(String[] args) {
/*
        final MyLinkedList list = MyLinkedList.initLinked(new int[]{1, 2, 3, 4, 11, 13, 17, 19, 1010});
        Node tem = new Node(13);
        list.last.next = tem;
        list.last = tem;
        list.size += 1;
        list.listAddNodeTail(1999);

        final MyLinkedList newList = MyLinkedList.initLinked(new int[]{2, 3, 12, 34});
        newList.last.next = tem;
        newList.last = tem;
        newList.size += 2;

        list.string();
        newList.string();
        System.out.println(MyLinkedList.listIntersect(list, newList).value);
*/
//		final MyLinkedList list = MyLinkedList.initLinked(new int[]{1, 2, 3, 4});
//		list.head.next.next.next.next = list.head.next;
//		System.out.println(list.findLoopNode().value);

//		final MyLinkedList list = new MyLinkedList();
//		final Node node1 = new Node(3);
//		final Node node2 = new Node(5);
//		final Node node3 = new Node(7);
//		list.listAddNodeHead(2);
//		list.head.next = node1;
//		list.head.next.next = node2;
//		list.head.next.next.next = node3;
//		node3.next = list.head;
//
//		list.sortedLoopListAddNode(list, 4);
//		System.out.println(list.head.next.next.value);
		
		final MyLinkedList list = MyLinkedList.initLinked(new int[]{1, 2, 3, 4, 5, 6, 7});
		System.out.println(list.getLastKthNode(7).value);
	}
}

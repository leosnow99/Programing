package other;

import java.util.HashMap;

/**
 * 设计 LRU 缓存结构，该结构在构造时确定大小，假设大小为 K，并有如下两个功能。
 *  set(key,value)：将记录(key,value)插入该结构。
 *  get(key)：返回 key 对应的 value 值
 * <p>
 * 这种缓存结构可以由双端队列与哈希表相结合的方式实现。首先实现一个基本的双向链表
 *
 * @author LEOSNOW
 */
public class LRUDemo {
	
	/**
	 * 节点结构
	 *
	 * @param <V> 节点存放值类型
	 */
	public static class Node<V> {
		public V value;
		public Node<V> last;
		public Node<V> next;
		
		public Node(V value) {
			this.value = value;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Node) {
				return this.value.equals(((Node<?>) obj).value);
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return value.hashCode();
		}
	}
	
	/**
	 * 双端链表
	 *
	 * @param <V> 队列存储值类型
	 */
	public static class NodeDoubleLinkedList<V> {
		private Node<V> head;
		private Node<V> tail;
		
		public NodeDoubleLinkedList() {
			this.head = null;
			this.tail = null;
		}
		
		public void addNode(Node<V> newNode) {
			if (newNode == null) {
				return;
			}
			if (this.head == null) {
				this.head = newNode;
			} else {
				this.tail.next = newNode;
				newNode.last = this.tail;
			}
			this.tail = newNode;
		}
		
		public void moveNodeToTail(Node<V> node) {
			if (this.tail == node) {
				return;
			}
			if (this.head == node) {
				this.head = node.next;
				this.head.last = null;
			} else {
				node.last.next = node.next;
				node.next.last = node.last;
			}
			node.last = this.tail;
			node.next = null;
			this.tail.next = node;
			this.tail = node;
		}
		
		public Node<V> removeHead() {
			if (this.head == null) {
				return null;
			}
			Node<V> res = this.head;
			if (this.head == this.tail) {
				this.head = null;
				this.tail = null;
			} else {
				this.head = res.next;
				this.head.last = null;
				res.next = null;
			}
			return res;
		}
		
	}
	
	/**
	 * LRU 缓存接哦古实现
	 * 使用HashMap + 双端队列使用
	 *
	 * @param <K> key类型
	 * @param <V> 值类型
	 */
	public static class MyCache<K, V> {
		private final HashMap<K, Node<V>> keyNodeMap;
		private final HashMap<Node<V>, K> nodeKeyMap;
		
		private final NodeDoubleLinkedList<V> nodeList;
		private final int capacity;
		
		public MyCache(int capacity) {
			if (capacity < 1) {
				throw new RuntimeException("should be more than 0");
			}
			this.keyNodeMap = new HashMap<>();
			this.nodeKeyMap = new HashMap<>();
			this.nodeList = new NodeDoubleLinkedList<>();
			this.capacity = capacity;
		}
		
		public V get(K key) {
			if (this.keyNodeMap.containsKey(key)) {
				final Node<V> node = this.keyNodeMap.get(key);
				this.nodeList.moveNodeToTail(node);
				return node.value;
			}
			return null;
		}
		
		public void set(K key, V value) {
			if (this.keyNodeMap.containsKey(key)) {
				final Node<V> node = this.keyNodeMap.get(key);
				node.value = value;
				this.nodeList.moveNodeToTail(node);
			} else {
				final Node<V> node = new Node<>(value);
				this.keyNodeMap.put(key, node);
				this.nodeKeyMap.put(node, key);
				this.nodeList.addNode(node);
				if (this.keyNodeMap.size() == this.capacity + 1) {
					this.removeMostUnusedCache();
				}
			}
		}
		
		private void removeMostUnusedCache() {
			final Node<V> removeNode = this.nodeList.removeHead();
			final K removeKey = this.nodeKeyMap.get(removeNode);
			this.nodeKeyMap.remove(removeNode);
			this.keyNodeMap.remove(removeKey);
		}
	}
}

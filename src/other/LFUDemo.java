package other;


import java.util.HashMap;

/**
 * 一个缓存结构需要实现如下功能。
 *  void set(int key, int value)：加入或修改 key 对应的 value。
 *  int get(int key)：查询 key 对应的 value 值。
 * 但是缓存中最多放 K 条记录，如果新的第 K+1 条记录要加入，就需要根据策略删掉一条记
 * 录，然后才能把新记录加入。这个策略为：在缓存结构的 K 条记录中，哪一个 key 从进入缓存
 * 结构的时刻开始，被调用 set 或者 get 的次数最少，就删掉这个 key 的记录；如果调用次数最少
 * 的 key 有多个，上次调用发生最早的 key 被删除。
 *
 * @author LEOSNOW
 */
public class LFUDemo {
	/**
	 * 节点数据结构
	 */
	private static class Node {
		public Integer key;
		public Integer value;
		public Integer times; // 这个节点发生get和set的次数总和
		public Node up; // 节点之间是双向链表, 所以有上一个节点;
		public Node down; // 节点之间是双向链表, 所以有下一个节点;
		
		public Node(int key, int value, int times) {
			this.key = key;
			this.value = value;
			this.times = times;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Node) {
				return this.value.equals(((Node) obj).value) && this.key.equals(((Node) obj).value);
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return key.hashCode() & value.hashCode();
		}
	}
	
	/**
	 * 桶结构
	 */
	private static class NodeList {
		public Node head; // 桶的头节点
		public Node tail; // 桶的伟节点
		public NodeList last;
		public NodeList next;
		
		public NodeList(Node node) {
			head = node;
			tail = node;
		}
		
		/**
		 * 将一个新节点加入桶, 新的节点
		 *
		 * @param newHead 新加入的节点
		 */
		public void addNodeFromHead(Node newHead) {
			newHead.down = head;
			head.up = newHead;
			head = newHead;
		}
		
		/**
		 * 判断桶是否为空
		 *
		 * @return 是否为空
		 */
		public boolean isEmpty() {
			return head == null;
		}
		
		/**
		 * 删除node节点并保证node的上下文环境重新连接
		 *
		 * @param node 删除的节点
		 */
		public void deleteNode(Node node) {
			if (head == tail) {
				this.head = null;
				this.tail = null;
			} else {
				if (node == head) {
					head = node.down;
					head.up = null;
				} else if (node == tail) {
					tail = node.up;
					tail.down = null;
				} else {
					node.up.down = node.down;
					node.down.up = node.up;
				}
			}
			node.up = null;
			node.down = null;
		}
		
		
	}
	
	public static class LFUCache {
		private final int capacity; // 缓存的大小限制
		private int size; // 缓存的当前拥有的节点
		
		// 表示key(Integer) 由哪个节点代表
		private final HashMap<Integer, Node> records;
		
		// 表示节点在哪个桶中
		private final HashMap<Node, NodeList> heads;
		
		// 整个结构中位于最左的桶
		private NodeList headList;
		
		public LFUCache(int k) {
			this.capacity = k;
			this.size = 0;
			this.records = new HashMap<>();
			this.heads = new HashMap<>();
			this.headList = null;
		}
		
		/**
		 * 这个函数的功能是，判断刚刚减少了一个节点的桶是否已经为空
		 * <p>
		 * 1）如果不空，什么也不做
		 * <p>
		 * 2)如果空了，removeNodeList 还是整个缓存结构最左的桶(headList)。
		 * 删掉这个桶的同时也要让最左的桶变成 removeNodeList 的下一个。
		 * <p>
		 * 3)如果空了，removeNodeList 不是整个缓存结构最左的桶(headList)。
		 * 把这个桶删除，并保证上一个桶和下一个桶之间还是双向链表的连接方式
		 *
		 * @param removeNodeList 刚刚减少了一个节点的桶
		 * @return 刚刚减少了一个节点的桶是否已经为空，空则返回true；不空则返回false
		 */
		private boolean modifyHeadList(NodeList removeNodeList) {
			if (removeNodeList.isEmpty()) {
				if (headList == removeNodeList) {
					headList = removeNodeList.next;
					if (headList != null) {
						headList.last = null;
					}
				} else {
					removeNodeList.last.next = removeNodeList.next;
					if (removeNodeList.next != null) {
						removeNodeList.next.last = removeNodeList.last;
					}
				}
				return false;
			}
			return true;
		}
		
		/**
		 * 把 node 从 oldNodeList 删掉，然后放到次数+1 的桶中
		 * 整个过程既要保证桶之间仍然是双向链表，也要保证节点之间仍然是双向链表
		 *
		 * @param node        node 这个节点的次数+1
		 * @param oldNodeList 这个节点原来在 oldNodeList里。
		 */
		private void move(Node node, NodeList oldNodeList) {
			oldNodeList.deleteNode(node);
			
			// preList 表示次数+1 的桶的前一个桶是谁
			// 如果 oldNodeList 删掉 node 之后还有节点，oldNodeList 就是次数+1 的桶的前一个桶;
			// oldNodeList 就是次数+1 的桶的前一个桶;
			//所以次数+1 的桶的前一个桶是 oldNodeList 的前一个
			final NodeList preList = modifyHeadList(oldNodeList) ? oldNodeList.last : oldNodeList;
			
			// nextList 表示次数+1 的桶的后一个桶是谁
			final NodeList nextList = oldNodeList.next;
			if (nextList == null) {
				final NodeList newList = new NodeList(node);
				if (preList != null) {
					preList.next = nextList;
				}
				newList.last = preList;
				if (headList == null) {
					headList = newList;
				}
				heads.put(node, newList);
			} else {
				if (nextList.head.times.equals(node.times)) {
					nextList.addNodeFromHead(node);
					heads.put(node, nextList);
				} else {
					final NodeList newList = new NodeList(node);
					if (preList != null) {
						preList.next = newList;
					}
					newList.last = preList;
					newList.next = nextList;
					nextList.last = newList;
					if (headList == nextList) {
						headList = newList;
					}
					heads.put(node, newList);
				}
			}
		}
		
		public void set(int key, int value) {
			if (records.containsKey(key)) {
				final Node node = records.get(key);
				node.value = value;
				node.times++;
				final NodeList curNodeList = heads.get(node);
				move(node, curNodeList);
			} else {
				if (size == capacity) {
					final Node node = headList.tail;
					headList.deleteNode(node);
					modifyHeadList(headList);
					records.remove(key);
					heads.remove(node);
					size--;
				}
				Node node = new Node(key, value, 1);
				if (headList == null) {
					headList = new NodeList(node);
				} else {
					if (headList.head.times.equals(node.times)) {
						headList.addNodeFromHead(node);
					} else {
						final NodeList newList = new NodeList(node);
						newList.next = headList;
						headList.last = newList;
						headList = newList;
					}
				}
				records.put(key, node);
				heads.put(node, headList);
				size++;
			}
		}
		
		public Integer get(int key) {
			if (!records.containsKey(key)) {
				return null;
			}
			final Node node = records.get(key);
			node.times++;
			final NodeList curNodeList = heads.get(node);
			move(node, curNodeList);
			return node.value;
		}
		
	}
}

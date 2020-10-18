package tree;

import java.util.LinkedList;
import java.util.Stack;

public class MyTree {
	public class Node {
		int value;
		Node left;
		Node right;
		
		public Node(int value) {
			this.value = value;
		}
		
		public Node() {
		}
	}
	
	private int size;
	
	private Node root;
	
	public MyTree() {
		this.size = 0;
		this.root = null;
	}
	
	public MyTree(int rootValue) {
		this.size = 1;
		this.root = new Node(rootValue);
	}
	
	//树中插入节点
	public void insert(int value) {
		root = insertImplRecursion(root, value);
	}
	
	//树中插入节点 非递归
	private void insertNode(int value) {
		Node current = root;
		Node parent = root;
		while (current != null) {
			parent = current;
			if (value < current.value) {
				current = current.right;
			} else {
				current = current.left;
			}
		}
		//找到空位置
		current = new Node(value);
		if (value < parent.value) {
			parent.left = current;
		} else {
			parent.right = current;
		}
		size++;
	}
	
	//删除节点
	public Node removeNode(int value) {
		Node node;
		
		if (root.value == value) {
			node = root;
			root = null;
			return node;
		}
		
		final Node parent = searchNodeParent(value);
		
		if (parent == null) {
			return null;
		}
		
		if (parent.left != null && parent.left.value == value) {
			node = parent.left;
		} else {
			node = parent.right;
		}
		if (node.left == null && node.right == null) {
			// 情况1：待删除结点是叶子结点
			if (node == root) {
				root = null;
			} else {
				if (parent.left == node) {
					parent.left = null;
				} else {
					parent.right = null;
				}
			}
		} else if (node.left != null && node.right != null) {
			// 情况2：待删除结点有两个子结点
			final Node maxNode = findMaxNode(node.left);
			removeNode(maxNode.value);
			node.value = maxNode.value;
		} else {
			// 情况3：待删除结点只有一个子结点
			//找到子节点
			Node child = (node.left == null) ? node.right : node.left;
			if (node == root) {
				root = child;
			}
			if (parent.right == node) {
				parent.right = child;
			} else {
				parent.left = child;
			}
		}
		size--;
		return node;
	}
	
	//查找最大节点
	public Node findMaxNode() {
		return findMaxNode(root);
	}
	
	private Node findMaxNode(Node node) {
		if (node.right == null) {
			return node;
		}
		return findMaxNode(node.right);
	}
	
	//查找最小节点
	public Node findMinNode() {
		return findMinNode(root);
	}
	
	public Node findMinNode(Node node) {
		if (node.left == null) {
			return node;
		}
		return findMinNode(node.left);
	}
	
	//返回二叉树节点数目
	public int getSize() {
		return size;
	}
	
	public int nodeLength() {
		return nodeLength(root);
	}
	
	public int nodeLength(Node node) {
		if (node == null) {
			return 0;
		}
		return nodeLength(node.left) + nodeLength(node.right) + 1;
	}
	
	//二叉树高度
	public int nodeHeight() {
		return nodeLength(root);
	}
	
	public int nodeHeight(Node node) {
		if (node == null) {
			return 0;
		}
		int leftHeight = nodeHeight(node.left);
		int rightHeight = nodeHeight(node.right);
		return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
	}
	
	//搜索节点
	public Node searchNode(int value, Node parent) {
		if (root == null) {
			return null;
		}
		
		Node current = root;
		while (current != null && current.value != value) {
			parent.value = current.value;
			parent.left = current.left;
			parent.right = current.right;
			if (value < current.value) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return current;
	}
	
	public Node searchNodeParent(int value) {
		if (root == null) {
			return null;
		}
		Node parent = root;
		
		while (parent != null) {
			if (parent.left != null && parent.left.value == value) {
				return parent;
			} else if (parent.left != null && parent.right.value == value) {
				return parent;
			}
			
			if (parent.value > value) {
				parent = parent.left;
			} else {
				parent = parent.right;
			}
		}
		return null;
	}
	
	//搜索节点 --递归实现
	public Node searchNodeRecursion(Node node, int value) {
		if (node == null) {
			return null;
		}
		if (node.value == value) {
			return node;
		} else if (value < node.value) {
			return searchNodeRecursion(node.left, value);
		} else {
			return searchNodeRecursion(node.right, value);
		}
		
		
	}
	
	//BST中插入值，递归方法
	private Node insertImplRecursion(Node node, int value) {
		if (node == null) {
			node = new Node(value);
			this.size++;
			return node;
		}
		
		if (node.value > value) {
			node.left = insertImplRecursion(node.left, value);
		} else {
			node.right = insertImplRecursion(node.right, value);
		}
		return node;
	}
	
	//二叉树遍历
	//递归
	//先序遍历
	public void preOrderRecursion() {
		preOrderRecursion(root);
	}
	
	private void preOrderRecursion(Node node) {
		if (node == null) {
			return;
		}
		System.out.print(node.value + " ");
		preOrderRecursion(node.left);
		preOrderRecursion(node.right);
	}
	
	//中序遍历
	public void inOrderRecursion() {
		inOrderRecursion(root);
	}
	
	private void inOrderRecursion(Node node) {
		if (node == null) {
			return;
		}
		inOrderRecursion(node.left);
		System.out.print(node.value + " ");
		inOrderRecursion(node.right);
	}
	
	//后续遍历
	public void afterOrderRecursion() {
		afterOrderRecursion(root);
	}
	
	public void afterOrderRecursion(Node node) {
		if (node == null) {
			return;
		}
		afterOrderRecursion(node.left);
		afterOrderRecursion(node.right);
		System.out.print(node.value);
	}
	
	//二叉树层序遍历
	public void levelOrder(Node root) {
		final int height = nodeHeight();
		for (int level = 1; level < height; level++) {
			levelOrderLevel(root, level);
		}
	}
	
	// 二叉树层序遍历辅助函数-打印第level层的结点
	public void levelOrderLevel(Node node, int level) {
		if (node == null) {
			return;
		}
		if (level == 1) {
			System.out.print(node.value + " ");
			return;
		}
		levelOrderLevel(node.left, level - 1);
		levelOrderLevel(node.right, level - 1);
	}
	
	//非递归遍历
	public void preOrderIter() {
		if (root == null) {
			return;
		}
		final Stack<Node> stack = new Stack<>();
		stack.push(root);
		
		while (!stack.isEmpty()) {
			final Node node = stack.pop();
			System.out.print(node.value + " ");
			
			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
		}
	}
	
	//非递归中序遍历
	public void inOrderIter() {
		if (root == null) {
			return;
		}
		final Stack<Node> stack = new Stack<>();
		Node current = root;
		
		while (current != null || !stack.isEmpty()) {
			if (current != null) {
				stack.push(current);
				current = current.left;
			} else {
				final Node node = stack.pop();
				System.out.print(node.value + " ");
				current = current.right;
			}
		}
	}
	
	//非递归后序遍历
	public void afterOrderIter() {
		if (root == null) {
			return;
		}
		final Stack<Node> stack = new Stack<>();
		final Stack<Node> outPut = new Stack<>();
		
		stack.push(root);
		Node node;
		while (!stack.isEmpty()) {
			node = stack.pop();
			outPut.push(node);
			
			if (node.left != null) {
				stack.push(node);
			}
			if (node.right != null) {
				stack.push(node);
			}
		}
		
		while (!outPut.isEmpty()) {
			node = outPut.pop();
			System.out.print(node.value + " ");
		}
	}
	
	//层序遍历-非递归
	public void levelOrderIter() {
		if (root == null) {
			return;
		}
		final LinkedList<Node> queue = new LinkedList<>();
		queue.addFirst(root);
		while (!queue.isEmpty()) {
			final Node node = queue.getFirst();
			if (node.left != null) {
				queue.addLast(node.left);
			}
			if (node.right != null) {
				queue.addLast(node.right);
			}
			System.out.print(node.value + " ");
		}
	}
	
	public static void main(String[] args) {
		final MyTree tree = new MyTree();
		tree.insert(15);
		tree.insert(8);
		tree.insert(10);
		tree.insert(12);
		tree.insert(20);
		tree.insert(18);
		tree.insert(25);
		tree.insert(16);
		tree.insert(30);
		tree.insert(19);
		
		tree.levelOrder(tree.root);
		System.out.println();
		final Node node = tree.removeNode(19);
		System.out.println(node.value);
		tree.levelOrder(tree.root);
		
	}
}

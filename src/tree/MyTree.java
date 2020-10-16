package tree;

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
        insertImplRecursion(root, value);
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
    }

    //删除节点
    public Node removeNode(int value) {
        final Node parent = new Node();
        final Node node = searchNode(value, parent);
        if (node == null) {
            return node;
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
        return node;
    }

    //搜索节点
    public Node searchNode(int value, Node parent) {
        if (root == null) {
            return null;
        }

        Node current = root;
        while (current != null && current.value == value) {
            parent = current;
            if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current;
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
}

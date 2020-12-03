package tree;

//在二叉树中找到一个节点的后继节点
public class GetNextNode {
    private static class Node {
        int value;
        Node parent;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public static Node getNextNode(Node node) {
        if (node == null) {
            return null;
        }

        //情况一：Node节点有右子树
        if (node.right != null) {
            return getMostLeftNode(node.right);
        }

        //情况二：如果 node 没有右子树，那么先看 node 是不是 node 父节点的左孩子节点，如果是 左孩子节点，那么此时 node 的父节点就是 node 的后继节点；
        //如果是右孩子节点，就向上寻找 node 的后继节点，假设向上移动到的节点记为 s，s 的父节点记为 p，如果发现 s 是 p 的左孩子 节点，
        //那么节点 p 就是 node 节点的后继节点，否则就一直向上移动
        while (node.parent != null && node.parent.left == node) {
            node = node.parent;
        }
        return node.parent;
    }

    public static Node getMostLeftNode(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}


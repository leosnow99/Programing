package tree;

import java.util.Stack;
//一棵二叉树原本是搜索二叉树，但是其中有两个节点调换了位置，使得这棵二叉树不再是 搜索二叉树，请找到这两个错误节点并返回。
//已知二叉树中所有节点的值都不一样，给定二叉 树的头节点 head，返回一个长度为 2 的二叉树节点类型的数组 errs，errs[0]表示一个错误节点，
//errs[1]表示另一个错误节点。
public class FindTwoErrorNode {

    //找到这两个错误节点。如果对所有的节点值都不一样的搜索二叉树进行中序遍 历，那么出现的节点值会一直升序。
    //因此，如果有两个节点位置错了，就一定会出现降序。
    //如果在中序遍历时节点值出现了两次降序，第一个错误的节点为第一次降序时较大的节点， 第二个错误的节点为第二次降序时较小的节点。
    //
    //寻找两个错误节点的过程可以总结为：第一个错误节点为第一次降序时较大的节点，第二 个错误节点为最后一次降序时较小的节点。
    public static Node[] getTwoErrorNode(Node head) {
        Node[] errors = new Node[2];
        if (head == null) {
            return errors;
        }
        Stack<Node> stack = new Stack<>();
        Node pre = null;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (pre != null && pre.value > head.value) {
                    errors[0] = errors[0] == null ? pre : errors[0];
                    errors[1] = head;
                }
                pre = head;
                head = head.left;
            }
        }
        return errors;
    }

    //进阶问题：如果在原问题中得到了这两个错误节点，我们当然可以通过交换两个节点的节 点值的方式让整棵二叉树重新成为搜索二叉树。
    //但现在要求你不能这么做，而是在结构上完全 交换两个节点的位置，请实现调整的函数。
    public static Node recoverTree(Node head) {
        Node[] errors = getTwoErrorNode(head);
        Node[] parents = getErrorParent(head, errors[0], errors[1]);

        Node e1 = errors[0];
        Node e1L = e1.left;
        Node e1R = e1.right;
        Node e1P = parents[0];

        Node e2 = errors[1];
        Node e2L = e2.left;
        Node e2R = e2.right;
        Node e2P = parents[1];

        return null;
    }

    public static Node[] getErrorParent(Node head, Node e1, Node e2) {
        Node[] parents = new Node[2];
        if (head == null) {
            return parents;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (head.left == e1 || head.right == e1) {
                    parents[1] = head;
                }
                if (head.left == e2 || head.right == e2) {
                    parents[2] = head;
                }
                head = head.right;
            }
        }
        return parents;
    }
}

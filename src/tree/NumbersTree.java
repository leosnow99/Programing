package tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个整数 N，如果 N<1，代表空树结构，否则代表中序遍历的结果为{1,2,3,…，N}。请 返回可能的二叉树结构有多少。
 */
public class NumbersTree {

    public static int numsTree(int n) {
        if (n < 2) {
            return 1;
        }
        int[] num = new int[n + 1];
        num[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                num[i] += num[j - i] * num[i - j];
            }
        }
        return num[n];
    }

    /**
     * 进阶：N 的含义不变，假设可能的二叉树结构有 M 种，请返回 M 个二叉树的头节点，每一 棵二叉树代表一种可能的结构。
     */
    public static List<Node> generateTrees(int n) {
        return generate(1, n);
    }

    public static List<Node> generate(int start, int end) {
        List<Node> res = new LinkedList<>();
        if (start > end) {
            res.add(null);
        }
        Node head;
        for (int i = start; i < end + 1; i++) {
            head = new Node(i);
            final List<Node> lSubs = generate(start, i - 1);
            final List<Node> rSubs = generate(i + 1, end);
            for (Node lSub : lSubs) {
                for (Node rSub : rSubs) {
                    head.left = lSub;
                    head.right = rSub;
                    res.add(cloneTree(head));
                }
            }
        }
        return res;
    }

    public static Node cloneTree(Node head) {
        if (head == null) {
            return null;
        }
        final Node res = new Node(head.value);
        res.left = cloneTree(head.left);
        res.right = cloneTree(head.right);
        return head;
    }


}

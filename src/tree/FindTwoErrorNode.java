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

    //问题一：e1 和 e2 是否有一个是头节点？如果有，谁是头节点？
    //问题二：e1 和 e2 是否相邻？如果相邻，谁是谁的父节点？
    //问题三：e1 和 e2 分别是各自父节点的左孩子节点还是右孩子节点？
    //特别注意：因为是在中序遍历时先找到 e1，后找到 e2，所以 e1 一定不是 e2 的右孩子节点， e2 也一定不是 e1 的左孩子节点。
    //以上三个问题与特别注意之间相互影响，情况非常复杂。经过仔细整理，共有 14 种情况， 每一种情况在调整 e1 和 e2 各自的拓扑关系时都有特殊处理。
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

        //1．e1 是头节点，e1 是 e2 的父节点，此时 e2 只可能是 e1 的右孩子节点。
        //2．e1 是头节点，e1 不是 e2 的父节点，e2 是 e2P 的左孩子节点。
        //3．e1 是头节点，e1 不是 e2 的父节点，e2 是 e2P 的右孩子节点。
        //4．e2 是头节点，e2 是 e1 的父节点，此时 e1 只可能是 e2 的左孩子节点。
        //5．e2 是头节点，e2 不是 e1 的父节点，e1 是 e1P 的左孩子节点。
        //6．e2 是头节点，e2 不是 e1 的父节点，e1 是 e1P 的右孩子节点。
        //7．e1 和 e2 都不是头节点，e1 是 e2 的父节点，此时 e2 只可能是 e1 的右孩子节点，e1 是 e1P 的左孩子节点。
        //8．e1 和 e2 都不是头节点，e1 是 e2 的父节点，此时 e2 只可能是 e1 的右孩子节点，e1 是 e1P 的右孩子节点。
        //9．e1 和 e2 都不是头节点，e2 是 e1 的父节点，此时 e1 只可能是 e2 的左孩子节点，e2 是 e2P 的左孩子节点。
        //10．e1 和 e2 都不是头节点，e2 是 e1 的父节点，此时 e1 只可能是 e2 的左孩子节点，e2 是 e2P 的右孩子节点。
        //11．e1 和 e2 都不是头节点，谁也不是谁的父节点，e1 是 e1P 的左孩子节点，e2 是 e2P 的左 孩子节点。
        //12．e1 和 e2 都不是头节点，谁也不是谁的父节点，e1 是 e1P 的左孩子节点，e2 是 e2P 的右 孩子节点。
        //13．e1 和 e2 都不是头节点，谁也不是谁的父节点，e1 是 e1P 的右孩子节点，e2 是 e2P 的左 孩子节点。
        //14．e1 和 e2 都不是头节点，谁也不是谁的父节点，e1 是 e1P 的右孩子节点，e2 是 e2P 的右 孩子节点。
        if (e1 == head) {
            //1．e1 是头节点，e1 是 e2 的父节点，此时 e2 只可能是 e1 的右孩子节点。
            if (e1 == e2P) {
                e1.left = e2L;
                e1.right = e2R;
                e2.left = e1L;
                e2.right = e1;
            } else if (e2P.left == e2) {
                //2．e1 是头节点，e1 不是 e2 的父节点，e2 是 e2P 的左孩子节点。
                e2P.left = e1;
                e1.left = e2L;
                e1.right = e2R;
                e2.right = e1R;
                e2.left = e1L;
            } else {
                //3．e1 是头节点，e1 不是 e2 的父节点，e2 是 e2P 的右孩子节点。
                e2P.right = e1;
                e1.left = e2L;
                e1.right = e2R;
                e2.right = e1R;
                e2.left = e1L;
            }
            head = e2;
        } else if (e2 == head) {
            if (e2 == e1P) {
                //4．e2 是头节点，e2 是 e1 的父节点，此时 e1 只可能是 e2 的左孩子节点。
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2;
                e1.right = e2R;
            } else if (e1P.left == e1) {
                //5．e2 是头节点，e2 不是 e1 的父节点，e1 是 e1P 的左孩子节点。
                e1P.left = e2;
                e1.left = e2L;
                e1.right = e2R;
                e2.left = e1L;
                e2.right = e2R;
            } else {
                //6．e2 是头节点，e2 不是 e1 的父节点，e1 是 e1P 的右孩子节点。
                e1P.right = e2;
                e1.left = e2L;
                e1.right = e2R;
                e2.left = e1L;
                e2.right = e2R;
            }
            head = e2;
        } else {
            if (e1 == e2P) {
                if (e1P.left == e1) {
                    //7．e1 和 e2 都不是头节点，e1 是 e2 的父节点，此时 e2 只可能是 e1 的右孩子节点，e1 是 e1P 的左孩子节点。
                    e1P.left = e2;
                    e2.right = e1;
                    e2.left = e1.left;
                    e1.left = e2.left;
                    e1.right = e2.right;
                } else {
                    //8．e1 和 e2 都不是头节点，e1 是 e2 的父节点，此时 e2 只可能是 e1 的右孩子节点，e1 是 e1P 的右孩子节点。
                    e1P.right = e2;
                    e2.right = e1;
                    e2.left = e1.left;
                    e1.left = e2.left;
                    e1.right = e2.right;
                }
            } else if (e2 == e1P) {
                if (e2P.left == e2) {
                    //9．e1 和 e2 都不是头节点，e2 是 e1 的父节点，此时 e1 只可能是 e2 的左孩子节点，e2 是 e2P 的左孩子节点。
                    e2P.left = e1;
                    e2.left = e1L;
                    e2.right = e1R;
                    e1.left = e2;
                    e1.right = e2R;
                } else {
                    //10．e1 和 e2 都不是头节点，e2 是 e1 的父节点，此时 e1 只可能是 e2 的左孩子节点，e2 是 e2P 的右孩子节点。
                    e2P.right = e1;
                    e2.left = e1L;
                    e2.right = e1R;
                    e1.left = e2;
                    e1.right = e2R;
                }
            } else if (e1P.left == e1) {
                if (e2P.left == e2) {
                    //11．e1 和 e2 都不是头节点，谁也不是谁的父节点，e1 是 e1P 的左孩子节点，e2 是 e2P 的左 孩子节点。
                    e2P.left = e1;
                    e1P.left = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1R;
                } else {
                    //12．e1 和 e2 都不是头节点，谁也不是谁的父节点，e1 是 e1P 的左孩子节点，e2 是 e2P 的右 孩子节点。
                    e2P.right = e1;
                    e1P.left = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1R;
                }
            } else {
                if (e2P.left == e2) {
                    //13．e1 和 e2 都不是头节点，谁也不是谁的父节点，e1 是 e1P 的右孩子节点，e2 是 e2P 的左 孩子节点。
                    e2P.left = e1;
                    e1P.right = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1R;
                } else {
                    //14．e1 和 e2 都不是头节点，谁也不是谁的父节点，e1 是 e1P 的右孩子节点，e2 是 e2P 的右 孩子节点。
                    e2P.right = e1;
                    e1P.right = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1R;
                }
            }
        }

        return head;
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

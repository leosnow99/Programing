package tree;

//判断二叉树是否为平衡二叉树
//平衡二叉树的性质为：要么是一棵空树，要么任何一个节点的左右子树高度差的绝对值不 超过 1。给定一棵二叉树的头节点 head，判断这棵二叉树是否为平衡二叉树。
//平衡二叉树的标准是：对任何子树来说，左子树和右子树的高度差都不超过 1。
public class BalanceTree {
    public static boolean isBalanced(Node head) {
        return process(head).isBalanced;
    }

    public static ReturnType process(Node head) {
        if (head == null) {
            return new ReturnType(true, 0);
        }
        final ReturnType leftData = process(head.left);
        final ReturnType rightData = process(head.right);
        boolean balance = leftData.isBalanced && rightData.isBalanced && Math.abs(leftData.height - rightData.height) < 2;
        final int height = Math.max(leftData.height, rightData.height) + 1;
        return new ReturnType(balance, height);
    }
}

class ReturnType{
    public boolean isBalanced;
    public int height;

    public ReturnType(boolean isBalanced, int height) {
        this.isBalanced = isBalanced;
        this.height = height;
    }
}

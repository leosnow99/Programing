package tree;

public class GetMaxBST {

    public Node getMaxBST(Node head) {
        return process(head).maxBSTHead;
    }

    public ReturnType process(Node node) {
        // base case : 如果子树是空树
        //最小值为系统最大
        //最大值为系统最小
        if (node == null) {
            return new ReturnType(null, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        // 默认直接得到左树全部信息
        ReturnType leftResult = process(node.left);
        ReturnType rightResult = process(node.right);

        // 以下过程为信息整合
        // 同时对以 X 为头节点的子树也做同样的要求，也需要返回如 ReturnType 描述的全部信息
        //以 X 为头节点的子树的最小值是：左树最小、右树最小及 X 的值三者中最小的
        int min = Math.min(node.value, Math.min(leftResult.min, rightResult.min));
        // 以 X 为头节点的子树的最大值是：左树最大、右树最大及 X 的值三者中最大的
        int max = Math.max(node.value, Math.max(leftResult.max, rightResult.max));
        // 如果只考虑可能性一和可能性二，则以 X 为头节点的子树的最大搜索二叉树大小
        int maxBSTSize = Math.max(leftResult.maxBSTSize, rightResult.maxBSTSize);
        // 如果只考虑可能性一和可能性二，则以 X 为头节点的子树的最大搜索二叉树头节点
        Node maxBSTHead = leftResult.maxBSTSize > rightResult.maxBSTSize ? leftResult.maxBSTHead : rightResult.maxBSTHead;
        // 利用收集的信息，可以判断是否存在第三种可能性
        if (leftResult.maxBSTHead == node.left && rightResult.maxBSTHead == node.right && node.value > leftResult.max && node.value < rightResult.min) {
            maxBSTSize = leftResult.maxBSTSize + rightResult.maxBSTSize + 1;
            maxBSTHead = node;
        }
        return new ReturnType(maxBSTHead, maxBSTSize, min, max);
    }

    private static class ReturnType {
        Node maxBSTHead;
        int maxBSTSize;
        int min;
        int max;

        public ReturnType(Node maxBSTHead, int maxBSTSize, int min, int main) {
            this.maxBSTHead = maxBSTHead;
            this.maxBSTSize = maxBSTSize;
            this.min = min;
            this.max = main;
        }
    }
}




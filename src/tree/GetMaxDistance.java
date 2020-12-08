package tree;


/**
 * <p>
 * 从二叉树的节点 A 出发，可以向上或者向下走，但沿途的节点只能经过一次，当到达节点 B 时，路径上的节点数叫作 A 到 B 的距离。
 *</p>
 * <p>
 * 树形 dp 套路第一步：以某个节点 X 为头节点的子树中，分析答案来自哪些可能性，并且这 种分析是以 X 的左子树、X 的右子树和 X 整棵树的角度来考虑可能性的。
 *  可能性一：以 X 为头节点的子树，最大距离可能是左子树上的最大距离。
 *  可能性二：以 X 为头节点的子树，最大距离可能是右子树上的最大距离。
 *  可能性三：以 X 为头节点的子树，最大距离可能是从 X 的左子树离 X 最远的节点，先到达 X，然后走到 X 的右子树离 X 最远的节点。也就是左子树高度+右子树高度+1。
 * </p>
 *
 * <p>
 *     树形 dp 套路第二步：根据第一步的可能性分析，列出所有需要的信息。左子树和右子树都 需要知道自己这棵子树上的最大距离，以及高度这两个信息。
 * </p>
 *
 * <p>
 *     树形 dp 套路第三步：根据第二步信息汇总。
 * </p>
 *
 */
public class GetMaxDistance {
    public static class ReturnType {
        public int height;
        public int maxDistance;

        public ReturnType(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }

    public static ReturnType process(Node head) {
        if (head == null) {
            return new ReturnType(0, 0);
        }
        final ReturnType leftData = process(head.left);
        final ReturnType rightData = process(head.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        int maxDistance = Math.max(leftData.height + 1 + rightData.height + 1, Math.max(leftData.maxDistance, rightData.maxDistance));
        return new ReturnType(height, maxDistance);
    }

    public static int getMaxDistance(Node head) {
        return process(head).maxDistance;
    }


}

package tree;

/**
 * 给定一棵<b>完全二叉树</b>的头节点 head，返回这棵树的节点个数。
 *
 * @author leosnow
 */
public class NodeNum {
    /**
     * <p>
     *     如果 head==null，说明是空树，直接返回 0。
     * </p>
     * <p>
     *     如果不是空树，就求树的高度，求法是找到树的最左节点看能到哪一层，层数记为 h。
     * </p>
     * <p>
     *     这一步是求解的主要逻辑，也是一个递归过程，记为 bs(node,l,h)，node 表示当前节点， l 表示 node 所在的层数，h 表示整棵树的层数是始终不变的。
     *     bs(node,l,h)的返回值表示以 node 为头节点的完全二叉树的节点数是多少。初始时，node 为头节点 head，l 为 1，因为 head 在第 1 层，
     *     一共有 h 层始终不变。
     * </p>
     * @param head 树的头节点
     * @return 树的节点个数
     */
    public static int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 0));
    }

    public static int bs(Node node, int level, int high) {
        if (level == high) {
            return level;
        }
        if (mostLeftLevel(node.right, level + 1) == high) {
            //，发现它能到达最后一层，即 h==4 层。此时说明 node 的整棵左子树都是满二叉树，并且层数为 h-l 层，一棵层数为 h-l 的满二叉 树，其节点数为 2h-1-1 个。
            //如果加上 node 节点自己，那么节点数为 2^(h-1)-1+1==2^(h-1)个。
            return (1 << (high - 1) + bs(node.right, level + 1, high));
        } else {
            //说 明 node 的整棵右子树都是满二叉树，并且层数为 h-l-1 层，一棵层数为 h-l-1 的满二叉树，其节 点数为 2h-l-1-1 个。如果加上 node 节点自己，
            //那么节点数为 2^(h-l-1)-1+1==2^(h-l-1)个
            return (1 << (high - level - 1) + bs(node.left, level + 1, high));
        }
    }

    public static int genHigh(Node head) {
        if (head == null) {
            return 0;
        }
        return genHigh(head.left) + 1;
    }

    public static int mostLeftLevel(Node head, int level) {
        while (head != null) {
            head = head.left;
            level++;
        }
        return --level;
    }
}

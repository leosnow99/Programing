package tree;

//给定彼此独立的两棵树头节点分别为 t1 和 t2，判断 t1 树是否包含 t2 树全部的拓扑结构。
public class ContainsTree {
    //如果 t1 中某棵子树头节点的值与 t2 头节点的值一样，则从这两个头节点开始匹配，匹配 的每一步都让 t1 上的节点跟着 t2 上的节点的先序遍历移动，
    //每移动一步，都检查 t1 的当前节 点是否与 t2 当前节点的值一样。比如，题目中的例子，t1 中的节点 2 与 t2 中的节点 2 匹配，
    //然后 t1 跟着 t2 向左，发现 t1 中的节点 4 与 t2 中的节点 4 匹配，t1 跟着 t2 继续向左，发现 t1 中的节点 8 与 t2 中的节点 8 匹配，
    //此时 t2 回到 t2 中的节点 2，t1 也回到 t1 中的节点 2，然后 t1 跟着 t2 向右，发现 t1 中的节点 5 与 t2 中的节点 5 匹配。t2 匹配完毕，
    //结果返回 true。如果 匹配的过程中发现有不匹配的情况，则直接返回 false，说明 t1 的当前子树从头节点开始，无法 与 t2 匹配，那么再去寻找 t1 的下一棵子树。
    //t1 的每棵子树上都有可能匹配出 t2，所以都要检 查一遍。
    public static boolean contains(Node headPre, Node headSecond) {
        if (headSecond == null) {
            return true;
        }
        if (headPre == null) {
            return false;
        }
        return check(headPre, headSecond);
    }

    public static boolean check(Node headPre, Node headSecond) {
        if (headSecond == null) {
            return true;
        }
        if (headPre == null || headPre.value != headSecond.value) {
            return false;
        }
        return check(headPre.left, headSecond.left) && check(headPre.right, headSecond.right);
    }

}

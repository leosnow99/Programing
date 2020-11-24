package tree;

import com.sun.org.apache.regexp.internal.RE;

import java.util.HashMap;
import java.util.Map;

//给定一棵二叉树的头节点 head，已知所有节点的值都不一样，返回其中最大的且符合搜索 二叉树条件的最大拓扑结构的大小。
public class BSTTopSize {

    //方法一：二叉树的节点数为 N，时间复杂度为 O(N2 )的方法。 首先来看这样一个问题，以节点 h 为头节点的树中，
    //在拓扑结构中也必须以 h 为头节点的 情况下，怎么找到符合搜索二叉树条件的最大结构？这个问题有一种比较容易理解的解法，
    //我 们先考查 h 的孩子节点，根据孩子节点的值从 h 开始按照二叉搜索的方式移动，如果最后能移 动到同一个孩子节点上，
    //说明这个孩子节点可以作为这个拓扑的一部分，并继续考查这个孩子 节点的孩子节点，一直延伸下去。
    public static int bstTopSize(Node head) {
        if (head == null) {
            return 0;
        }
        int max = maxTop(head, head);
        max = Math.max(max, bstTopSize(head.left));
        max = Math.max(max, bstTopSize(head.right));

        return max;
    }

    private static int maxTop(Node h, Node n) {
        if (n != null && isBSTNode(h, n, n.value)) {
            return maxTop(h, n.left) + maxTop(h, n.right) + 1;
        }
        return 0;
    }

    private static boolean isBSTNode(Node h, Node n, int value) {
        if (h == null) {
            return false;
        }
        if (h == n) {
            return true;
        }
        return isBSTNode(h.value > value ? h.left : h.right, n, value);
    }

    private static class Record{
        public int l;
        public int r;
        public Record(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public static int bstTopSizeHigh(Node head) {
        Map<Node, Record> map = new HashMap<>();
        return posOrder(head, map);
    }

    private static int posOrder(Node h, Map<Node, Record> map) {
        if (h == null) {
            return 0;
        }
        int ls = posOrder(h.left, map);
        int rs = posOrder(h.right, map);
        modifyMap(h.left, h.value, map, true);
        modifyMap(h.right, h.value, map, false);

        final Record lRecord = map.get(h.left);
        final Record rRecord = map.get(h.right);

        int lBst = lRecord == null ? 0 : lRecord.l + lRecord.r + 1;
        int rBst = rRecord == null ? 0 : rRecord.l + rRecord.r + 1;

        map.put(h, new Record(lBst, rBst));
        return Math.max(lBst + rBst + 1, Math.max(ls, rs));
    }

    private static int modifyMap(Node node, int value, Map<Node, Record> map, boolean isRight) {
        if (node == null || !map.containsKey(node)) {
            return  0;
        }
        Record record = map.get(node);
        if ((isRight && node.value > value) || (!isRight && node.value < value)) {
            map.remove(node);
            return record.l + record.r + 1;
        } else {
           int minus = modifyMap(isRight ? node.right : node.left, value, map, isRight);
            if (isRight) {
                record.r = record.r - minus;
            } else {
                record.l = record.l - minus;
            }
            map.put(node, record);
            return minus;

        }
    }
}

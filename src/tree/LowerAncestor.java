package tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 给定一棵二叉树的头节点 head，以及这棵树中的两个节点 o1 和 o2，请返回 o1 和 o2 的最 近公共祖先节点。
 *
 * 进阶问题：如果查询两个节点的最近公共祖先的操作十分频繁，想法让单条查询的查询时 间减少。
 *
 * 再进阶问题：给定二叉树的头节点 head，同时给定所有想要进行的查询。二叉树的节点数 量为 N，查询条数为 M，请在时间复杂度为 O(N+M)内返回所有查询的结果。
 * @author  leosnow
 */
public class LowerAncestor {

    public static Node getLowestAncestor(Node head, Node o1, Node o2) {
        if (head == null || head == o1 || head == o2) {
            return head;
        }
        Node left = getLowestAncestor(head.left, o1, o2);
        Node right = getLowestAncestor(head.right, o1, o2);

        if (left != null && right != null) {
            return head;
        }

        return left == null ? right : left;
    }

    public static Node getLowestAncestorAdvanced(Node head, Node o1, Node o2) {
        final RecordFirst recordFirst = new RecordFirst(head);
        return recordFirst.query(o1, o2);
    }
}

/**
 * 结构一：建立二叉树中每个节点对应的父节点信息，是一张哈希表。
 *
 */
class RecordFirst {
    private final Map<Node, Node> map;

    public RecordFirst(Node head) {
        this.map = new HashMap<>();
        if (head != null) {
            map.put(head, null);
            setMap(head);
        }
    }

    public void setMap(Node head) {
        if (head == null) {
            return;
        }
        if (head.left != null) {
            map.put(head.left, head);
        }
        if (head.right != null) {
            map.put(head.right, head);
        }
        setMap(head.right);
        setMap(head.left);
    }

    //结构一建立记录的过程时间复杂度为 O(N)、额外空间复杂度为 O(N)。进行查询操 作时，时间复杂度为 O(h)，其中，h 为二叉树的高度。
    public Node query(Node o1, Node o2) {
        final HashSet<Node> path = new HashSet<>();
        while (map.containsKey(o1)) {
            o1 = map.get(o1);
            path.add(o1);
        }
        while (!path.contains(o2)) {
            o2 = map.get(o2);
            if (o2 == null) {
                break;
            }
        }
        return o2;
    }
}

/**
 * 结构二：直接建立任意两个节点之间的最近公共祖先记录，便于以后查询。
 * 建立记录的具体过程如下：
 * 1．对二叉树中的每棵子树（一共 N 棵）都进行步骤 2。
 * 2．假设子树的头节点为 h，h 所有的后代节点和 h 节点的最近公共祖先都是 h，记录下来。h 左子树的每个节点和 h 右子树的每个节点的最近公共祖先都是 h，记录下来。
 * 为了保证记录不重复，设计一种好的实现方式是这种结构实现的重点。
 *
 * 如果二叉树的节点数为 N，想要记录每两个节点之间的信息，信息的条数为((N-1)×N)/2。所 以建立结构二的过程的额外空间复杂度为 O(N2 )，时间复杂度为 O(N2 )，
 * 单次查询的时间复杂度 为 O(1)。
 *
 * @author leosnow
 */
class RecordSecond {
    private final Map<Node, Map<Node, Node>> map;

    public RecordSecond(Node head) {
        map = new HashMap<>();
        initMap(head);
        setMap(head);
    }

    //初始化所有节点 --初始化所有节点Map
    private void initMap(Node head) {
        if (head == null) {
            return;
        }
        map.put(head, new HashMap<>());
        initMap(head.left);
        initMap(head.right);
    }

    private void setMap(Node head) {
        if (head == null) {
            return;
        }
        headRecord(head.left, head);
        headRecord(head.right, head);
        subRecord(head);
        setMap(head.left);
        setMap(head.right);
    }

    private void headRecord(Node n, Node h) {
        if (n == null) {
            return;
        }
        map.get(n).put(h, h);
        headRecord(n.left, h);
        headRecord(n.right, h);
    }

    private void subRecord(Node head) {
        if (head == null) {
            return;
        }
        preLeft(head.left, head.right, head);
        subRecord(head.left);
        subRecord(head.right);
    }

    private void preLeft(Node l, Node r, Node h) {
        if (l == null) {
            return;
        }
        preRight(l, r, h);
        preLeft(l.left, r, h);
        preLeft(l.right, r, h);
    }

    private void preRight(Node l, Node r, Node h) {
        if (r == null) {
            return;
        }
        map.get(l).put(r, h);
        preRight(l, r.left, h);
        preRight(l, r.right, h);
    }

    public Node query(Node o1, Node o2) {
        if (o1 == o2) {
            return o1;
        }
        if (map.get(o1).get(o2) != null) {
            return map.get(o1).get(o2);
        }
        if (map.get(o2).get(o1) != null) {
            return map.get(o2).get(o1);
        }
        return null;
    }
}
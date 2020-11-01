package linked;

import java.util.HashMap;

//复制含有随机指针节点的链表
public class CopyListWithRand {
    //使用哈希表
    public static Node copyListWithRand(Node head) {
        final HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }

        return map.get(cur);
    }

    /**
     * 不借助hash表
     * <p>
     * 1. 从左到右遍历链表，对每个节点 cur 都复制生成相应的副本节点 copy，然后把 copy 放 在 cur 和下一个要遍历节点的中间。
     * <p>
     * 2. 再从左到右遍历链表，在遍历时设置每一个副本节点的 rand 指针。
     * <p>
     * 3.步骤 2 完成后，节点 1，2，3，……之间的 rand 关系没有任何变化，节点 1′，2′，3′…… 之间的 rand 关系也被正确设置了，
     * 此时所有的节点与副本节点串在一起，将其分离出来即可。
     */
    public static Node copyListWithRandHigh(Node head) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        Node next;

        //实现拷贝节点并将节点插入链表中间
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }

        cur = head;
        Node curCopy;
        //设置赋值节点的rand指针
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }

        Node res = head.next;
        cur = head;
        //拆分
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            cur = next;
            curCopy.next = next != null ? next.next : null;
        }

        return res;
    }

    static class Node {
        public int value;
        public Node next;
        public Node rand;

        Node(int value) {
            this.value = value;
        }
    }
}



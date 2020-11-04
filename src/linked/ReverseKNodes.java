package linked;

import java.util.Stack;

/**
 * 将单链表的每 K 个节点之间逆序
 * <p>
 * 给定一个单链表的头节点 head，实现一个调整单链表的函数，使得每 K 个节点之间逆序， 如果最后不够 K 个节点一组，则不调整最后几个节点。
 */
public class ReverseKNodes {

    //利用栈结构的解法
    public static Node reverseKNodes(Node head, int k) {
        if (k < 2) {
            return head;
        }
        final Stack<Node> stack = new Stack<>();
        Node newHead = head;
        Node cur = head;
        Node pre = null;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            stack.push(cur);
            if (stack.size() == k) {
                pre = resign(stack, pre, next);
                newHead = newHead == head ? cur : newHead;
            }
            cur = next;
        }
        return newHead;
    }

    //反转栈中的k个元素
    public static Node resign(Stack<Node> stack, Node left, Node right) {
        Node cur = stack.pop();
        if (left != null) {
            left.next = cur;
        }
        Node next = null;
        while (!stack.isEmpty()) {
            next = stack.pop();
            cur.next = next;
            cur = next;
        }
        cur.next = right;
        return cur;
    }

    /**
     * 不需要栈结构，在原链表中直接调整。
     * 用变量记录每一组开始的第一个节点和最后一个节点，然后直接逆序调整，把这一组的节 点都逆序。
     * 和方法一一样，同样需要注意第一组节点的特殊处理，以及之后的每个组在逆序重 连之后，需要让该组的第一个节点（原来是最后一个节点）
     * 被之前组的最后一个节点连接上， 将该组的最后一个节点（原来是第一个节点）连接下一个节点。
     */
    public static Node reverseKNodesHigh(Node head, int k) {
        if (k < 2) {
            return head;
        }

        Node starter;
        Node cur = head;
        Node pre = null;
        Node next;
        int count = 1;
        while (cur != null) {
            next = cur.next;
            if (count == k) {
                starter = pre == null ? head : pre.next;
                head = pre == null ? cur : head;
                resignHigh(starter, cur, pre, next);
                pre = starter;
                count = 0;
            }
            count++;
            cur = next;
        }
        return head;
    }

    public static void resignHigh(Node starter, Node end, Node left, Node right) {
        Node pre = starter;
        Node cur = starter.next;
        Node next;
        while (cur != right) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (left != null) {
            left.next = end;
        }
        starter.next = right;
    }
}

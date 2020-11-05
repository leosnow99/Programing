package linked;

import java.util.Stack;

//在单链表中删除指定值的节点
//给定一个链表的头节点 head 和一个整数 num，请实现函数将值为 num 的节点全部删除。
public class RemoveValue {

    //利用栈或者其他容器收集节点的方法。时间复杂度为 O(N)，额外空间复杂度为 O(N)。
    public static Node removeValue(Node head, int value) {
        final Stack<Node> stack = new Stack<>();
        while (head != null) {
            if (head.value != value) {
                stack.push(head);
            }
            head = head.next;
        }
        while (!stack.isEmpty()) {
            stack.peek().next = head;
            head = stack.pop();
        }
        return head;
    }

    //不用任何容器而直接调整的方法。时间复杂度为 O(N)，额外空间复杂度为 O(1)
    public static Node removeValueHigh(Node head, int value) {
        //寻找新的头节点
        while (head != null) {
            if (head.value != value) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == value) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}

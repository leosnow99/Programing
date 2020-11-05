package linked;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.DoubleBinaryOperator;

//将搜索二叉树转换成双向链表
public class TreeConvertToLinkedList {

    //用队列等容器收集二叉树中序遍历结果的方法。时间复杂度为 O(N)，额外空间复 杂度为 O(N)
    public static DoubleNode convert(DoubleNode head) {
        final Queue<DoubleNode> queue = new LinkedList<>();
        //生成一个队列，记为 queue，按照二叉树中序遍历的顺序，将每个节点放入 queue 中
        inOrderToQueue(head, queue);
        if (queue.isEmpty()) {
            return head;
        }
        head = queue.poll();
        DoubleNode pre = null;
        pre.pre = null;
        DoubleNode cur = null;

        //从 queue 中依次弹出节点，并按照弹出的顺序重连所有的节点即可
        while (!queue.isEmpty()) {
            cur = queue.poll();
            pre.next = cur;
            cur.pre = pre;
            pre = cur;
        }
        return head;
    }

    public static void inOrderToQueue(DoubleNode head, Queue<DoubleNode> queue) {
        if (head == null) {
            return;
        }
        inOrderToQueue(head.pre, queue);
        queue.offer(head);
        inOrderToQueue(head.next, queue);
    }

    //利用递归函数，除此之外，不使用任何容器的方法。时间复杂度为 O(N)，额外空 间复杂度为 O(h)，h 为二叉树的高度
    public static DoubleNode convertHigh(DoubleNode head) {
        if (head == null) {
            return null;
        }
        return process(head).starter;
    }

    //先把以 X 为头的搜索二叉树的左子树转换为有序双向链表，并且返回左子树有 序双向链表的头和尾，
    //然后把以 X 为头的搜索二叉树的右子树转换为有序双向链表，并且返回 右子树有序双向链表的头和尾，接着通过 X 把两部分接起来即可。
    private static ReturnType process(DoubleNode head) {
        if (head == null) {
            return new ReturnType(null, null);
        }
        final ReturnType leftList = process(head.pre);
        final ReturnType rightList = process(head.next);
        if (leftList.end != null) {
            leftList.end.next = head;
        }
        head.pre = leftList.end;
        head.next = rightList.starter;
        if (rightList.starter != null) {
            rightList.starter.pre = head;
        }
        return new ReturnType(leftList.starter == null ? head : leftList.starter, rightList.end == null ? head : rightList.end);
    }

    private static class ReturnType {
        DoubleNode starter;
        DoubleNode end;

        public ReturnType(DoubleNode starter, DoubleNode end) {
            this.starter = starter;
            this.end = end;
        }
    }
}

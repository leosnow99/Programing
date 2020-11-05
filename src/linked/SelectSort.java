package linked;

//给定一个无序单链表的头节点 head，实现单链表的选择排序。 要求：额外空间复杂度为 O(1)。
public class SelectSort {
    public static Node selectionSort(Node head) {
        Node tail = null;   //排序部分尾部
        Node cur = head;    //未排序部分头部
        Node smallPre = null; //最小节点的前一个节点
        Node small = null; //最小节点
        while (cur != null) {
            small = cur;
            smallPre = getSmallPreNode(cur);
            //判断第一个节点是否是最小节点
            if (smallPre != null) {
                small = smallPre.next;
                smallPre.next = small.next;
            }
            cur = small == cur ? cur.next : cur;
            if (tail == null) {
                head = small;
            } else {
                tail.next = small;
            }
            tail = small;
        }
        return head;
    }

    private static Node getSmallPreNode(Node head) {
        if (head == null) {
            return head;
        }

        Node smallPre = null;
        Node small = head;
        Node pre= head;
        Node cur = head.next;
        while (cur != null) {
            if (cur.value < small.value) {
                smallPre = pre;
                small = cur;
            }
            pre = cur;
            cur = cur.next;
        }
        return smallPre;
    }
}


package linked;

//合并两个有序的单链表
public class MergeTwoSortedLinkedList {
    /**
     * 比较 head1 和 head2 的值，小的节点也是合并后链表的最小节点，这个节点无疑应该是 合并链表的头节点，
     * 记为 head；在之后的步骤里，哪个链表的头节点的值更小，另一个链表的 所有节点都会依次插入到这个链表中。
     */
    public static Node merge(Node headFirst, Node headSecond) {
        if (headFirst == null || headSecond == null) {
            return headFirst == null ? headSecond : headFirst;
        }
        Node head = headFirst.value < headSecond.value ? headFirst : headSecond;
        Node curFirst = head == headFirst ? headFirst : headSecond;
        Node curSecond = head == headFirst ? headSecond : headFirst;
        Node pre = null;
        Node next;

        while (curFirst != null && curSecond != null) {
            if (curFirst.value <= curSecond.value) {
                pre = curFirst;
                curFirst = curFirst.next;
            } else {
                next = curSecond.next;
                pre.next = curSecond;
                curSecond.next = curFirst;
                pre = curSecond;
                curSecond = next;
            }
        }

        pre.next = curFirst == null ? curSecond : curFirst;
        return head;
    }
}

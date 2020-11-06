package linked;

//按照左右半区的方式重新组合单链表
//给定一个单链表的头部节点 head，链表长度为 N，如果 N 为偶数，那么前 N/2 个节点算作 左半区，后 N/2 个节点算作右半区；
//如果 N 为奇数，那么前 N/2 个节点算作左半区，后 N/2+1 个节点算作右半区。 左半区从左到右依次记为 L1->L2->…，
//右半区从左到右依次记为 R1->R2->…，请将单链表调整成 L1->R1->L2->R2->…的形式。
public class RightLeftCrossMerge {

    public static void relocate(Node head) {
        if (head == null || head.next == null) {
            return;
        }
        Node mid = head;
        Node right = head.next;
        while (right.next != null || right.next.next != null) {
            mid = mid.next;
            right = right.next.next;
        }
        right = mid.next;
        mid.next = null;
        mergeLR(head, right);
    }

    public static void mergeLR(Node left, Node right) {
        Node next;
        while (left.next != null) {
            next = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = next;
        }
        left.next = right;
    }
}

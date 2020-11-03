package linked;

import java.util.Stack;

//两个单链表生成相加链表
public class AddTwoList {

    //利用栈结构求解
    public static Node addList(Node headFirst, Node headSecond) {
        final Stack<Integer> first = new Stack<>();
        final Stack<Integer> second = new Stack<>();
        while (headFirst != null) {
            first.push(headFirst.value);
            headFirst = headFirst.next;
        }

        while (headSecond != null) {
            second.push(headSecond.value);
            headSecond = headSecond.next;
        }
        int ca = 0;
        int firstValue;
        int secondValue;
        int sum;
        Node node = null;
        Node pre;
        while (!first.isEmpty() || !second.isEmpty()) {
            firstValue = first.isEmpty() ? 0 : first.pop();
            secondValue = second.isEmpty() ? 0 : second.pop();
            sum = firstValue + secondValue + ca;

            pre = node;
            node = new Node(sum % 10);
            node.next = pre;
            ca = sum / 10;
        }
        if (ca == 1) {
            pre = node;
            node = new Node(1);
            node.next = pre;
        }
        return node;
    }

    //利用链表的逆序求解，可以节省用栈的空间

    /**
     * 1．将两个链表逆序，这样就可以依次得到从低位到高位的数字。
     * <p>
     * 2．同步遍历两个逆序后的链表，这样就依次得到两个链表从低位到高位的数字，在这个过 程中生成相加链表即可，
     * 同时需要关注每一步是否有进位，用 ca 表示。具体过程与方法一的步 骤 2 相同。
     * <p>
     * 3．当两个链表都遍历完成后，还要关注进位信息是否为 1，如果为 1，还要生成一个节点 值为 1 的新节点。
     * <p>
     * 4．将两个逆序的链表再逆序一次，即调整成原来的样子。
     * <p>
     * 5．返回新生成的结果链表。
     */
    public static Node addListHigh(Node headFirst, Node headSecond) {
        headFirst = reverseList(headFirst);
        headSecond = reverseList(headSecond);

        int ca = 0;
        int firstValue;
        int secondValue;
        int sum;

        Node first = headFirst;
        Node second = headSecond;
        Node pre;
        Node node = null;

        while (first != null || second != null) {
            firstValue = first != null ? first.value : 0;
            secondValue = second != null ? second.value : 0;
            sum = firstValue + secondValue + ca;
            pre = node;
            node = new Node(sum % 10);
            node.next = pre;
            ca = sum / 10;

            first = first != null ? first.next : null;
            second = second != null ? second.next : null;
        }

        if (ca == 1) {
            pre = node;
            node = new Node(1);
            node.next = pre;
        }

        reverseList(headFirst);
        reverseList(headSecond);
        return node;
    }

    public static Node reverseList(Node head) {
        Node pre = null;
        Node next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}

package linked;

import java.util.Stack;

//通用链表算法
public class UniversalAlgorithm {
    //打印两个有序链表的公共部分
    public static void printCommonPart(Node first, Node second) {
        while (first != null && second != null) {
            if (first.value < second.value) {
                first = first.next;
            } else if (first.value > second.value) {
                second = second.next;
            } else {
                System.out.println("common value: " + first.value);
                first = first.next;
                second = second.next;
            }
        }
    }

    //在单链表和双链表中删除倒数第 K 个节点
    public static Node removeLastKthNode(Node head, int lastKth) {
        if (head == null || lastKth < 1) {
            return null;
        }
        Node cur = head;
        while (cur != null) {
            lastKth--;
            cur = cur.next;
        }

        //如果 K 值等于 0，说明链表倒数第 K 个节点就是头节点，此时直接返回 head.next，
        //也就是原链表的第二个节点，让第二个节点作为链表的头返回即可，相当于删除头 节点；
        if (lastKth == 0) {
            head = head.next;
        }

        //重新从头节点开始走，每移动一步，就让 K 的值加 1。
        //当 K 等于 0 时，移动停止，移动到的节点就是要删除节点的前一个节点。
        if (lastKth <= 0) {
            cur = head;
            while (++lastKth != 0) {
                cur = cur.next;
            }
            //找到被删节点的上一个节点
            cur.next = cur.next.next;
        }

        return head;
    }

    //删除链表的中间节点
    //链表长度每增加 2（ 要删除的节点就后移一个节点
    public static Node removeMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        if (head.next.next == null) {
            return head.next;
        }

        Node pre = head;
        Node cur = head.next.next;
        while (cur.next != null && cur.next.next != null) {
            pre = pre.next;
            cur = cur.next.next;
        }
        pre.next = pre.next.next;
        return head;
    }

    //删除a/b 处的节点
    public static Node removeRatio(Node head, int a, int b) {
        if (a < 1 || a > b) {
            return head;
        }
        //获取链表长度
        int len = 0;
        Node cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        len = (int) Math.ceil((double) a * len / (double) b);
        if (len == 1) {
            head = head.next;
        }
        if (len > 1) {
            cur = head;
            while (--len != 1) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }

    //反转单向链表
    public static Node reverseList(Node head) {
        //辅助节点
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

    //反转双向链表
    public static DoubleNode reverseList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    //反转部分单向链表
    public static Node reversePart(Node head, int from, int to) {
        if (from < 1 || from >= to || head == null) {
            return head;
        }
        int len = 0;
        Node cur = head;
        //反转节点前一个节点
        Node fromPreNode = null;
        //反转节点后一个节点
        Node toLastNode = null;
        while (cur != null) {
            len++;
            if (len == from - 1) {
                fromPreNode = cur;
            }
            if (len == to + 1) {
                toLastNode = cur;
            }
            cur = cur.next;
        }
        if (to > len) {
            return head;
        }

        //如果 fPre 为 null，说明反转部分是包含头节点的，则返回新的头节点，也就是没反转之 前反转部分的最后一个节点，也是反转之后反转部分的第一个节点；
        cur = fromPreNode == null ? head : fromPreNode.next;

        Node temNode = cur.next;
        cur.next = toLastNode;

        Node next;
        while (temNode != toLastNode) {
            next = temNode.next;
            temNode.next = cur;
            cur = temNode;
            temNode = next;
        }

        if (fromPreNode != null) {
            fromPreNode.next = cur;
            return head;
        }
        return cur;
    }

    //约瑟夫夫问题（环形单链表报数）时间复杂度O（m*n）
    public static Node josephusKill(Node head, int n) {
        if (head == null || head.next == null || n < 1) {
            return head;
        }
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }
        int count = 0;
        while (head != last) {
            if (++count == n) {
                last.next = head.next;
                count = 0;
            } else {
                last = last.next;
            }
            head = head.next;
        }
        return head;
    }

    //判断一个链表是否为回文结构
    public static boolean isPalindrome(Node head) {
        final Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

    //将链表的右半部分“折过去”，然后让它和左半部分比较 --右半部分存入栈中
    public static boolean isPalindromeHalf(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        //链表右半部分起点
        Node right = head.next;
        Node cur = head;
        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }

        final Stack<Node> stack = new Stack<>();
        while (right != null) {
            stack.push(right);
            right = right.next;
        }

        //比较
        while (!stack.isEmpty()) {
            if (stack.pop().value != head.value) {
                return false;
            }
            head = head.next;
        }
        return false;
    }

    //改变链表右半区的结构，使整个右半区反转
    public static boolean isPalindromeBinary(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;

        //查找中间节点
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;  // n1 -> 链表中部
            n2 = n2.next.next; //n2 -> 链表结尾
        }

        //反转连标右半部分
        n2 = n1.next; //右半部分第一个节点
        n1.next = null;
        Node n3;
        while (n2 != null) {
            n3 = n2.next; //保存下一个节点
            n2.next = n1; //反转下一个节点
            n1 = n2; //n1移动
            n2 = n3;//n2移动
        }

        //检查回文串
        boolean res = true;
        n2 = head; //左半部分起点
        n3 = n1; //右半部分起点 n3保存最后节点
        while (n2 != null && n1 != null) {
            if (n2.value != n1.value) {
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }

        //恢复右半部分
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = head;

        Node node = josephusKill(head, 3);
        if (node == null) {
            return;
        }
        System.out.println(node.value);
    }

}

class Node {
    int value;
    Node next;

    public Node(int data) {
        this.value = data;
    }
}

class DoubleNode {
    private final int value;
    DoubleNode pre;
    DoubleNode next;

    public DoubleNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

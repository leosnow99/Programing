package linked;

public class MyLinkedList {
    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node head;

    private Node last;

    private int size;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public MyLinkedList() {
        this.size = 0;
    }

    //头插法插入结点
    public void listAddNodeHead(int value) {
        final Node node = new Node(value);
        this.size++;
        if (last == null) {
            this.last = node;
        }
        node.next = head;
        head = node;
    }

    //尾插法插入节点
    public void listAddNodeTail(int value) {
        final Node node = new Node(value);
        if (last == null) {
            this.head = node;
            this.last = node;
            return;
        }
        last.next = node;
        last = node;
        this.size += 1;
    }

    //从链表删除值为value的结点
    public void listDelNode(int value) {
        Node node = head;
        if (node.value == value) {
            head = node.next;
            this.size -= 1;
        }
        while (node.next != null) {
            if (node.next.value == value) {
                node.next = node.next.next;
                this.size -= 1;
            }
            node = node.next;
        }
    }

    //链表遍历
    public void string() {
        if (head == null) {
            System.out.println("空链表!");
        }
        Node current = head;
        StringBuilder stringBuilder = new StringBuilder();
        while (current != null) {
            stringBuilder.append(current.value).append(" -> ");
            current = current.next;
        }
        stringBuilder.replace(stringBuilder.lastIndexOf(" -> "), stringBuilder.length(), "");
        System.out.println(stringBuilder.toString());
    }

    //使用数组初始化一个链表，共len个元素
    public static MyLinkedList initLinked(int[] values) {

        final MyLinkedList myLinkedList = new MyLinkedList();
        if (values == null || values.length == 0) {
            return myLinkedList;
        }
        for (int value : values) {
            myLinkedList.listAddNodeTail(value);
        }
        return myLinkedList;
    }

    //链表长度函数
    public int length() {
        return this.size;
    }

    //链表拷贝
    public static MyLinkedList listCopy(MyLinkedList list) {
        final MyLinkedList newList = new MyLinkedList();
        Node current = list.head;
        while (current != null) {
            newList.listAddNodeTail(current.value);
            current = current.next;
        }
        return newList;
    }

    //链表合并
    public static MyLinkedList listMerge(MyLinkedList firstList, MyLinkedList secondList) {
        final MyLinkedList resultList = new MyLinkedList();

        if (firstList == null) {
            return secondList;
        } else if (secondList == null) {
            return firstList;
        }

        Node firstHead = firstList.head;
        Node secondHead = secondList.head;

        while (firstHead != null && secondHead != null) {
            if (firstHead.value < secondHead.value) {
                resultList.listAddNodeTail(firstHead.value);
                firstHead = firstHead.next;
            } else {
                resultList.listAddNodeTail(secondHead.value);
                secondHead = secondHead.next;
            }
        }

        if (firstHead != null) {
            resultList.last.next = firstHead;
            resultList.last = firstList.last;
        } else {
            resultList.last.next = secondHead;
            resultList.last = secondList.last;
        }
        resultList.size = firstList.size + secondList.size;

        return resultList;
    }

    //链表相交判断，如果相交返回相交的结点，否则返回NULL。
    public static Node listIntersect(MyLinkedList firstList, MyLinkedList secondList) {
        final int firstLen = firstList.length();
        final int secondLen = secondList.length();
        final int delta = Math.abs(firstLen - secondLen);

        Node longList = firstList.getHead();
        Node shortList = secondList.getHead();

        if (firstLen < secondLen) {
            longList = secondList.getHead();
            shortList = firstList.getHead();
        }

        for (int i = 0; i < delta; i++) {
            longList = longList.next;
        }

        while (longList != null && shortList != null) {
            if (longList == shortList) {
                return longList;
            }
            longList = longList.next;
            shortList = shortList.next;
        }

        return null;
    }

    //检测链表是否有环-Floyd判圈算法
    // 若存在环，返回相遇结点，否则返回NULL
    public Node listDetectLoop() {
        Node fast = head;
        Node slow = head;

        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                System.out.println("Found Loop");
                return slow;
            }
        }
        return null;
    }

    //查找链表中环入口
    public Node findLoopNode() {
        Node meetNode = listDetectLoop();
        if (meetNode == null) {
            return null;
        }

        Node current = head;
        while (current != meetNode) {
            current = current.next;
            meetNode = meetNode.next;
        }
        return meetNode;
    }

    public static void main(String[] args) {
/*
        final MyLinkedList list = MyLinkedList.initLinked(new int[]{1, 2, 3, 4, 11, 13, 17, 19, 1010});
        Node tem = new Node(13);
        list.last.next = tem;
        list.last = tem;
        list.size += 1;
        list.listAddNodeTail(1999);

        final MyLinkedList newList = MyLinkedList.initLinked(new int[]{2, 3, 12, 34});
        newList.last.next = tem;
        newList.last = tem;
        newList.size += 2;

        list.string();
        newList.string();
        System.out.println(MyLinkedList.listIntersect(list, newList).value);
*/
        final MyLinkedList list = MyLinkedList.initLinked(new int[]{1, 2, 3, 4});
        list.head.next.next.next.next = list.head.next;
        System.out.println(list.findLoopNode().value);
    }
}

package linked;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * <p>
 * 在左、中、右三个部分的内部也做顺序要求，要求每部分里的节点从左到右的顺序与 原链表中节点的先后次序一致。
 */
public class ListPartition {
    /**
     * 普通解法的时间复杂度为 O(N)，额外空间复杂度为 O(N)，就是把链表中的所有节点放入一 个额外的数组中，然后统一调整位置的办法。具体过程如下：
     * 1．先遍历一遍链表，为了得到链表的长度，假设长度为 N。
     * <p>
     * 2．生成长度为 N 的 Node 类型的数组 nodeArr，然后遍历一次链表，将节点依次放进 nodeArr 中。
     * 本书在这里不用 LinkedList 或 ArrayList 等 Java 提供的结构，因为一个纯粹的数组结构比较 利于步骤 3 的调整。
     * <p>
     * 3．在 nodeArr 中把小于 pivot 的节点放在左边，把相等的放中间，把大于的放在右边。也 就是改进了快速排序中partition 的调整过程，
     * 即如下代码中的 arrPartition 方法。实现的具体解 释请参看本书“数组类似 partition 的调整”问题，这里不再详述。
     * <p>
     * 4．经过步骤 3 的调整后，nodeArr 是满足题目要求的节点顺序，只要把 nodeArr 中的节点 依次重连起来即可，整个过程结束。
     *
     * @param head  链表头节点
     * @param pivot 切分点
     */
    public static Node listPartition(Node head, int pivot) {
        if (head == null || head.next == null) {
            return head;
        }
        Node cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        //存放链表节点
        Node[] nodeArr = new Node[len];
        cur = head;
        for (int i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }

        arrPartition(nodeArr, pivot);

        for (int i = 0; i < nodeArr.length - 1; i++) {
            nodeArr[i].next = nodeArr[i + 1];
        }
        nodeArr[nodeArr.length - 1].next = null;
        return nodeArr[0];
    }

    //对Node数组排序
    public static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].value == pivot) {
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }

    //交换Node数组中两个位置下标的数据
    public static void swap(Node[] nodeArr, int first, int second) {
        if (first == second) {
            return;
        }
        Node temp = nodeArr[first];
        nodeArr[first] = nodeArr[second];
        nodeArr[second] = temp;
    }

    /**
     * 如果链表长度为 N，时间复杂度请达到 O(N)，额外空间复杂度请达到 O(1)。
     * <p>
     * 将原链表中的所有节点依次划分进三个链表，三个链表分别为 small 代表左部分，equal 代表中间部分，big 代表右部分。
     * 例如，链表 7->9->1->8->5->2->5，pivot=5。在划分之后，small、equal、big 分别为：
     * small：1->2->null equal：5->5->null big：7->9->8->null 2．
     * 将 small、equal 和 big 三个链表重新串起来即可。
     * <p>
     * 整个过程需要特别注意对 null 节点的判断和处理
     */
    public static Node listPartitionHigh(Node head, int pivot) {
        Node smallHead = null;
        Node smallTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node bigHead = null;
        Node bigTail = null;
        Node next; //保存下一个节点

        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = head;
                }
            } else if (head.value == pivot) {
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = head;
                }
            } else {
                if (bigHead == null) {
                    bigHead = head;
                    bigTail = head;
                } else {
                    bigTail.next = head;
                    bigTail = head;
                }
            }
            head = next;
        }

        //小的和相等的连接
        if (smallTail != null) {
            smallTail.next = equalHead;
            equalTail = equalTail == null ? smallTail : equalTail;
        }

        //和大的连接
        if (equalTail != null) {
            equalTail.next = bigHead;
        }

        return smallHead != null ? smallHead : equalHead != null ? equalHead : bigHead;
    }
}

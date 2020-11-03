package linked;

//两个单链表相交的一系列问题
//单链表可能有环，也可能无环。给定两个单链表的头节点 head1 和 head2，这两个链表可能相交，也可能不相交

//本题可以拆分成三个子问题，每个问题都可以作为一道独立的算法题，具体如下。
//问题一：如何判断一个链表是否有环，如果有，则返回第一个进入环的节点，没有则返回 null。
//问题二：如何判断两个无环链表是否相交，相交则返回第一个相交节点，不相交则返回 null。
//问题三：如何判断两个有环链表是否相交，相交则返回第一个相交节点，不相交则返回 null。

//注意：如果一个链表有环，另外一个链表无环，它们是不可能相交的，直接返回 null。
public class ListIntersection {
    /**
     * 问题一：如何判断一个链表是否有环，如果有，则返回第一个进入环的节点，没有则返回 null。
     * 如果一个链表没有环，那么遍历链表一定可以遇到链表的终点；如果链表有环，那么遍历 链表就永远在环里转下去了。
     * <p>
     * 如何找到第一个入环节点，具体过程如下：
     * 1. 设置一个慢指针 slow 和一个快指针 fast。在开始时，slow 和 fast 都指向链表的头节点 head。
     * 然后 slow 每次移动一步，fast 每次移动两步，在链表中遍历起来。
     * <p>
     * 2. 如果链表无环，那么 fast 指针在移动过程中一定先遇到终点，一旦 fast 到达终点，说明 链表是没有环的，
     * 接返回 null，表示该链表无环，当然也没有第一个入环的节点。
     * <p>
     * 3. 如果链表有环，那么 fast 指针和 slow 指针一定会在环中的某个位置相遇，当 fast 和 slow 相遇时，
     * fast 指针重新回到 head 的位置，slow 指针不动。接下来，fast 指针从每次移动两步改 为每次移动一步，
     * slow 指针依然每次移动一步，然后继续遍历。
     * <p>
     * 4．fast 指针和 slow 指针一定会再次相遇，并且在第一个入环的节点处相遇。
     */
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 问题二：如何判断两个无环链表是否相交，相交则返回第一个相交节点，不相交则返回 null。
     * <p>
     * 如果两个无环链表相交，那么从相交节点开始，一直到两个链表终止的这一段，是两个链 表共享的。解决问题二的具体过程如下：
     * <p>
     * 1．链表 1 从头节点开始，走到最后一个节点（不是结束），统计链表 1 的长度记为 len1， 同时记录链表 1 的最后一个节点记为 end1。
     * <p>
     * 2．链表 2 从头节点开始，走到最后一个节点（不是结束），统计链表 2 的长度记为 len2， 同时记录链表 2 的最后一个节点记为 end2。
     * <p>
     * 3．如果 end1!=end2，说明两个链表不相交，返回 null 即可；如果 end==end2，说明两个链 表相交，进入步骤 4 来找寻第一个相交节点。
     * <p>
     * 4．如果链表 1 比较长，链表 1 就先走 len1−len2 步；如果链表 2 比较长，链表 2 就先走 len2−len1 步。
     * 然后两个链表一起走，一起走的过程中，两个链表第一次走到一起的那个节点就 是第一个相交的节点。
     */
    public static Node noLoop(Node headFirst, Node headSecond) {
        if (headFirst == null || headSecond == null) {
            return null;
        }
        Node first = headFirst;
        Node second = headSecond;
        int n = 0;
        while (first.next != null) {
            n++;
            first = first.next;
        }
        while (second.next != null) {
            n--;
            second = second.next;
        }

        //判断尾节点
        if (first != second) {
            return null;
        }

        first = n > 0 ? headFirst : headSecond;
        second = first == headFirst ? headSecond : headFirst;
        n = Math.abs(n);

        while (n != 0) {
            first = first.next;
            n--;
        }
        while (first != second) {
            first = first.next;
            second = second.next;
        }

        return first;
    }

    /**
     * 问题三：如何判断两个有环链表是否相交，相交则返回第一个相交节点，不相交则返回 null。
     * 考虑问题三的时候，我们已经得到了两个链表各自的第一个入环节点，
     * 假设链表 1 的第一 个入环节点记为 loop1，链表 2 的第一个入环节点记为 loop2。
     * <p>
     * 1. 如果 loop1==loop2，这种情况下，我们只要考虑链表 1 从头开始到 loop1 这一段与链表 2 从头开始到 loop2 这 一段，
     * 在那里第一次相交即可，而不用考虑进环该怎么处理，这就与问题二类似，只不过问题 二是把 null 作为一个链表的终点，
     * 而这里是把 loop1(loop2)作为链表的终点。但是判断的主要过 程是相同的。
     * <p>
     * 2.让链表 1 从 loop1 出发，因为 loop1 和之后的所有节点都在环上，所以将来一定能回到 loop1。
     * 如果回到 loop1 之前并没有遇到 loop2，说明两个链表不相交，直接返回 null；如果回到 loop1 之前遇到了 loop2，说明两个链表的相交。
     * 因为 loop1 和 loop2 都在两条链表上，只不过 loop1 是离链表 1 较近的节点，
     * loop2 是离链表 2 较近的节点。所以，此时返回 loop1 或 loop2 都可以
     */
    public static Node bothLoop(Node headFirst, Node loopFirst, Node headSecond, Node loopSecond) {
        Node first;
        Node second;
        if (loopFirst == loopSecond) {
            first = headFirst;
            second = headSecond;
            int n = 0;
            while (first.next != null) {
                n++;
                first = first.next;
            }
            while (second.next != null) {
                n--;
                second = second.next;
            }
            first = n > 0 ? headFirst : headSecond;
            second = first == headFirst ? headSecond : headFirst;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                first = first.next;
            }
            while (first != second) {
                first = first.next;
                second = second.next;
            }
            return first;
        } else {
            first = loopFirst.next;
            while (first != loopFirst) {
                if (first == loopSecond) {
                    return first;
                } else {
                    first = first.next;
                }
            }
            return null;
        }
    }

    //入口函数
    public static Node getIntersectNode(Node headFirst, Node headSecond) {
        if (headFirst == null || headSecond == null) {
            return null;
        }
        Node loopFirst = getLoopNode(headFirst);
        Node loopSecond = getLoopNode(headSecond);
        if (loopFirst != null && loopSecond != null) {
            return bothLoop(headFirst, loopFirst, headSecond, loopSecond);
        }
        if (loopFirst == null && loopSecond == null) {
            return noLoop(headFirst, headSecond);
        }
        return null;
    }
}


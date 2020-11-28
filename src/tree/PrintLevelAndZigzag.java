package tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class PrintLevelAndZigzag {
    public static void printLevel(Node head) {
        if (head == null) {
            return;
        }
        final Queue<Node> queue = new LinkedList<>();
        int level = 1;
        Node last = head;
        Node nLast = null;
        queue.offer(head);
        System.out.print("Level " + (level++) + ":");
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.print(head.value + " ");
            if (head.left != null) {
                queue.offer(head.left);
                nLast = head.left;
            }
            if (head.right != null) {
                queue.offer(head.right);
                nLast = head.right;
            }
            if (head == last && !queue.isEmpty()) {
                System.out.print("\nLevel " + (level++) + " : ");
                last = nLast;
            }
        }
        System.out.println();
    }

    //原则 1：如果是从左到右的过程。那么一律从 dq 的头部弹出节点，如果弹出的节点没有孩 子节点，当然不用放入任何节点到 dq 中；如果当前节点有孩子节点，先让左孩子节点从尾部进 入 dq，再让右孩子节点从尾部进入 dq。
    //
    //根据原则 1，先从 dq 头部弹出节点 1 并打印，然后先让节点 2 从 dq 尾部进入，再让节点 3 从 dq 尾部进入，如图 3-30 所示。
    //
    //原则 2：如果是从右到左的过程，那么一律从 dq 的尾部弹出节点，如果弹出的节点没有孩 子节点，当然不用放入任何节点到 dq 中；如果当前节点有孩子节点，先让右孩子从头部进入
    //dq，再让左孩子节点从头部进入 dq。
    public static void printByZigzag(Node head) {
        if (head == null) return;
        final Deque<Node> deque = new LinkedList<>();
        int level = 1;
        boolean lr = true;
        Node last = head;
        Node nLast = null;
        deque.offerFirst(head);
        printLevelAndOrientation(level++, lr);
        while (!deque.isEmpty()) {
            //如果是从左到右的过程。那么一律从 dq 的头部弹出节点，如果弹出的节点没有孩 子节点，当然不用放入任何节点到 dq 中；
            //如果当前节点有孩子节点，先让左孩子节点从尾部进 入 dq，再让右孩子节点从尾部进入 dq。
            if (lr) {
                head = deque.pollFirst();
                if (head.left != null) {
                    nLast = nLast == null ? head.left : nLast;
                    deque.offerLast(head.left);
                }
                if (head.right != null) {
                    nLast = nLast == null ? head.right : nLast;
                    deque.offerFirst(head.right);
                }
            } else {
                //如果是从右到左的过程，那么一律从 dq 的尾部弹出节点，如果弹出的节点没有孩 子节点，当然不用放入任何节点到 dq 中；
                //如果当前节点有孩子节点，先让右孩子从头部进入dq，再让左孩子节点从头部进入 dq。
                head = deque.pollLast();
                if (head.right != null) {
                    nLast = nLast == null ? head.right : nLast;
                    deque.offerFirst(nLast.right);
                }
                if (head.left != null) {
                    nLast = nLast == null ? head.left : nLast;
                    deque.offerFirst(head.left);
                }
            }
            System.out.println(head.value + " ");
            if (head == last && !deque.isEmpty()) {
                lr = !lr;
                last = nLast;
                nLast = null;
                System.out.println();

                printLevelAndOrientation(++level, lr);
            }
        }
        System.out.println();
    }

    private static void printLevelAndOrientation(int level, boolean lt) {
        System.out.print("Level " + level + " from ");
        System.out.print(lt ? "left to right: " : "right to left: ");
    }

}

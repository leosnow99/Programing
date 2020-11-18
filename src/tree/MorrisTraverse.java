package tree;

/**
 * Morris 遍历的实质就是避免用栈结构，而是让下层到上层有指针，
 * 具体是通过让底层节点 指向 null 的空闲指针指回上层的某个节点，从而完成下层到上层的移动。
 * 我们知道，二叉树上 的很多节点都有大量的空闲指针，比如，某些节点没有右孩子节点，那么这个节点的 right 指针 就指向 null，
 * 我们称为空闲状态，Morris 遍历正是利用了这些空闲指针。
 */
public class MorrisTraverse {
    //假设当前节点为 cur，初始时 cur 就是整棵树的头节点，根据以下标准让 cur 移动：
    //
    //1．如果 cur 为 null，则过程停止，否则继续下面的过程。
    //
    //2．如果 cur 没有左子树，则让 cur 向右移动，即令 cur = cur.right。
    //
    //3．如果 cur 有左子树，则找到 cur 左子树上最右的节点，记为 mostRight。
    //
    //1）如果 mostRight 的 right 指针指向 null，则令 mostRight.right = cur，
    //  也就是让 mostRight 的 right 指针指向当前节点，然后让 cur 向左移动，即令 cur = cur.left。
    //
    //2）如果 mostRight 的 right 指针指向 cur，则令 mostRight.right = null，
    //也就是让 mostRight 的 right 指针指向 null，然后让 cur 向右移动，即令 cur = cur.right。
    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            mostRight = cur.left;
            //如果当前有左子树
            if (mostRight != null) {
                //找到cur左子树上最右的节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 从上面的 while 里出来后，mostRight 就是 cur 左子树上最右的节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            // cur 如果没有左子树，cur 向右移动 // 或者 cur 左子树上最右节点的右指针是指向 cur 的，cur 向右移动
            cur = cur.right;
        }
    }

    //根据 Morris 遍历，加工出先序遍历。
    //  1．对于 cur 只能到达一次的节点（无左子树的节点），cur 到达时直接打印。
    //  2．对于 cur 可以到达两次的节点（有左子树的节点），cur 第一次到达时打印，第二次到达 时不打印。
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            mostRight = cur.left;
            //如果当前有左子树
            if (mostRight != null) {
                //找到cur左子树上最右的节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 从上面的 while 里出来后，mostRight 就是 cur 左子树上最右的节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.println(cur.value + " ");
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.println(cur.value + " ");
            }
            // cur 如果没有左子树，cur 向右移动 // 或者 cur 左子树上最右节点的右指针是指向 cur 的，cur 向右移动
            cur = cur.right;
        }
        System.out.println();
    }

    //根据 Morris 遍历，加工出中序遍历。
    //  1．对于 cur 只能到达一次的节点（无左子树的节点），cur 到达时直接打印。
    //  2．对于 cur 可以到达两次的节点（有左子树的节点），cur 第一次到达时不打印，第二次到 达时打印。
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            mostRight = cur.left;
            //如果当前有左子树
            if (mostRight != null) {
                //找到cur左子树上最右的节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 从上面的 while 里出来后，mostRight 就是 cur 左子树上最右的节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.println(cur.value + " ");
            // cur 如果没有左子树，cur 向右移动 // 或者 cur 左子树上最右节点的右指针是指向 cur 的，cur 向右移动
            cur = cur.right;
        }
    }
}

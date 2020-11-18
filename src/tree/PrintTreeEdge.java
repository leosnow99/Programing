package tree;

/**
 * 给定一棵二叉树的头节点 head，按照如下两种标准分别实现二叉树边界节点的逆时针打印。
 * 标准一：
 * 1．头节点为边界节点。
 * 2．叶节点为边界节点。
 * 3．如果节点在其所在的层中是最左的或最右的，那么该节点也是边界节点。
 * <p>
 * 标准二：
 * 1．头节点为边界节点。
 * 2．叶节点为边界节点。
 * 3．树左边界延伸下去的路径为边界节点。
 * 4．树右边界延伸下去的路径为边界节点。
 */
public class PrintTreeEdge {

    //标准一
    public static void printEdgeFirst(Node head) {
        if (head == null) {
            return;
        }
        int height = getHeight(head, 0);
        Node[][] edgeMap = new Node[height][2];
        //获取树的两边节点
        setEdgeMap(head, 0, edgeMap);
        //打印左边界
        for (int i = 0; i < height; i++) {
            System.out.print(edgeMap[i][0].value + " ");
        }
        //打印非左边界与右边界的叶子节点
        printLeadNotInMap(head, 0, edgeMap);
        //打印右边界
        for (int i = height - 1; i >= 0; i--) {
            if (edgeMap[i][0] != edgeMap[i][1]) {
                System.out.print(edgeMap[i][1].value + " ");
            }
        }
        System.out.println();
    }

    public static int getHeight(Node head, int l) {
        if (head == null) {
            return l;
        }
        return Math.max(getHeight(head.left, l + 1), getHeight(head.right, l + 1));

    }

    public static void setEdgeMap(Node h, int l, Node[][] edgeMap) {
        if (h == null) {
            return;
        }
        edgeMap[l][0] = edgeMap[l][0] == null ? h : edgeMap[l][0];
        edgeMap[l][1] = h;
        setEdgeMap(h.left, l + 1, edgeMap);
        setEdgeMap(h.right, l + 1, edgeMap);
    }

    public static void printLeadNotInMap(Node h, int l, Node[][] edgeMap) {
        if (h == null) {
            return;
        }
        if (h.left == null && h.right == null && h != edgeMap[l][0] && h != edgeMap[l][1]) {
            System.out.print(h.value + " ");
        }
        printLeadNotInMap(h.left, l + 1, edgeMap);
        printLeadNotInMap(h.right, l + 1, edgeMap);
    }

    //标准二
    public static void printEdgeSecond(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        if (head.left != null && head.right != null) {
            printLeftEdge(head.left, true);
            printLeftEdge(head.right, true);
        } else {
            printEdgeSecond(head.left == null ? head.right : head.left);
        }
        System.out.println();
    }

    public static void printLeftEdge(Node head, boolean print) {
        if (head == null) {
            return;
        }
        if (print || (head.left == null && head.right == null)) {
            System.out.print(head.value + " ");
        }
        printLeftEdge(head.left, print);
        printLeftEdge(head.right, print && head.left == null);
    }

    public static void printRightEdge(Node head, boolean print) {
        if (head == null) {
            return;
        }
        printRightEdge(head.right, print);
        printRightEdge(head.left, print && head.right == null);
        if (print || (head.left == null && head.right == null)) {
            System.out.print(head.value + " ");
        }
    }
}

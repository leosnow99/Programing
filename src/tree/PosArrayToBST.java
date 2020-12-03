package tree;

//给定一个整型数组 arr，已知其中没有重复值，判断 arr 是否可能是节点值类型为整型的搜 索二叉树后序遍历的结果。

//进阶问题：如果整型数组 arr 中没有重复值，且已知是一棵搜索二叉树的后序遍历结果， 通过数组 arr 重构二叉树。

//二叉树的后序遍历为先左、再右、最后根的顺序，所以，如果一个数组是 二叉树后序遍历的结果，那么头节点的值一定会是数组的最后一个元素。
//根据搜索二叉树的性 质，比后序数组最后一个元素值小的数组会在数组的左边，比数组最后一个元素值大的数组会 在数组的右边。
public class PosArrayToBST {
    public static boolean isPosArray(int[] arr) {
        if (arr == null || arr.length < 1) {
            return false;
        }
        return isPost(arr, 0, arr.length - 1);

    }

    public static boolean isPost(int[] arr, int start, int end) {
        if (start == end) {
            return true;
        }
        int less = -1;
        int more = end;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[end]) {
                less = i;
            } else  {
                more = more == end ? i : more;
            }
        }
        if (less == -1 || more == end) {
            return isPost(arr, start, end - 1);
        }
        if (less != more - 1) {
            return false;
        }
        return isPost(arr, start, less) && isPost(arr, more, end - 1);
    }

    public static Node posArrayToBST(int[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static Node process(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        final Node head = new Node(arr[end]);

        int less = -1;
        int more = end;

        for (int i = start; i < end; i++) {
            if (arr[i] > arr[end]) {
                less = i;
            } else {
                more = more == end ? i : more;
            }
        }

        head.left = process(arr, start, less);
        head.right = process(arr, more, end - 1);
        return head;
    }

}

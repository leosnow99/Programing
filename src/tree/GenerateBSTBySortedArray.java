package tree;

//通过有序数组生成平衡搜索二叉树
public class GenerateBSTBySortedArray {
    public static Node generateBST(int[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        return generate(arr, 0, arr.length - 1);
    }

    public static Node generate(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (start - end) >> 2;
        Node head = new Node(arr[mid]);
        head.left = generate(arr, start, mid - 1);
        head.right = generate(arr, mid + 1, end);
        return head;
    }
}

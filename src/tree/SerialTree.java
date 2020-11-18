package tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//二叉树的序列化和反序列化
public class SerialTree {
    //方法一：通过先序遍历实现序列化和反序列化。
    //首先假设序列化的结果字符串为 str，初始时 str=""。
    //先 序遍历二叉树，如果遇到 null 节点，就在 str 的末尾加上“#!”，“#”表示这个节点为空，
    //节点 值不存在，“!”表示一个值的结束；如果遇到不为空的节点，假设节点值为 3，就在 str 的末尾 加上“3!”
    public static String serialTreeFirst(Node head) {
        if (head == null) {
            return "#!";
        }
        return head.value + "!" +
                serialTreeFirst(head.left) +
                serialTreeFirst(head.right);
    }

    //反序列化
    public static Node reconByPreString(String preStr) {
        final String[] values = preStr.split("!");
        Queue<String> queue = new LinkedList<>();
        Arrays.stream(values).forEach(queue::offer);
        return reconPreOrder(queue);
    }

    public static Node reconPreOrder(Queue<String> queue) {
        final String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        final Node head = new Node(Integer.parseInt(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

    //通过层遍历实现序列化和反序列化。
    public static String serialByLevel(Node head) {
        if (head == null) {
            return "#!";
        }
        StringBuilder res = new StringBuilder(head.value + "!");
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.left != null) {
                res.append(head.left.value).append("!");
                queue.offer(head.left);
            } else {
                res.append("#!");
            }

            if (head.right != null) {
                res.append(head.right.value).append("!");
            } else {
                res.append("#!");
            }
        }
        return res.toString();
    }

    //反序列化
    public static Node reconByLevelString(String levelStr) {
        final String[] values = levelStr.split("!");
        int index = 0;
        Node head = generateNode(values[index++]);

        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.offer(head);
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            node.left = generateNode(values[index++]);
            node.right = generateNode(values[index++]);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return head;
    }

    public static Node generateNode(String value) {
        if (value.equals("#")) {
            return null;
        }
        return new Node(Integer.parseInt(value));
    }
}

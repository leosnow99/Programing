package linked;

//链表节点值类型为 int 型，给定一个链表中的节点 node，但不给定整个链表的头节点。如 何在链表中删除 node？请实现这个函数，并分析这样做会出现哪些问题。
//这样的删除方式无法删除最后一个节点。
//这种删除方式在本质上根本就不是删除了 node 节点，而是把 node 节点的值改变， 然后删除 node 的下一个节点，在实际的工程中可能会带来很大问题
public class RemoveNode {
    public static void removeNodeWired(Node node) {
        if (node == null) {
            return;
        }
        Node next = node.next;
        if (next == null) {
            throw new RuntimeException("can not remove last node.");
        }
        node.value = next.value;
        node.next = next.next;
    }
}

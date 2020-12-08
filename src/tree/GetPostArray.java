package tree;

import java.util.HashMap;

/**
 * 已知一棵二叉树所有的节点值都不同，给定这棵树正确的先序和中序数组，不要重建整棵 树，而是通过这两个数组直接生成正确的后序数组。
 */
public class GetPostArray {

    public static int[] getPostArray(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        int len = pre.length;
        int[] pos = new int[len];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(in[i], i);
        }
        setPos(pre, 0, len - 1, in, 0, len - 1, pos, len - 1, map);
        return pos;

    }

    private static int setPos(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd, int[] pos, int posIndex, HashMap<Integer, Integer> map) {
        if (preStart > preEnd) {
            return posIndex;
        }
        pos[posIndex--] = pre[preStart];
        final Integer index = map.get(pre[preStart]);
        posIndex = setPos(pre, preStart - inEnd + index + 1 , preEnd, in, index + 1, inEnd, pos, posIndex, map);

        return setPos(pre, preStart + 1, preStart + index - inStart, in, inStart, index - 1, pos, posIndex, map);
    }
}

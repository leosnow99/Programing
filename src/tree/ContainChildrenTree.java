package tree;

//给定彼此独立的两棵树头节点分别为 t1 和 t2，判断 t1 中是否有与 t2 树拓扑结构完全相同 的子树。
public class ContainChildrenTree {
    //首先把 t1 树和 t2 树按照先序遍历的方式 序列化，以题目的例子来说， t1 树序列化后的结果为“1!2!4!#!8!#!#!5!9!#!#!#!3!6!#!#!7!#!#!”，记为 t1Str。
    //t2 树序列化后的 结果为“2!4!#!8!#!#!5!9!#!#!#!”，记为 t2Str。接下来，只要验证 t2Str 是否是 t1Str 的子串即可，
    //这个用 KMP 算法可以在线性时间内解决。因此，t1 序列化的过程为 O(N)，t2 序列化的过程为 O(M)，KMP 解决 t1Str 和 t2Str 的匹配问题 O(M+N)，
    //所以时间复杂度为 O(M+N)。
    public static boolean isSubTree(Node headPre, Node headSecond) {
        final String headPreSer = serialByPre(headPre);
        final String headSecSer = serialByPre(headSecond);
        return getIndexOf(headPreSer, headSecSer) != -1;
    }

    public static String serialByPre(Node head) {
        if (head == null) {
            return "#!";
        }
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    //KMP
    public static int getIndexOf(String source, String target) {
        if (source == null || target == null || target.length() < 1 || source.length() < target.length()) {
            return -1;
        }
        final char[] sChars = source.toCharArray();
        final char[] tChars = target.toCharArray();

        int sIndex = 0;
        int mIndex = 0;
        final int[] nextArray = getNextArray(tChars);
        while (sIndex < source.length() && mIndex < target.length()) {
            if (sChars[sIndex] == tChars[mIndex]) {
                sIndex++;
                mIndex++;
            } else if (nextArray[mIndex] == -1) {
                sIndex++;
            } else {
                mIndex = nextArray[mIndex];
            }
        }
        return mIndex == target.length() ? sIndex - mIndex : -1;
    }


    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }


}

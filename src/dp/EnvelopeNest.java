package dp;


import java.util.Arrays;
import java.util.Comparator;

/**
 *<b>信封嵌套问题</b>
 * <p>给定一个 N 行 2 列的二维数组，每一个小数组的两个值分别代表一个信封的长和宽。如果 信封 A 的长和宽都小于信封 B，那么信封 A 可以放在信封 B 里，请返回信封最多嵌套多少层。</p>
 * <p/>
 * @author leosnow
 */
public class EnvelopeNest {
    public static class Envelope {
        public int len;
        public int wid;

        public Envelope(int len, int wid) {
            this.len = len;
            this.wid = wid;
        }
    }

    public static class EnvelopeComparator implements Comparator<Envelope> {
        @Override
        public  int compare(Envelope o1, Envelope o2) {
            return o1.len != o2.len ? o1.len - o2.len : o1.wid - o2.wid;
        }

    }

    /**
     * 首先把 N 个长度为 2 的小数组变成信封数组。然后对信封数组排序，排序的策略为，按照 长度从小到大排序，长度相等的信封之间按照宽度从大到小排序
     * @param matrix 信封数组信息
     * @return 信封数组排序结果
     */
    public static Envelope[] getSortedEnvelopes(int[][] matrix) {
        Envelope[] res = new Envelope[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            res[i] = new Envelope(matrix[i][0], matrix[i][1]);
        }

        Arrays.sort(res, new EnvelopeComparator());
        return res;
    }

    /**
     * 我们假设有一个信封 X，处在这个排序之后数组中的某个位置，长度为 X len，宽度为 X wid。 我们要求出必须以 X 作为最外面信封的情况下，最多套几层。那么信封 X 之后的信封一定不能 放在 X 里，因为之后信封的长度都大于或等于 Xlen。分析一下信封 X 之前的信封，因为排序策 略是按照长度从小到大排序的，所以 X 之前的信封长度要么小于 X，要么等于 X：
     *
     * 1）如果 X 之前的信封长度小于 X 的长度。那么只要之前信封的宽度小于 X 的宽度，一定可 以放在 X 内。所以在宽度组成的数组中，X 的宽度如果作为最后一个数，求宽度数组的最长递 增子序列即可。
     *
     * 2）如果 X 之前的信封长度等于 X 的长度。因为长度相等的信封之间按照宽度从大到小排序， 所以这些信封的宽度一定大于或等于 X 的宽度，这样就不可能是 X 的宽度作为最后一个数的情 况下，宽度数组的最长递增子序列的一部分。
     * @param matrix 信封信息
     * @return 最多嵌套信封个数
     */
    public static int maxEnvelopes(int[][] matrix) {
        final Envelope[] envelopes = getSortedEnvelopes(matrix);
        int[] ends = new int[matrix.length];
        ends[0] = envelopes[0].wid;
        int right = 0;
        int l;
        int r;
        int mid;

        for (int i = 1; i < envelopes.length; i++) {
            l = 0;
            r = right;
            while (l < r) {
                mid = l - (l - r) >> 2;
                if (envelopes[i].wid > ends[mid]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            right = Math.max(l, right);
            ends[l] = envelopes[i].wid;
        }

        return right + 1;
    }


}

package dp;

/**
 * <b>汉诺塔</b>
 * <p>给定一个整数 n，代表汉诺塔游戏中从小到大放置的 n 个圆盘，假设开始时所有的圆盘都 放在左边的柱子上，想按照汉诺塔游戏的要求把所有的圆盘都移到右边的柱子上。</p>
 * <p/>
 * <b>进阶问题</b>
 * <p>给定一个整型数组 arr，其中只含有 1、2 和 3，代表所有圆盘目前的状态，1 代 表左柱，2 代表中柱，3 代表右柱，arr[i]的值代表第 i+1 个圆盘的位置。
 * 比如，arr=[3,3,2,1]，代 表第 1 个圆盘在右柱上、第 2 个圆盘在右柱上、第 3 个圆盘在中柱上、第 4 个圆盘在左柱上。
 * 如果 arr 代表的状态是最优移动轨迹过程中出现的状态，返回 arr 这种状态是最优移动轨迹中的 第几个状态。
 * 如果 arr 代表的状态不是最优移动轨迹过程中出现的状态，则返回-1。</p>
 *
 * @author leosnow
 */
public class Hanoi {
    /**
     * 假设有 from 柱子、mid 柱子和 to 柱子，都从 from 的圆盘 1~i 完全移动到 to，最 优过程为：
     * <ul>
     *     <li>步骤 1：为圆盘 1~i-1 从 from 移动到 mid。 </li>
     *     <li>步骤 2：为单独把圆盘 i 从 from 移动到 to。</li></li>
     *     <li>步骤 3：为把圆盘 1~i-1 从 mid 移动到 to。如果圆盘只有 1 个，直接把这个圆盘从 from 移 动到 to</li>
     * </ul>
     *
     * @param N 圆盘个数
     */
    public static void hanoi(int N) {
        if (N > 0) {
            func(N, "left", "mid", "right");
        }
    }

    public static void func(int N, String from, String mid, String to) {
        if (N == 1) {
            System.out.println("move from " + from + " to " + to);
        } else {
            func(N - 1, from, to, mid);
            func(1, from, mid, to);
            func(N - 1, mid, from, to);
        }
    }

    /**
     * 对于数组 arr 来说，arr[N-1]表示最大圆盘 N 在哪个柱子上，情况有以下三种。
     * <ul>
     *     <li>圆盘 N 在左柱上，说明步骤 1 或者没有完成，或者已经完成，需要考查圆盘 1~N-1 的状况。</li>
     *     <li>圆盘 N 在右柱上，说明步骤 1 已经完成，起码走完了 2N-1-1 步。步骤 2 也已经完成， 起码又走完了 1 步，所以当前状况起码是最优步骤的 2 N-1 步，剩下的步骤怎么确定还 得继续考查圆盘 1~N-1 的状况。</li>
     *     <li>圆盘 N 在中柱上，这是不可能的，最优步骤中不可能让圆盘 N 处在中柱上，直接返 回-1。</li>
     * </ul>
     * 所以整个过程可以总结为：对圆盘 1~i 来说，如果目标为从 from 到 to，那么情况有三种：
     * <ul>
     *     <li>圆盘 i 在 from 上，需要继续考查圆盘 1~i-1 的状况，圆盘 1~i-1 的目标为从 from 到 mid。</li>
     *     <li>圆盘 i 在 to 上，说明起码走完了 2 i-1步，剩下的步骤怎么确定还得继续考查圆盘 1~i-1 的状况，圆盘 1~i-1 的目标为从 mid 到 to。</li>
     *     <li>圆盘 i 在 mid 上，直接返回-1。</li>
     * </ul>
     *
     * @param arr 圆盘数组
     * @return 移动最少步数
     */
    public static int step(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        return process(arr, arr.length - 1, 1, 2, 3);
    }

    public static int process(int[] arr, int i, int from, int mid, int to) {
        if (i == -1) {
            return 0;
        }
        if (arr[i] != from && arr[i] != to) {
            return -1;
        }
        if (arr[i] == from) {
            return process(arr, i - 1, from, to, mid);
        } else {
            int rest = process(arr, i - 1, mid, from, to);
            if (rest == -1) {
                return -1;
            }
            return (1 << 2) + rest;
        }
    }

    public static int stepHigh(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int from = 1;
        int mid = 2;
        int to = 3;
        int i = arr.length - 1;
        int res = 0;
        int temp;
        while (i > 0) {
            if (arr[i] != from && arr[i] != to) {
                return -1;
            }
            if (arr[i] == to) {
                res += 1 << i;
                temp = from;
                from = mid;
            } else {
                temp = to;
                to = mid;
            }
            mid = temp;
            i--;
        }
        return res;
    }

}

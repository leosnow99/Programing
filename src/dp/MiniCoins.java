package dp;

/**
 * 给定数组 arr，arr 中所有的值都为正数且不重复。每个值代表一种面值的货币，每种面值 的货币可以使用任意张，
 * 再给定一个整数 aim，代表要找的钱数，求组成 aim 的最少货币数。
 *
 * @author leosnow
 */
public class MiniCoins {
    public static int miniCoins(int[] arr, int num) {
        if (num <= 0 || arr == null || arr.length == 0 || arr[0] > num) {
            return -1;
        }
        return process(arr, 0, num);
    }

    public static int process(int[] arr, int index, int rest) {
        // base case：
        //已经没有面值能够考虑了
        //如果此时剩余的钱为 0，返回 0 张
        //如果此时剩余的钱不是 0，返回-1
        if (index == arr.length) {
            return rest == 0 ? 0 : -1;
        }
        // 最少张数，初始时为-1，因为还没找到有效解
        int result = -1;
        // 依次尝试使用当前面值(arr[i])0 张、1 张、k 张，但不能超过 rest
        for (int k = 0; k * arr[index] <= rest; k++) {
            // 使用了 k 张 arr[i]，剩下的钱为 rest - k * arr[i]
            //交给剩下的面值去搞定(arr[i+1..N-1])
            int next = process(arr, index + 1, rest - k * arr[index]);
            if (next != -1) { // 说明这个后续过程有效
                result = result == -1 ? next : Math.min(result, next + k);
            }
        }
        return result;
    }

    /**
     * <p>
     *     尝试过程是无后效性的。上面的尝试其实明显是无后效性的，但是为了方便理解， 我们还是举个例子，arr = {5,2,3,1}，aim=100，
     *     那么 process(arr,0,100)的返回值就是最终答案。如 果使用 2 张 5 元，0 张 2 元，那么后续的过程是 process(arr,2,90)；
     *     但如果使用 0 张 5 元，5 张 2 元，那么后续的过程还是 process(arr,2,90)。这个状态的返回值肯定是一样的，说明一个状态最
     *     终的返回值与怎么达到这个状态的过程无关。
     * </p>
     *
     * <p>
     *    可变参数 i 和 rest 一旦确定，返回值就确定了。
     * </p>
     *
     * <p>
     *     如果可变参数 i 和 rest 组合的所有情况组成一张二维表，这张表一定可以装下所有的返 回值。i 的含义是 arr 中的位置，
     *     又因为 process 中允许 i 来到 arr 的终止位置，所以 i 的范围是[0,N]。 rest 代表剩余的钱数，剩余的钱不可能大于 aim，
     *     所以 rest 的范围是[0,aim]。所以这张二维表是 一个 N 行 aim 列的表，记为 dp[][]。
     * </p>
     *
     * <p>
     *     最终状态是 process(arr,0,aim)，也就是 dp[0][aim]的值，位于 dp 表 0 行最后一列。
     * </p>
     * @param arr 可以兑换的面额
     * @param num 兑换面额
     * @return 最小兑换额度
     */
    public static int miniCoinsHigh(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) {
            return -1;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][num + 1];
        // 设置最后一排的值，除 dp[N][0]为 0 外，其他都是-1
        for (int i = 0; i < num; i++) {
            dp[N][i] = -1;
        }

        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= num; rest++) {
                dp[i][rest] = -1;
                if (dp[i + 1][rest] != -1) {
                    dp[i][rest] = dp[i + 1][rest];
                }
                // 如果左边的位置不越界且有效
                if (rest - arr[i] >= 0 && dp[i][rest - arr[i]] != -1) {
                    // 如果之前下面的值无效
                    if (dp[i + 1][rest] == -1) {
                        dp[i][rest] = dp[i][rest - arr[i]] + 1;
                    } else {
                        // 说明下面和左边的值都有效，取最小的
                        dp[i][rest] = Math.min(dp[i][rest - arr[i]] + 1, dp[i + 1][rest]);
                    }
                }
            }
        }
        return dp[0][num];
    }
}

package dp;

/**
 * 给定数组 arr，arr 中所有的值都为正数且不重复。每个值代表一种面值的货币，每种面值 的货币可以使用任意张，
 * 再给定一个整数 aim，代表要找的钱数，求换钱有多少种方法。
 *
 * @author leosnow
 */
public class ExchangeMoney {

    /**
     * 暴力解法
     *
     * @param arr 可换数组
     * @param aim 目标
     * @return 换钱方法数
     */
    public static int changeMoney(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 1) {
            return 0;
        }

        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                res += process(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }

    /**
     * 记忆化搜索
     *
     * <p>我们可以事先准备好一个 map，每计算完 一个递归过程，都将结果记录到 map 中。当下次进行同样的递归过程之前，
     * 先在 map 中查询这 个递归过程是否已经计算过，如果已经计算过，就把值拿出来直接用，如果没计算过，需要再 进入递归过程。</p>
     *
     * @param arr 可换数组
     * @param aim 目标
     * @return 换钱方法数
     */
    public static int changeMoneyHigh(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 1) {
            return 0;
        }

        int[][] map = new int[arr.length + 1][aim + 1];
        return processHigh(arr, 0, aim, map);
    }

    public static int processHigh(int[] arr, int index, int aim, int[][] map) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            int mapValue;
            for (int i = 1; arr[index] * i < aim; i++) {
                mapValue = map[index + 1][aim - arr[index] * i];
                if (mapValue != 0) {
                    res += mapValue == -1 ? 0 : mapValue;
                } else {
                    res += processHigh(arr, index + 1, aim - arr[index] * i, map);
                }

            }
        }
        map[index][aim] = res == 0 ? -1 : res;
        return res;
    }

    public static int exchangeMoneyDp(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 1) {
            return 0;
        }

        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i * arr[0] <= aim; i++) {
            dp[0][i * arr[0]] = i;
        }

        int num;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                num = 0;
                for (int k = 0; j - k * arr[i] > 0; k++) {
                    num += dp[i - 1][j - k * arr[i]];
                }
                dp[i][j] = num;
            }
        }

        return dp[arr.length - 1][aim];
    }

    /**
     * 时间复杂度也减小至 O(N×aim)
     *
     * @param arr 可换数组
     * @param aim 目标
     * @return 换钱方法数
     */
    public static int exchangeMoneyDpHigh(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 1) {
            return 0;
        }

        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i * arr[0] <= aim; i++) {
            dp[0][i * arr[0]] = i;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }
        return dp[arr.length - 1][aim];
    }

    /**
     * 空间压缩
     * 最优解，是时间复杂度为 O(N×aim)、额外空间复杂度 O(aim)的方法。
     *
     * @param arr 可换数组
     * @param aim 目标
     * @return 换钱方法数
     */
    public static int exchangeMoneyDpHighZip(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 1) {
            return 0;
        }

        int[] dp = new int[aim + 1];

        for (int i = 0; i * arr[0] <= aim; i++) {
            dp[i * arr[0]] = i;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j <= aim; j++) {
                dp[j] += j - arr[i] >= 0 ? dp[j - arr[i]] : 0;
            }
        }
        return dp[aim];
    }

}

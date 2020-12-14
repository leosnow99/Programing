package dp;

/**
 * 给定一个数组 arr，代表一排有分数的气球。每打爆一个气球都能获得分数，假设打爆气球 的分数为 X，获得分数的规则如下：
 * <ul>
 *     <li>如果被打爆气球的左边有没被打爆的气球，找到离被打爆气球最近的气球，假设分数为 L；如果被打爆气球的右边有没被打爆的气球，找到离被打爆气球最近的气球，假设分数为 R。 获得分数为 L×X×R。</li>
 *     <li>如果被打爆气球的左边有没被打爆的气球，找到离被打爆气球最近的气球，假设分数为 L；如果被打爆气球的右边所有气球都已经被打爆。获得分数为 L×X</li>
 *     <li>如果被打爆气球的左边和右边所有的气球都已经被打爆。获得分数为 X</li>
 * </ul>
 * <p>目标是打爆所有气球，获得每次打爆的分数。通过选择打爆气球的顺序，可以得到不同的 总分，请返回能获得的最大分数。</p>
 *
 * @author leosnow
 */
public class BlowBalloon {

    // 打爆 arr[L..R]范围上的所有气球，返回最大的分数
    //假设 arr[L-1]和 arr[R+1]一定没有被打爆
    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            // 如果 arr[L..R]范围上只有一个气球，直接打爆即可
            return arr[L - 1] * arr[L] * arr[R + 1];
        }

        // 最后打爆 arr[L]的方案与最后打爆 arr[R]的方案，先比较一下
        int max = Math.max(arr[L - 1] * arr[L] * process(arr, L + 1, R) * arr[R + 1],
                arr[L - 1] * process(arr, L, R - 1) * arr[R] * arr[R + 1]);

        for (int i = L + 1; i < R; i++) {
            max = Math.max(max, arr[L - 1] * process(arr, L, i - 1) * arr[i] * process(arr, i + 1, R) * arr[R + 1]);
        }

        return max;
    }

    /**
     * 暴力递归
     *
     * @param arr 气球分数数组
     * @return 能获得的最大分数
     */
    public static int maxCoins(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int len = arr.length;
        //为了保证L， R位置不为空， 补1
        int[] help = new int[len + 2];
        help[0] = 1;
        help[len + 1] = 1;
        System.arraycopy(arr, 0, help, 1, arr.length);
        return process(help, 1, len);
    }

    public static int maxCoinsDp(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int len = arr.length;
        //为了保证L， R位置不为空， 补1
        int[] help = new int[len + 2];
        help[0] = 1;
        help[len + 1] = 1;
        System.arraycopy(arr, 0, help, 1, arr.length);

        int[][] dp = new int[len + 2][len + 2];

        for (int i = 1; i <= len; i++) {
            dp[i][i] = arr[i - 1] * arr[i] * arr[i + 1];
        }

        for (int L = len; L > 0; L--) {
            for (int R = L + 1; R <= len; R++) {
                // 求解 dp[L][R]，表示 help[L..R]上打爆所有气球的最大分数
                // 最后打爆 help[L]的方案
                int finalL = help[L - 1] * help[L] * dp[L + 1][R] * help[R + 1];
                // 最后打爆 help[R]的方案
                int finalR = help[L - 1] * help[R] * dp[L][R - 1] * help[R + 1];
                dp[L][R] = Math.max(finalL, finalR);
                // 尝试中间位置的气球最后被打爆的每一种方案
                for (int i = L + 1; i < R; i++) {
                    dp[L][R] = Math.max(dp[L][R], help[L - 1] * help[i] * help[R + 1] + dp[L][i - 1] + dp[i + 1][R]);
                }
            }
        }
        return dp[1][len];
    }

}

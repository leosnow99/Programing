package dp;

/**
 * 假设有排成一行的 N 个位置，记为 1~N，N 一定大于或等于 2。开始时机器人在其中的 M 位置上（M 一定是 1～N 中的一个），
 * 机器人可以往左走或者往右走，如果机器人来到 1 位置， 那么下一步只能往右来到 2 位置；如果机器人来到 N 位置，那么下一步只能往左来到 N-1 位置。
 * 规定机器人必须走 K 步，最终能来到 P 位置（P 也一定是 1～N 中的一个）的方法有多少种。给 定四个参数 N、M、K、P，返回方法数。
 *
 * @author leosnow
 */
public class RobotWalk {
    /**
     * 总共 N 个位置，从 M 点出发，还剩 K 步，返回最终能达到 P 的方法数
     *
     * @param N N个位置
     * @param M 起始点
     * @param K 步数
     * @param P 终点
     * @return 路径数
     */
    public static int ways(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || P < 1 || P > N || K < 1) {
            return 0;
        }
        return walk(N, M, K, P);
    }

    /**
     * @param N    位置为 1 ~ N，固定参数
     * @param cur  当前的位置， 可变参数
     * @param rest 剩余步数，可变参数
     * @param P    最终目标参数，固定参数
     * @return 只能在 1~N 这些位置上移动，当前在 cur 位置，走完 rest 步之后，停在 P 位置的方法
     */
    public static int walk(int N, int cur, int rest, int P) {
        // 如果没有剩余步数了，当前的 cur 位置就是最后的位置
        // 如果最后的位置停在 P 上，那么之前做的移动是有效的
        //如果最后的位置没在 P 上，那么之前做的移动是无效的
        if (rest == 0) {
            return cur == P ? 1 : 0;
        }

        // 如果还有 rest 步要走，而当前的 cur 位置在 1 位置上，那么当前这步只能从 1 走向 2
        // 后续的过程就是来到 2 位置上，还剩 rest-1 步要走
        if (cur == 1) {
            return walk(N, 2, rest - 1, P);
        }
        // 如果还有 rest 步要走，而当前的 cur 位置在 N 位置上，那么当前这步只能从 N 走向 N-1
        // 后续的过程就是来到 N-1 位置上，还剩 rest-1 步要走
        if (cur == N) {
            return walk(N, N - 1, rest - 1, P);
        }

        // 如果还有 rest 步要走，而当前的 cur 位置在中间位置上，那么可以走向左，也可以走向右
        //走向左之后，后续的过程就是，来到 cur-1 位置上，还剩 rest-1 步要走
        //走向右之后，后续的过程就是，来到 cur+1 位置上，还剩 rest-1 步要走
        //走向左、走向右是截然不同的方法，所以总方法数都要算上
        return walk(N, cur - 1, rest - 1, P) + walk(N, cur + 1, rest - 1, P);
    }

    public static int waysHigh(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || P < 1 || P > N || K < 1) {
            return 0;
        }
        int[][] dp = new int[K + 1][N + 1];
        dp[0][P] = 1;
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                if (j == 1) {
                    dp[i][j] = dp[i - 1][j + 1];
                } else if (j == N) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
            }
        }

        return dp[K][M];
    }

    //空间压缩
    public static int waysHighAndZip(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || P < 1 || P > N || K < 1) {
            return 0;
        }
        int[] dp = new int[N + 1];
        dp[P] = 1;
        for (int i = 1; i <= K ; i++) {
            int leftUp = dp[1];
            for (int j = 1; j <= N; j++) {
                int temp = dp[j];
                if (j == 1) {
                    dp[j] = dp[j + 1];
                } else if (j == N) {
                    dp[j] = leftUp;
                } else {
                    dp[j] = leftUp + dp[j + 1];
                }
                leftUp = temp;
            }
        }
        return dp[M];
    }
}

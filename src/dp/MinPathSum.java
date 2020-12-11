package dp;

/**
 * 给定一个矩阵 m，从左上角开始每次只能向右或者向下走，最后到达右下角的位置，路径 上所有的数字累加起来就是路径和，返回所有的路径中最小的路径和。
 *
 * @author leosnow
 */
public class MinPathSum {
    public static int minPathSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < col; i++) {
            dp[0][i] = matrix[0][i] + dp[0][i - 1];
        }

        for (int i = 1; i < row; i++) {
            dp[i][0] = matrix[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < col - 1; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * <b>空间压缩</b>
     * <p>
     * 经典动态规划方法在经过空间压缩之后，时间 复杂度依然是 O(M×N)，但是额外空间复杂度可以从 O(M×N)减小至 O(min{M,N})，也就是不使用 大小为 M×N 的 dp 矩阵，
     * 而仅仅使用大小为 min{M,N}的 arr 数组。
     * </p>
     *
     * <p>
     *     本题压缩空间的方法几乎可以应用到所有需要二维动态规划表的面试题目中，通过一个数 组滚动更新的方式无疑节省了大量的空间。没有优化之前，
     *     取得某个位置动态规划值的过程是 在矩阵中进行两次寻址，优化后，这一过程只需要一次寻址，程序的常数时间也得到了一定程 度的加速。但是空间压缩的方法是有局限性的，
     *     本题如果改成“打印具有最小路径和的路径”， 那么就不能使用空间压缩的方法。如果类似本题这种需要二维表的动态规划题目，最终目的是 想求最优解的具体路径，
     *     往往需要完整的动态规划表，但如果只是想求最优解的值，则可以使 用空间压缩的方法。因为空间压缩的方法是滚动更新的，
     *     会覆盖之前求解的值，让求解轨迹变 得不可回溯。
     * </p>
     *
     *
     * @param matrix 数组矩阵
     * @return 到达最右下角的值
     */
    public static int minPathSumHigh(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        // 行数与列数较大的那个为 more
        int more = Math.max(matrix.length, matrix[0].length);
        // 行数与列数较小的为less
        int less = Math.min(matrix.length, matrix[0].length);
        // 行数是不是大于或等于列数
        boolean rowMore = more == matrix.length;
        //辅助数组
        int[] dp = new int[less];
        for (int i = 0; i < less; i++) {
            dp[i] = dp[i - 1] + (rowMore ? matrix[0][i] : matrix[i][0]);
        }

        for (int i = 1; i < more; i++) {
            dp[0] = dp[0] + (rowMore ? matrix[i][0] : matrix[0][i]);
            for (int j = 1; j < less; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + (rowMore ? matrix[i][j] : matrix[j][i]);
            }
        }
        return dp[less - 1];
    }
}

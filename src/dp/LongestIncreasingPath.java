package dp;

/**
 * @leetcode 329
 * 给定一个整数矩阵，找出最长递增路径的长度。
 * <p>
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 * <p>
 * 将矩阵看成一个有向图，每个单元格对应图中的一个节点，如果相邻的两个单元格的值不相等，则在相邻的两个单元格之间存在一条从较小值指向较大值的有向边。问题转化成在有向图中寻找最长路径。
 * <p>
 * 深度优先搜索是非常直观的方法。从一个单元格开始进行深度优先搜索，即可找到从该单元格开始的最长递增路径。对每个单元格分别进行深度优先搜索之后，即可得到矩阵中的最长递增路径的长度。
 * <p>
 * 但是如果使用朴素深度优先搜索，时间复杂度是指数级，会超出时间限制，因此必须加以优化。
 * <p>
 * 朴素深度优先搜索的时间复杂度过高的原因是进行了大量的重复计算，同一个单元格会被访问多次，每次访问都要重新计算。由于同一个单元格对应的最长递增路径的长度是固定不变的，因此可以使用记忆化的方法进行优化。用矩阵 memo\text{memo}memo 作为缓存矩阵，已经计算过的单元格的结果存储到缓存矩阵中。
 * <p>
 * 使用记忆化深度优先搜索，当访问到一个单元格 (i,j)(i,j)(i,j) 时，如果 memo[i][j]≠0\text{memo}[i][j] \neq 0memo[i][j]​=0，说明该单元格的结果已经计算过，则直接从缓存中读取结果，如果 memo[i][j]=0\text{memo}[i][j]=0memo[i][j]=0，说明该单元格的结果尚未被计算过，则进行搜索，并将计算得到的结果存入缓存中。
 * <p>
 * 遍历完矩阵中的所有单元格之后，即可得到矩阵中的最长递增路径的长度。
 * <p>
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/solution/ju-zhen-zhong-de-zui-chang-di-zeng-lu-jing-by-le-2/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author LEOSNOW
 */
public class LongestIncreasingPath {
	private static final int[][] DIRS = {
			{-1, 0},
			{1, 0},
			{0, -1},
			{0, 1}
	};
	
	public static int longestIncreasingPath(int[][] matrix) {
		if (matrix == null || matrix.length < 1) {
			return 0;
		}
		int rows = matrix.length;
		int columns = matrix[0].length;
		int ans = 0;
		int[][] dp = new int[rows][columns];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				ans = Math.max(ans, process(matrix, i, j, dp));
			}
		}
		
		return ans;
	}
	
	public static int process(int[][] matrix, int row, int column, int[][] dp) {
		if (dp[row][column] != 0) {
			return dp[row][column];
		}
		++dp[row][column];
		for (int[] dir : DIRS) {
			int newRow = row + dir[0];
			int newColumn = column + dir[1];
			if (newRow >= 0 && newRow < matrix.length && newColumn >= 0 && newColumn < matrix[0].length
					&& matrix[newRow][newColumn] > matrix[row][column]) {
				dp[row][column] = Math.max(dp[row][column], process(matrix, newRow, newColumn, dp) + 1);
			}
		}
		
		return dp[row][column];
	}
}

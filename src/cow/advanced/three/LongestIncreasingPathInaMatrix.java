package cow.advanced.three;

/**
 * @author LEOSNOW
 */
public class LongestIncreasingPathInaMatrix {
	public static int longest(int[][] matrix) {
		int max = Integer.MIN_VALUE;
		
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				max = Math.max(max, process(matrix, 0, 0));
			}
		}
		
		return max;
	}
	
	public static int process(int[][] matrix, int row, int col) {
		if (row == matrix.length - 1 && col == matrix[0].length - 1) {
			return 0;
		}
		
		int path = 1;
		
		//向左走条件:
		//  1. 不越界
		//  2. 左边值比当前值要大
		if (col > 0 && matrix[row][col - 1] > matrix[row][col]) {
			path = Math.max(process(matrix, row, col - 1) + 1, path);
		}
		
		//向上走条件:
		//  1. 不越界
		//  2. 上边值比当前值要大
		if (row > 0 && matrix[row - 1][col] > matrix[row][col]) {
			path = Math.max(process(matrix, row - 1, col) + 1, path);
		}
		
		//向下走
		if (row < matrix.length - 1 && matrix[row + 1][col] > matrix[row][col]) {
			path = Math.max(process(matrix, row + 1, col) + 1, path);
		}
		
		//向右走
		if (col < matrix[0].length - 1 && matrix[row][col + 1] > matrix[row][col]) {
			path = Math.max(process(matrix, row, col + 1) + 1, path);
		}
		
		return path;
	}
	
	public static int longestIncreasingPath(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		
		int[][] dp = new int[matrix.length][matrix[0].length];
		
		int max = 0;
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				max = Math.max(max, maxIncreasing(matrix, dp, row + 1, col, matrix[row][col]) + 1);
				max = Math.max(max, maxIncreasing(matrix, dp, row, col + 1, matrix[row][col]) + 1);
				max = Math.max(max, maxIncreasing(matrix, dp, row - 1, col, matrix[row][col]) + 1);
				max = Math.max(max, maxIncreasing(matrix, dp, row, col - 1, matrix[row][col]) + 1);
			}
		}
		return max;
	}
	
	public static int maxIncreasing(int[][] matrix, int[][] dp, int row, int col, int value) {
		if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || matrix[row][col] >= value) {
			return 0;
		}
		
		if (dp[row][col] == 0) {
			//从来没有获取过该值, 进行初次计算
			dp[row][col] = maxIncreasing(matrix, dp, row + 1, col, matrix[row][col]) + 1;
			dp[row][col] = Math.max(dp[row][col], maxIncreasing(matrix, dp, row, col + 1, matrix[row][col]) + 1);
			dp[row][col] = Math.max(dp[row][col], maxIncreasing(matrix, dp, row - 1, col, matrix[row][col]) + 1);
			dp[row][col] = Math.max(dp[row][col], maxIncreasing(matrix, dp, row, col - 1, matrix[row][col]) + 1);
			
		}
		
		return dp[row][col];
		
	}
}

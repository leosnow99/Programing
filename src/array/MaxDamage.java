package array;

/**
 * @author LEOSNOW
 */
public class MaxDamage {
	public static int getMax(int[] arr, int sum) {
		return process(arr, 0, sum);
	}
	
	public static int getMaxDp(int[] arr, int sum) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[][] dp = new int[arr.length][sum + 1];
		if (arr[0] <= sum) {
			dp[0][arr[0]] = arr[0];
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j <= sum; j++) {
				int no = dp[i - 1][j];
				int only = j - arr[i] == 0 ? arr[i] : 0;
				int part = j - arr[i] > 0 ? dp[i - 1][j - arr[i]] * arr[i] : 0;
				dp[i][j] = Math.max(no, Math.max(only, part));
			}
		}
		return dp[dp.length - 1][dp[0].length - 1];
	}
	
	public static int process(int[] arr, int index, int sum) {
		if (sum < 0) {
			return -1;
		}
		if (index == arr.length) {
			return sum == 0 ? 1 : -1;
		}
		if (sum == 0) {
			return 1;
		}
		int notInclude = process(arr, index + 1, sum);
		int include = arr[index] * process(arr, index + 1, sum - arr[index]);
		return Math.max(notInclude, include);
	}
	
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5};
		System.out.println(getMax(arr, 10));
	}
	
	
}

package array;

import java.util.Arrays;

/**
 * 保证arr中都是正数, 找出最小不可组成和
 *
 * @author LEOSNOW
 */
public class SmallestUnFormedSum {
	
	public static int unFormedSumDp(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int min = Integer.MIN_VALUE;
		int sum = 0;
		for (int k : arr) {
			min = Math.min(min, k);
			sum += k;
		}
		
		boolean[][] dp = new boolean[arr.length][sum + 1];
		dp[0][arr[0]] = true;
		
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j <= sum; j++) {
				if (arr[i] == j) {
					dp[i][j] = true;
				} else if (dp[i - 1][j]) {
					dp[i][j] = true;
				} else if (j - arr[i] >= 0 && dp[i - 1][j - arr[i]]) {
					dp[i][j] = true;
				}
			}
		}
		for (int i = min; i <= sum; i++) {
			if (!dp[arr.length - 1][i]) {
				return i;
			}
		}
		
		return sum + 1;
	}
	
	/**
	 * 空间压缩
	 *
	 * @param arr 数组arr, 均为正数
	 * @return 返回最小不可组成和
	 */
	public static int unFormedSumDpHigh(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int min = Integer.MIN_VALUE;
		int sum = 0;
		
		for (int k : arr) {
			min = Math.min(min, k);
			sum += k;
		}
		
		boolean[] dp = new boolean[sum + 1];
		dp[0] = true;
		
		for (int i = 1; i < arr.length; i++) {
			for (int j = sum; j >= arr[i]; j--) {
				dp[j] = dp[j - arr[i]] || dp[j];
			}
		}
		
		for (int i = min; i < dp.length; i++) {
			if (!dp[i]) {
				return i;
			}
		}
		
		return sum + 1;
	}
	
	/**
	 * 如果已知正数数组arr中肯定有1 这个数, 是否能更快的得到最小不可组成和
	 *
	 * @param arr 从一开始的正数数组
	 * @return 最下不可组成和
	 */
	public static int unFormedSumHigh(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		Arrays.sort(arr);
		int range = 1;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > range + 1) {
				return range + 1;
			}
			range += arr[i];
		}
		return range + 1;
	}
}

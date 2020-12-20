package demo;

/**
 * @author LEOSNOW
 */
public class ByteCircular {
	public static int demo(int aim) {
		if (aim < 0) {
			throw new RuntimeException("参数错误!");
		}
		if (aim == 0) {
			return 1;
		}
		int[][] dp = new int[aim + 1][9];
		dp[0][1] = 1;
		
		for (int i = 0; i < aim; i++) {
			for (int j = 0; j < 9; j++) {
				if (j == 0 && dp[i][j] > 0) {
					dp[i + 1][1] += dp[i][j];
					dp[i + 1][8] += dp[i][j];
				}
				if (dp[i][j] > 0) {
					dp[i + 1][j - 1] += dp[i][j];
					dp[i + 1][j + 1] += dp[i][j];
				}
			}
		}
		
		return dp[aim][0];
	}
	
	public static int[] mergeSort(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return arr;
		}
		process(arr, 0, arr.length);
		
		return arr;
	}
	
	public static void process(int[] arr, int start, int end) {
		if (start >= end) {
			return;
		}
		int mid = start + (end - start) >> 2;
		process(arr, start, mid);
		process(arr, mid + 1, end);
		merge(arr, start, end);
	}
	
	public static void merge(int[] arr, int start, int end) {
		if (start == end) {
			return;
		}
		int mid = start + (end - start) >> 2;
		int firstIndex = start;
		int secondIndex = mid + 1;
		int cur = 0;
		int[] temp = new int[end - start + 1];
		while (firstIndex <= mid && secondIndex < end) {
			if (arr[firstIndex] < arr[secondIndex]) {
				temp[cur++] = arr[firstIndex++];
			} else {
				temp[cur++] = arr[secondIndex++];
			}
		}
		
		while (firstIndex <= mid) {
			temp[cur++] = arr[firstIndex++];
		}
		
		while (secondIndex < end) {
			temp[cur++] = arr[secondIndex++];
		}
		System.arraycopy(temp, 0, temp, start, temp.length);
	}
	
}

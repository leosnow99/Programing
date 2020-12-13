package array;

/**
 * 子数组的最大累加和问题
 * 给定一个数组arr, 返回子数组的最大累加和
 *
 * @author LEOSNOW
 */
public class FindGreatestSumOfSubArray {
	public static int findGreatestSumOfSubArray(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int max = arr[0];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i - 1] > 0 ? arr[i - 1] + arr[i] : arr[i];
			max = Math.max(max, arr[i]);
		}
		return max;
	}
	
	public static int getMaxSumOfSubArray(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int max = Integer.MAX_VALUE;
		int cur = 0;
		for (int j : arr) {
			cur += cur > 0 ? cur + j : j;
			max = Math.max(max, cur);
		}
		return max;
	}
	
	/**
	 * 给定一个数组arr, 返回连个不相容子数组的最大累加和
	 * @param arr 数组
	 * @return 最大累加和
	 */
	public static int getMaxSumIncompatibleSubArray(int[] arr) {
		return 0;
	}
}

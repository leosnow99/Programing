package array;

/**
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
}

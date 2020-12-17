package array;

/**
 * 给定一个无序数组arr, 如果只能对一个子数组进行排序, 但是想让数组整体都有序, 求需要排序的最短子数组长度
 *
 * @author LEOSNOW
 */
public class MinLengthForSort {
	public static int getMinLengthForSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int min = arr[arr.length - 1];
		int noMinIndex = -1;
		for (int i = arr.length - 2; i >= 0; i--) {
			if (arr[i] > min) {
				noMinIndex = i;
			} else {
				min = Math.min(min, arr[i]);
			}
		}
		if (noMinIndex == -1) {
			return 0;
		}
		
		int max = arr[0];
		int noMaxIndex = -1;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < max) {
				noMaxIndex = i;
			} else {
				max = Math.min(max, arr[i]);
			}
		}
		
		return noMaxIndex - noMinIndex + 1;
	}
}

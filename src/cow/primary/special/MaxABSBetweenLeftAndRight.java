package cow.primary.special;

public class MaxABSBetweenLeftAndRight {
	public static int maxABS(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int j : arr) {
			max = Math.max(max, j);
		}
		return Math.max(Math.abs(max - arr[0]), Math.abs(max - arr[arr.length - 1]));
	}
}

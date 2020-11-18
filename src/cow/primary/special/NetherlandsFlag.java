package cow.primary.special;

public class NetherlandsFlag {
	public static int[] partition(int[] arr, int flag) {
		return partition(arr, flag, 0, arr.length - 1);
	}
	
	public static int[] partition(int[] arr, int flag, int left, int right) {
		int less = left - 1;
		int more = right + 1;
		while (left < more) {
			if (arr[left] < flag) {
				swap(arr, ++less, left);
			} else if (arr[left] > flag) {
				swap(arr, less, --more);
			} else {
				++less;
			}
		}
		
		return arr;
	}
	
	private static void swap(int[] arr, int left, int right) {
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}
}

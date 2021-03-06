package array;

/**
 * 给定一个无序整型数组arr, 找到数组中未出现的最小正整数
 * @author LEOSNOW
 */
public class SmallestMissNum {
	public static int missNum(int[] arr) {
		int left = 0;
		int right = arr.length;
		while (left < right) {
			if (arr[left] == left + 1) {
				left++;
			} else if (arr[left] <= left || arr[left] >= right || arr[arr[left - 1]] == arr[left]) {
				// 最右坐标无法到达, arr[left]数无效
				arr[left] = arr[right--];
			} else {
				// 将比坐标大的值切换到指定坐标
				swap(arr, left, arr[left] - 1);
			}
		}
		return right + 1;
	}
	
	public static void swap(int[] arr, int indexFirst, int indexSecond) {
		int temp = arr[indexSecond];
		arr[indexSecond] = arr[indexFirst];
		arr[indexFirst] = temp;
	}
}

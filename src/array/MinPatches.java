package array;

/**
 * 给定一个有序的正数数组arr, 和一个正数range, 如果可以自由选择arr中的数字, 想累加得到1 ~ range范围上所有的数, 返回arr最少还缺几个数;
 *
 * @author LEOSNOW
 */
public class MinPatches {
	
	public static int minPatches(int[] arr, int range) {
		// 缺多少个数字
		int patches = 0;
		// 已经完成了1 ~ touch的目标
		long touch = 0;
		for (int i = 0; i < arr.length; i++) {
			while (arr[i] > touch + 1) {
				touch += touch + 1;
				patches++;
				if (touch >= range) {
					return patches;
				}
			}
			touch += arr[i];
			if (touch >= range) {
				return patches;
			}
		}
		while (range >= touch + 1) {
			touch += touch + 1;
			patches++;
		}
		return patches;
	}
}

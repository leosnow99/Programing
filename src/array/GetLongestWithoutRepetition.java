package array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LEOSNOW
 */
public class GetLongestWithoutRepetition {
	/**
	 * 滑动窗口法
	 * @param arr 数组
	 * @return 最长无重复字串长度
	 */
	public static int maxLength (int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int res = 0;
		int left = -1;
		final Map<Integer, Integer> windows = new HashMap<>(32);
		for (int right = 0; right < arr.length; right++) {
			if (windows.containsKey(arr[right])) {
				left = Math.max(left, windows.get(arr[right]));
			}
			windows.put(arr[right], right);
			res = Math.max(res, right - left);
		}
		return res;
	}
}

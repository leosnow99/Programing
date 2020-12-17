package array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个长度大于3的数组arr, 返回该数组能不能分成四个部分, 并且每个部分的累加和相等
 * 解: 前缀累加和 + map 数组扫描
 *
 * @author LEOSNOW
 */
public class Split4Parts {
	
	public static boolean canSplit(int[] arr) {
		if (arr == null || arr.length < 7) {
			return false;
		}
		// key某一累加和(任何一个前缀累加和都会作为key), value出现的位置
		Map<Integer, Integer> map = new HashMap<>(arr.length);
		// 全局累加和
		int sum = arr[0];
		for (int i = 1; i < arr.length; i++) {
			map.put(i, sum);
			sum += arr[i];
		}
		
		//第一刀左侧累加和
		int lSum = arr[0];
		for (int s1 = 1; s1 < arr.length - 5; s1++) {
			// 第二刀的值
			int checkSum = lSum * 2 + arr[s1];
			if (map.containsKey(checkSum)) {
				// 第二的位置
				int s2 = map.get(checkSum);
				// 第三刀的值
				checkSum += lSum + arr[s2];
				if (map.containsKey(checkSum)) {
					int s3 = map.get(checkSum);
					if (checkSum + arr[s3] + lSum == sum) {
						return true;
					}
				}
			}
			lSum += arr[s1];
		}
		return false;
	}
}

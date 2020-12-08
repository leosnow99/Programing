package array;

/**
 * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
 * @leetcode 410
 * @author LEOSNOW
 */
public class SplitArray {
	public int splitArray(int[] nums, int m) {
		int max = 0;
		int sum = 0;
		
		// 计算「子数组各自的和的最大值」的上下界
		for (int num : nums) {
			max = Math.max(max, num);
			sum += num;
		}
		
		int left = max;
		int right = sum;
		while (left < right) {
			int mid = left + (right - left) / 2;
			int splits = split(nums, mid);
			if (splits > m) {
				// 如果分割数太多，说明「子数组各自的和的最大值」太小，此时需要将「子数组各自的和的最大值」调大
				// 下一轮搜索的区间是 [mid + 1, right]
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}
	
	
	/***
	 *
	 * @param nums 原始数组
	 * @param maxIntervalSum 子数组各自的和的最大值
	 * @return 满足不超过「子数组各自的和的最大值」的分割数
	 */
	private int split(int[] nums, int maxIntervalSum) {
		// 至少是一个分割
		int splits = 1;
		//当前区间和
		int currentCount = 0;
		for (int num : nums) {
			if (currentCount + num > maxIntervalSum) {
				currentCount = 0;
				splits++;
			}
			currentCount += num;
		}
		return splits;
	}
}

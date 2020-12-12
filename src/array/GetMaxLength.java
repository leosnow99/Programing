package array;

/**
 * 给定一个数组 arr，该数组无序，但每个值均为正数，再给定一个正数 k。求 arr 的所有子 数组中所有元素相加和为 k 的最长子数组长度。
 *
 * @author leosnow
 */
public class GetMaxLength {
	/**
	 * <p>开始时变量 left=0，right=0，代表子数组 arr[left..right]。</p>
	 * <p>变量 sum 始终表示子数组 arr[left..right]的和。开始时 sum=arr[0]，即 arr[0..0]的和。</p>
	 * <p>变量 len 一直记录累加和为 k 的所有子数组中最大子数组的长度。开始时，len=0</p>
	 * <p>
	 * 根据 sum 与 k 的比较结果决定是 left 移动还是 right 移动，具体如下：
         * <p>
         * 如果 sum==k，说明 arr[left..right]累加和为 k，如果 arr[left..right]长度大于 len，则更新len，
         * 此时因为数组中所有的值都为正数，那么所有从 left 位置开始，在 right 之后的位置结束的子数组，即 arr[left..i(i>right)]，
         * 累加和一定大于 k。所以，令 left 加 1，这表示我们开始考查以 left 之后的位置开始的子数组，同时令 sum-=arr[left]，
         * sum 此开始表示 arr[left+1..right]的累加和。
         * </p>
         * <p>
         * 如果 sum 小于 k，说明 arr[left..right]还需要加上 right 后面的值，其和才可能达到 k，所以，
         * 令 right 加 1，sum+=arr[right]。需要注意的是，right 加 1 后是否越界。
         * </p>
         * <p>
         * 如果 sum 大于 k，说明所有从 left 位置开始，在 right 之后的位置结束的子数组，即arr[left..i(i>right)]，
         * 累加和一定大于 k。所以，令 left 加 1，这表示我们开始考查以 left之后的位置开始的子数组，同时令 sum-=arr[left]，
         * sum 此时表示 arr[left+1..right]的累加和。
         * </p>
	 * </p>
	 * <p>如果 right<arr.length，重复步骤 4。否则直接返回 len，全部过程结束。</p>
	 *
	 * @param arr 字符数字
	 * @param k 和为k
	 * @return 最大子序列
	 */
	public static int getMaxLength(int[] arr, int k) {
		if (arr == null || arr.length < 1 || k < 0) {
			return 0;
		}
		int left = 0;
		int right = 0;
		int sum = arr[left];
		int len = 0;
		
		while (right < arr.length) {
			if (sum == k) {
				len = Math.max(len, right - left + 1);
				sum -= arr[left++];
			} else if (sum < k) {
				right++;
				if (right == arr.length) {
					break;
				}
			} else {
				sum -= arr[left++];
			}
		}
		return len;
	}
}

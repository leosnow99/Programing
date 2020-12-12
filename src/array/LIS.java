package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 最长递增子序列
 *
 * @author LEOSNOW
 */
public class LIS {
	
	/**
	 * 1. 求出最长递增子序列长度
	 * 2. 求出字典序靠前的子序列
	 *
	 * @param arr 字符数组
	 * @return 最长递增子序列长度
	 */
	public static int[] getLis(int[] arr) {
		List<Integer> res = new ArrayList<>();
		int n = arr.length;
		int[] maxLen = new int[n];
		
		for (int i = 0; i < n; i++) {
			if (res.isEmpty()) {
				res.add(arr[i]);
				maxLen[i] = 1;
			} else {
				// res中最后一个元素小于arr[i],直接添加
				if (res.get(res.size() - 1) < arr[i]) {
					res.add(arr[i]);
					maxLen[i] = res.size();
				} else {
					//找出res中第一个大于arr[i]的元素，然后将它置换成arr[i]
					for (int j = res.size() - 1; j >= 0; j--) {
						if (res.get(j) < arr[i]) {
							res.set(j + 1, arr[i]);
							maxLen[i] = j + 2;
							break;
						}
						//找出res中第一个大于arr[i]的元素，然后将它置换成arr[i]
						if (j == 0) {
							res.set(0, arr[i]);
							maxLen[i] = 1;
						}
					}
				}
			}
		}
		
		//创建返回数组
		int[] resArr = new int[res.size()];
		//从后往前面遍历
		for (int i = arr.length - 1, j = res.size(); j > 0; i--) {
			if (maxLen[i] == j) {
				resArr[--j] = arr[i];
			}
		}
		
		return resArr;
	}
	
	
	/*
	 * 算法思路：
	 * 分两步： 1、求出最长递增子序列的长度； 2、求出字典序靠前的子序列
	 * 假设arr = [1,2,8,6,4],res存放递增子序列，maxLen数组存放的是对应下标i结尾的最长递增子序列的长度。
	 * 第一步：
	 *
	 * 初始情况下，res={1}，maxLen=[1]
	 * 接下来是2，此时res的最后一个元素小于2，则直接添加进res，此时res={1,2},maxLen=[1,2]
	 * 接下来是8，此时res的最后一个元素小于8，则直接添加进res，此时res={1,2,8},maxLen=[1,2,3]
	 * 接下来是6，此时res的最后一个元素大于6，则找出res中第一个大于6的元素，并将之置换为6，此时res={1,2,6},maxLen=[1,2,3,3]
	 * 接下来是4，此时res的最后一个元素大于4，则找出res中第一个大于4的元素，并将之置换为4，此时res={1,2,4},maxLen=[1,2,3,3,3]
	 * 第二步：
	 * 假设原来数组是arr，我们在第一步得到maxLen=[1,2,3,3,3],最终我们要返回的结果为resArray，并且结果必须满足字典序最小的最长递增子序列，resArray的最后一个元素根据maxLen数组可以知道是arr数组中的arr[2],arr[3],arr[4]中的其中一个，
	 * 如果是arr[2]，此时arr[2]<arr[3]和arr[2]<arr[4]，此时maxLen[3],maxLen[4]则都大于4了，所以，最后一个元素只能是arr[4]，即arr中最后一个最大长度的下标，此时才能保证之前求出来的maxLen数组全部都是对应的。
	 */
}

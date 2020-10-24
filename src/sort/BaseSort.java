package sort;

import java.util.Arrays;

public class BaseSort {
	//插入排序
	//插入排序是很基本的排序，特别是在数据基本有序的情况下，插入排序的性能很高，最好情况可以达到O(N)，其最坏情况和平均情况时间复杂度都是 O(N^2)。
	public void insertSort(int[] arr, int n) {
		for (int i = 0; i < n; i++) {
			/*
			 * 循环不变式：arr[0...i-1]有序。每次迭代开始前，arr[0...i-1]有序，
			 * 循环结束后i=n，arr[0...n-1]有序
			 * */
			int key = arr[i];
			for (int j = i; j > 0 && arr[j - 1] > key; j++) {
				arr[j] = arr[j - 1];
			}
			arr[i] = key;
		}
	}
	
	//希尔排序
	//希尔排序内部调用插入排序来实现，通过对 N/2，N/4…1阶分别排序，最后得到整体的有序。
	public void shellSort(int[] arr, int n) {
		for (int gap = n / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < n; i++) {
				int key = arr[i];
				for (int j = i; j >= gap && arr[j - gap] > key; j -= gap) {
					arr[j] = arr[j - gap];
				}
				arr[i] = key;
			}
		}
	}
	
	//选择排序
	//选择排序的思想就是第i次选取第i小的元素放在位置i。比如第1次就选择最小的元素放在位置0，第2次选择第二小的元素放在位置1。
	public void selectSort(int[] arr, int n) {
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = 0; j < n; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			if (min != i) {
				int temp = arr[min];
				arr[min] = arr[i];
				arr[i] = temp;
			}
		}
	}
	
	//冒泡排序
	public void bubbleSort(int[] arr, int n) {
		boolean sorted = false;
		
		for (int i = 0; i < n - 1; i++) {
			for (int j = i; j < n - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
					sorted = true;
				}
			}
			
			if (!sorted) {
				return;
			}
		}
	}
	
	//归并排序
	public void mergeSort(int[] arr, int left, int right) {
		if (left < right) {
			int mid = left + (left - right) / 2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);
			merger(arr, left, mid, right);
		}
	}
	
	public void merger(int[] arr, int left, int mid, int right) {
		int[] temp = new int[right - left + 1];
		int leftIndex = left;
		int rightIndex = mid + 1;
		int tempIndex = 0;
		
		while (leftIndex <= mid && rightIndex <= right) {
			if (arr[leftIndex] < arr[rightIndex]) {
				temp[tempIndex++] = arr[leftIndex++];
			} else {
				temp[tempIndex++] = arr[rightIndex++];
			}
		}
		
		while (leftIndex <= mid) {
			temp[tempIndex++] = arr[leftIndex++];
		}
		
		while (rightIndex <= mid) {
			temp[tempIndex++] = arr[rightIndex++];
		}
		
		if (tempIndex >= 0)
			System.arraycopy(temp, 0, arr, 0, tempIndex);
	}
	
	/**
	 * 归并排序-非递归
	 */
	void mergeSortIter(int[] a, int n) {
		int i, s = 2;
		while (s <= n) {
			i = 0;
			while (i + s <= n) {
				merger(a, i, i + s / 2 - 1, i + s - 1);
				i += s;
			}
			
			//处理末尾残余部分
			merger(a, i, i + s / 2 - 1, n - 1);
			s *= 2;
		}
		//最后再从头到尾处理一遍
		merger(a, 0, s / 2 - 1, n - 1);
	}
	
	public void quickSort(int[] arr, int left, int right) {
		if (left >= right) return;
		final int partition = partition(arr, left, right);
		
		quickSort(arr, left, partition);
		quickSort(arr, partition + 1, right);
		
	}
	
	private int partition(int[] arr, int left, int right) {
		int temp = arr[left];
		while (left < right) {
			while (left < right && arr[right] >= temp) {
				right--;
			}
			
			if (left < right) {
				arr[left++] = arr[right];
			}
			
			while (left < right && arr[left] < temp) {
				left++;
			}
			
			if (left < right) {
				arr[right--] = arr[left];
			}
		}
		arr[left] = temp;
		System.out.println(left);
		return left;
	}
	
	private void swap(int[] arr, int first, int second) {
		int temp = arr[first];
		arr[first] = arr[second];
		arr[second] = temp;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[]{1, 3, 7, 2, 2, 9, 4};
		final BaseSort baseSort = new BaseSort();
		baseSort.quickSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}
}

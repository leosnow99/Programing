package search;

public class BinarySearch {
	public static int binarySearch(int[] arr, int key) {
		int length = arr.length - 1;
		if (length == 0) {
			return 0;
		}
		int i = 0;
		while (i < length) {
			int mid = length + (i - length) / 2;
			if (key < arr[mid]) {
				length = mid - 1;
			} else if (key > arr[mid]) {
				i = mid + 1;
			} else {
				return mid;
			}
		}
		//key应该插入的位置
		return -(i + 1);
	}
	
	/**
	 * 算法的精髓在于循环不变式的巧妙设计
	 * <p>
	 * 设定两个不存在的元素a[-1]和a[n]，使得a[-1] < t <= a[n]，
	 * 但是我们并不会去访问这两个元素，因为(l+u)/2 > l=-1, (l+u)/2 < u=n。
	 * 循环不变式为l<u && t>a[l] && t<=a[u] 。循环退出时必然有l+1=u, 而且a[l] < t <= a[u]。
	 */
	public static int binarySearchFirst(int[] arr, int key) {
		int right = arr.length;
		int left = -1;
		while (left + 1 != right) {
			/*循环不变式a[l]<t<=a[u] && l<u*/
			int mid = left + (right - left) / 2;
			if (arr[mid] < key) {
				left = mid;
			} else {
				right = mid;
			}
		}
		if (right > arr.length || arr[right] != key) {
			return -1;
		}
		return right;
	}
	
	public static int binarySearchLast(int[] arr, int key) {
		int right = arr.length;
		int left = -1;
		while (left + 1 != right) {
			int mid = left + (right - left) / 2;
			if (arr[mid] <= key) {
				right = mid;
			} else {
				left = mid;
			}
		}
		
		if (left <= -1 || arr[left] != key) {
			return -1;
		}
		return left;
	}
	
	//旋转数组查找-两次二分查找
	public static int binarySearchRotateTwice(int[] arr, int key) {
		final int position = findRotatePosition(arr);
		if (position == -1) {
			return binarySearchFirst(arr, key);
		}
		if (arr[position] == key) {
			return position;
		} else if (arr[position] > key) {
			int[] tem = new int[position];
			System.arraycopy(arr, 0, tem, 0, position);
			return binarySearchFirst(tem, key);
		} else {
			int[] tem = new int[arr.length - position];
			System.arraycopy(arr, position + 1, tem, 0, arr.length - position);
			return position + binarySearchFirst(tem, key);
		}
	}
	
	//查找旋转位置
	public static int findRotatePosition(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < arr[i + 1]) {
				return i;
			}
		}
		return -1;
	}
	
	//旋转数组查找一次二分查找
	public static int binarySearchRotate(int[] arr, int key) {
		int left = 0;
		int right = arr.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (key == arr[mid]) {
				return mid;
			}
			if (arr[mid] > arr[left]) {
				//数组左边有序
				if (arr[mid] > key && arr[left] <= key) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else {
				//数组右边有序
				if (arr[mid] < key && arr[right] >= key) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		return -1;
	}
	
	
	public static void main(String[] args) {
		System.out.println(binarySearch(new int[]{1, 2, 5, 6, 12, 45, 67, 73, 81, 91}, 46));
	}
}

package heap;

public class HeapDemo {
	public static void maxHeapify(int[] array, int i, int heapSize) {
	
	}
	
	public static int getLeft(int i) {
		return 2 * i + 1;
	}
	
	public static int getRight(int i) {
		return 2 * i + 2;
	}
	
	public static int getParent(int i) {
		if (i <= 0) {
			return 0;
		}
		return (i - 1) / 2;
	}
}

package heap;

/**
 * 打印N个数组整体最大的TopK
 * @author LEOSNOW
 */
public class PrintMaxTopK {
	private static class HeapNode {
		private int value;
		private final int arrNum;
		private int index;
		
		public HeapNode(int value, int arrNum, int index) {
			this.value = value;
			this.arrNum = arrNum;
			this.index = index;
		}
	}
	
	public static void printTopK(int[][] matrix, int topK) {
		int heapSize = matrix.length;
		final HeapNode[] heap = new HeapNode[heapSize];
		for (int i = 0; i < heapSize; i++) {
			int index = matrix[i].length - 1;
			heap[i] = new HeapNode(matrix[i][index], i, index);
			heapInsert(heap, i);
		}
		
		for (int i = 0; i < topK; i++) {
			if (heapSize == 0) {
				break;
			}
			System.out.print(heap[0].value + " ");
			final HeapNode heapNode = heap[0];
			if (heapNode.index != 0) {
				heapNode.value = matrix[heapNode.arrNum][--heapNode.index];
			} else {
				swap(heap, 0, --heapSize);
			}
			heapify(heap, 0, heapSize);
		}
	}
	
	private static void heapify(HeapNode[] heap, int start, int end) {
		int left = start * 2 + 1;
		int right = start * 2 + 2;
		int largest = start;
		while (left < end) {
			if (heap[left].value > heap[start].value) {
				largest = left;
			}
			if (right < end && heap[right].value > heap[start].value) {
				largest = right;
			}
			if (largest == start) {
				return;
			}
			swap(heap, start, largest);
			start = largest;
			left = start * 2 + 1;
			right = start * 2 + 2;
		}
	}
	
	private static void swap(HeapNode[] heap, int nodeFirst, int nodeSecond) {
		final HeapNode heapNode = heap[nodeSecond];
		heap[nodeSecond] = heap[nodeFirst];
		heap[nodeFirst] = heapNode;
	}
	
	public static void heapInsert(HeapNode[] heap, int index) {
		while (index != 0) {
			int parent = (index - 1) / 2;
			if (heap[parent].value < heap[index].value) {
				swap(heap, parent, index);
				index = parent;
			} else {
				break;
			}
		}
	}
}

package heap;

public class PriorityQueue {
	private int capacity = 16;
	private int heapSize;
	private int[] heap;
	
	public PriorityQueue() {
		this.heap = new int[capacity];
	}
	
	public PriorityQueue(int capacity) {
		this.capacity = capacity;
		this.heap = new int[capacity];
	}
	
	//建立最大堆
	public PriorityQueue(int[] list) {
		this.heap = list;
		this.heapSize = heap.length;
		for (int i = heapSize / 2 - 1; i >= 0; i--) {
			maxHeapify(i);
		}
	}
	
	//返回队列中最大关键字的元素
	public int maxElement() {
		if (heapSize > 0) {
			return heap[0];
		}
		return 0;
	}
	
	public int extractMax() {
		if (heapSize <= 0) {
			return 0;
		}
		
		swap(0, --heapSize);
		maxHeapify(0);
		return heap[heapSize];
	}
	
	//将 key 插入到队列中
	public void insert(int k) {
		heapSize++;
		if (heapSize >= capacity) {
			capacity = 2 * capacity;
			final int[] newHeap = new int[capacity];
			System.arraycopy(heap, 0, newHeap, 0, heapSize);
			this.heap = newHeap;
		}
		heap[heapSize] = k;
		increaseKey(heapSize, k);
	}
	
	//将队列 i 处的关键字的值增加到 key
	public void increaseKey(int i, int k) {
		heap[i] = k;
		
		while (i > 0 && heap[getParent(i)] < heap[i]) {
			swap(i, getParent(i));
			i = getParent(i);
		}
	}
	
	public void maxHeapify(int i) {
		maxHeapify(i, heapSize);
	}
	
	public void maxHeapify(int i, int size) {
		int largest = i;
		
		int left = getLeft(i);
		int right = getRight(i);
		
		if (left < size && heap[left] > heap[i]) {
			largest = left;
		}
		
		if (right < size && heap[right] > heap[largest]) {
			largest = right;
		}
		
		if (largest != i) {
			swap(i, largest);
			maxHeapify(largest, size);
		}
		
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
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < heapSize; i++) {
			builder.append(heap[i]).append(" ");
		}
		return builder.toString();
	}
	
	private void swap(int first, int last) {
		int temp = heap[first];
		heap[first] = heap[last];
		heap[last] = temp;
	}
	
	public static void main(String[] args) {
		final PriorityQueue que = new PriorityQueue(new int[]{16, 4, 10, 14, 7, 9, 3, 2, 8, 1, 17,19, 32,12,234,45,67,78,89,34,6,23});
		
		System.out.println(que);
		
		while (que.heapSize > 0) {
			System.out.println(que.extractMax());
		}
	}
}

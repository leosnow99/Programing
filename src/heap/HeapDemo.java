package heap;

import java.util.Arrays;

public class HeapDemo {
    private int[] heap;
    private int heapSize;

    public HeapDemo() {
        heap = new int[16];
        heapSize = 0;
    }

    //建立最大堆
    public HeapDemo(int[] list) {
        this.heap = list;
        this.heapSize = heap.length;
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            maxHeapify(i);
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

    private void swap(int first, int last) {
        int temp = heap[first];
        heap[first] = heap[last];
        heap[last] = temp;
    }

    private void sort() {
        sort(heapSize);
    }

    private void sort(int size) {
//        while (size > 1) {
//            swap(0, size - 1);
//            maxHeapify(0, --size);
//        }

        for (int i = size - 1; i >= 1; i--) {
            swap(0, i);
            System.out.println(i + " " + size);
            maxHeapify(0, --size);
        }
    }

    public static void main(String[] args) {
        final HeapDemo heapDemo = new HeapDemo(new int[]{16, 4, 10, 14, 7, 9, 3, 2, 8, 1, 17});
        System.out.println(Arrays.toString(heapDemo.heap));
        heapDemo.sort();
        System.out.println(Arrays.toString(heapDemo.heap));

    }
}

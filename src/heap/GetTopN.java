package heap;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定String类型的数组strArr，再给定整数k，请严格按照排名顺序打印 出次数前k名的字符串。
 * @author LEOSNOW
 */
public class GetTopN {
	
	/**
	 * 统计各个字符出现次数，使用Map
	 * 创建初始堆(大顶堆)，定义出现次数大的字符串较大，出现次数相同是自然序较前的串较大
	 * 依次去K个堆顶元素并调整堆
	 *
	 * @param strings 字符数组
	 * @param k       出现次数
	 * @return string字符串二维数组
	 */
	public static String[][] topKStrings(String[] strings, int k) {
		if (strings == null || strings.length == 0) {
			return new String[0][];
		}
		
		String[][] result = new String[k][];
		// 统计各个字符串出现的次数
		Map<String, Integer> cntMap = new HashMap<>(32);
		for (String str : strings) {
			cntMap.put(str, cntMap.getOrDefault(str, 0) + 1);
		}
		//创建初始堆 大顶堆
		Node[] heap = new Node[cntMap.size() + 1];
		int index = 0;
		for (Map.Entry<String, Integer> entry : cntMap.entrySet()) {
			final Node node = new Node(entry.getKey(), entry.getValue());
			heap[++index] = node;
		}
		
		//调整初始堆
		for (int i = index >> 1; i > 0; i--) {
			heapify(heap, i, heap.length);
		}
		
		//取强K个
		int limit = heap.length - 1;
		for (int i = 0; i < k && i <= limit; i++) {
			String[] r = new String[]{heap[1].val, String.valueOf(heap[1].cnt)};
			result[i - 1] = r;
			
			Node temp = heap[1];
			heap[1] = heap[limit];
			heap[limit] = temp;
			limit--;
			heapify(heap, 1, limit + 1);
		}
		
		return result;
	}
	
	public static void heapify(Node[] heap, int startIndex, int endIndex) {
		int childIndex = 2 * startIndex;
		if (childIndex + 1 < endIndex && heap[childIndex + 1].compareTo(heap[childIndex]) > 0) {
			childIndex = childIndex + 1;
		}
		
		if (childIndex < endIndex && heap[childIndex].compareTo(heap[startIndex]) > 0) {
			final Node temp = heap[childIndex];
			heap[childIndex] = heap[startIndex];
			heap[startIndex] = temp;
			heapify(heap, childIndex, endIndex);
		}
	}
	
	private static class Node implements Comparable<Node> {
		String val;
		int cnt;
		
		public Node(String val, int cnt) {
			this.val = val;
			this.cnt = cnt;
		}
		
		@Override
		//定义比较方法: cnt 大的大， cnt相同时，val自然序小的大
		public int compareTo(Node o) {
			if (this.cnt != o.cnt) {
				return this.cnt - o.cnt;
			} else {
				return -this.val.compareTo(o.val);
			}
		}
	}
}

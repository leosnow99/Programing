package array;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author LEOSNOW
 */
public class GetLeastNumbers {
	public static ArrayList<Integer> getLeastNumbers(int [] input, int k) {
		final ArrayList<Integer> result = new ArrayList<>();
		if (input == null || input.length == 0 || k == 0) {
			return result;
		}
		
		final Queue<Integer> queue = new PriorityQueue<>(k, (a, b) -> (b - a));
		for (int j : input) {
			if (queue.size() < k) {
				queue.offer(j);
			} else {
				if (j < queue.peek()) {
					queue.poll();
					queue.offer(j);
				}
			}
		}
		result.addAll(queue);
		return result;
	}
}

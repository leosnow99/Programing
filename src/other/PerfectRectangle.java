package other;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你几组矩形，请判断他们能不能正好组成一个完整的大矩形，且没
 * 有重合的部分。
 *
 * @author LEOSNOW
 */
public class PerfectRectangle {
	
	public static boolean isRectangleCover(int[][] rectangles) {
		if (rectangles.length == 0 || rectangles[0].length == 0) {
			return false;
		}
		
		final String connector = "_";
		
		int x1 = Integer.MAX_VALUE;
		int x2 = Integer.MIN_VALUE;
		int y1 = Integer.MAX_VALUE;
		int y2 = Integer.MIN_VALUE;
		
		Set<String> set = new HashSet<>();
		int area = 0;
		for (int[] rectangle : rectangles) {
			x1 = Math.min(rectangle[0], x1);
			y1 = Math.min(rectangle[1], y1);
			x2 = Math.min(rectangle[2], x2);
			y2 = Math.min(rectangle[3], y2);
			area += (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);
			String s1 = rectangle[0] + connector + rectangle[1];
			String s2 = rectangle[0] + connector + rectangle[3];
			String s3 = rectangle[2] + connector + rectangle[3];
			String s4 = rectangle[2] + connector + rectangle[1];
			
			if (!set.add(s1)) {
				set.remove(s1);
			}
			if (!set.add(s2)) {
				set.remove(s2);
			}
			if (!set.add(s3)) {
				set.remove(s3);
			}
			if (!set.add(s4)) {
				set.remove(s4);
			}
		}
		if (!set.contains(x1 + connector + y1)
				|| !set.contains(x1 + connector + y2)
				|| !set.contains(x2 + connector + y1)
				|| !set.contains(x2 + connector + y2)
				|| set.size() != 4) {
			return false;
		}
		return area == (x2 - x1) * (y2 - y1);
		
	}
}

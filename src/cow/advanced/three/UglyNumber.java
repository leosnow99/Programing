package cow.advanced.three;

/**
 * 一个数的因子仅仅包括2, 3, 5的数称为丑数;
 * 数字1也看作丑数, 所以从1开始的10个丑数分别为: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12
 * 返回第n个丑数
 *
 * @author LEOSNOW
 */
public class UglyNumber {
	public static int uglyNumber(int n) {
		int[] help = new int[n];
		help[0] = 1;
		int i2 = 0;
		int i3 = 0;
		int i5 = 0;
		int index = 1;
		while (index < n) {
			help[index] = Math.min(2 * help[i2], Math.min(3 * help[i3], 5 * help[i5]));
			if (help[index] == 2 * help[i2]) {
				i2++;
			}
			if (help[index] == 3 * help[i3]) {
				i3++;
			}
			if (help[index] == 5 * help[i5]) {
				i5++;
			}
			index++;
		}
		return help[n - 1];
	}
}

package cow.advanced.three;

/**
 * 将整数字符串转换成整数值
 * 整数32位 不能0开头
 * @author LEOSNOW
 */
public class ConvertStrToInt {

	public static int convert(String str) {
		if (str == null || "".equals(str) || !isValid(str)) {
			throw new RuntimeException("can not convert!");
		}

		//是否是负数
		boolean posi = str.charAt(0) != '-';
		int minq = Integer.MIN_VALUE / 10;
		int minr = Integer.MAX_VALUE;
		int res = 0;
		int cur;

		for (int i = posi ? 0 : 1; i < str.length(); i++) {
			cur = '0' - str.charAt(i);
			//检查越界
			if (res < minq || (res == minq && cur < minr)) {
				throw new RuntimeException("can not convert!");
			}
			res = res * 10 + cur;
		}

		//判断越界
		if (posi && res == Integer.MIN_VALUE) {
			throw new RuntimeException("can not convert!");
		}

		return posi ? -res : res;
	}
	
	public static boolean isValid(String str) {
		if (str.charAt(0) != '-' && (str.charAt(0) < '0' || str.charAt(0) > '9')) {
			return false;
		}
		if (str.charAt(0) == '-' && (str.length() == 1 || str.charAt(1) == '0')) {
			return false;
		}
		if (str.charAt(0) == '0') {
			return false;
		}
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) < '0' || str.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	
	}


	public static void main(String[] args) {
//		String test1 = "2147483647"; // max in java
//		System.out.println(convert(test1));
//
//		String test2 = "-2147483648"; // min in java
//		System.out.println(convert(test2));
//
//		String test3 = "2147483648"; // overflow
//		System.out.println(convert(test3));
//
//		String test4 = "-2147483649"; // overflow
//		System.out.println(convert(test4));

		String test5 = "-123";
		System.out.println(convert(test5));

	}
}

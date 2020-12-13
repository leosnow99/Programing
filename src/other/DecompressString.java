package other;

/**
 * 还原压缩字符串
 * 3{ab}bs2{d} -> a b a b a b b s d d
 *
 * @author LEOSNOW
 */
public class DecompressString {
	
	private static class ReturnData {
		String str;
		int end;
		
		public ReturnData(String str, int end) {
			this.str = str;
			this.end = end;
		}
	}
	
	public static String decompressString(String str) {
		if (str == null || str.length() < 1) {
			return "";
		}
		return process(str.toCharArray(), 0).str;
	}
	
	public static ReturnData process(char[] chars, int index) {
		StringBuilder res = new StringBuilder();
		int times = 0;
		while (index < chars.length && chars[index] != '}') {
			if (chars[index] == '{') {
				final ReturnData returnData = process(chars, index + 1);
				res.append(getTimesStr(times, returnData.str));
				times = 0;
				index = returnData.end + 1;
			} else {
				if (chars[index] >= '0' && chars[index] <= '9') {
					times = times * 10 + chars[index] - '0';
				}
				if (chars[index] >= 'a' && chars[index] <= 'z') {
					res.append(chars[index]);
				}
				index++;
			}
		}
		return new ReturnData(res.toString(), index);
	}
	
	public static String getTimesStr(int times, String base) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < times; i++) {
			res.append(base);
		}
		return res.toString();
	}
}

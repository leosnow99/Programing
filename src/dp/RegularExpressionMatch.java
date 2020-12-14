package dp;

/**
 * 字符串匹配问题
 * <p>
 * 给定字符串str, 其中绝对不含有字符'.' 和 '*'. 再给定字符串exp, 其中可以含有'.'或'*'
 * '.'可以匹配一个字符, '*' 表示'*'的前一个字符可以有0个或者多个
 * <p>检测str能否被exp匹配</p>
 *
 * @author LEOSNOW
 */
public class RegularExpressionMatch {
	
	public static boolean isValid(char[] s, char[] e) {
		for (char c : s) {
			if (c == '*' || c == '.') {
				return false;
			}
		}
		for (int i = 0; i < e.length; i++) {
			if (e[i] == '*' && (i == 0 || e[i - 1] == '*')) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isMatch(String str, String exp) {
		if (str == null || exp == null) {
			return false;
		}
		
		final char[] chars = str.toCharArray();
		final char[] expChars = exp.toCharArray();
		
		return isValid(chars, expChars) && process(chars, expChars, 0, 0);
	}
	
	public static boolean process(char[] s, char[] e, int sIndex, int eIndex) {
		if (eIndex == e.length) {
			return sIndex == s.length;
		}
		if (eIndex + 1 == e.length || e[eIndex] != '*') {
			return sIndex != s.length && (e[eIndex] == s[sIndex] || e[eIndex] == '.')
					&& process(s, e, sIndex + 1, eIndex + 1);
		}
		
		while (sIndex != s.length && (e[eIndex] == s[sIndex] || e[eIndex] == '.')) {
			// *表示0次重复的情况
			if (process(s, e, sIndex, eIndex + 2)) {
				return true;
			}
			sIndex++;
		}
		return process(s, e, sIndex, eIndex + 2);
	}
	
	public static boolean isMatchDp(String str, String exp) {
		if (str == null || exp == null) {
			return false;
		}
		
		final char[] chars = str.toCharArray();
		final char[] expChars = exp.toCharArray();
		
		if (!isValid(chars, expChars)) {
			return false;
		}
		
		boolean[][] dp = initDpMap(chars, expChars);
		
		for (int i = chars.length; i >= 0; i--) {
			for (int j = expChars.length - 2; j >= 0; j--) {
				if (expChars[j + 1] != '*') {
					dp[i][j] = (chars[i] == expChars[j] || expChars[j] == '.') && dp[i + 1][j + 1];
				} else {
					int sIndex = i;
					while (sIndex != chars.length && (chars[sIndex] == expChars[j] || expChars[j] == '.')) {
						if (dp[sIndex][j + 2]) {
							dp[i][j] = true;
							break;
						}
						sIndex++;
					}
					if (!dp[i][j]) {
						dp[i][j] = dp[sIndex][j + 2];
					}
				}
			}
		}
		
		return dp[0][0];
	}
	
	private static boolean[][] initDpMap(char[] s, char[] e) {
		int sLen = s.length;
		int eLen = e.length;
		boolean[][] dp = new boolean[sLen + 1][eLen + 1];
		
		dp[sLen][eLen] = true;
		
		for (int j = eLen - 2; j >= 0; j = j - 2) {
			if (e[j] != '*' && e[j + 1] == '*') {
				dp[sLen][j] = true;
			} else {
				break;
			}
		}
		
		if (sLen > 0 && eLen > 0) {
			if ((e[eLen - 1] == '.' || s[sLen - 1] == e[eLen - 1])) {
				dp[sLen - 1][eLen - 1] = true;
			}
		}
		return dp;
	}
}

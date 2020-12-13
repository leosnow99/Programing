package other;

/**
 * @author LEOSNOW
 */
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class ExpressionNumber {
	public static int getNum(String expression, boolean aim) {
		final char[] chs = expression.toCharArray();
		if (isValid(chs)) {
			return 0;
		}
		final ReturnData returnData = getValidNum(chs, 0, chs.length - 1);
		return aim ? returnData.trueNums : returnData.falseNums;
	}
	
	
	public static class ReturnData {
		public int trueNums;
		public int falseNums;
		
		public ReturnData(int trueNums, int falseNums) {
			this.trueNums = trueNums;
			this.falseNums = falseNums;
		}
	}
	
	public static boolean isValid(char[] exp) {
		if ((exp.length & 1) == 0) {
			return true;
		}
		
		for (int i = 0; i < exp.length; i = i + 2) {
			if (exp[i] != '0' && exp[i] != '1') {
				return true;
			}
		}
		
		for (int i = 1; i < exp.length; i = i + 2) {
			if (exp[i] != '&' && exp[i] != '|' && exp[i] != '^') {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * start 和 end 位置一定要压中0或1
	 *
	 * @param chs   表达式数组
	 * @param start 开始位置
	 * @param end   结束位置
	 * @return true和false数目
	 */
	public static ReturnData getValidNum(char[] chs, int start, int end) {
		if (start == end) {
			if (chs[start] == '0') {
				return new ReturnData(0, 1);
			} else {
				return new ReturnData(1, 0);
			}
		}
		
		int trueNums = 0;
		int falseNums = 0;
		
		for (int i = start + 1; i < end; i = i + 2) {
			final ReturnData leftReturnData = getValidNum(chs, start, i - 1);
			final ReturnData rightReturnData = getValidNum(chs, i + 1, end);
			int a = leftReturnData.trueNums;
			int b = leftReturnData.falseNums;
			int c = rightReturnData.trueNums;
			int d = rightReturnData.falseNums;
			if (chs[i] == '&') {
				trueNums += a * c;
				falseNums += b * c + b * d + a * d;
			} else if (chs[i] == '|') {
				trueNums += a * c + a * d + b * c;
				falseNums += b * d;
			} else {
				// ^ 异或
				trueNums += a * d + b * c;
				falseNums += a * c + b * d;
			}
		}
		
		return new ReturnData(trueNums, falseNums);
	}
	
	public static int getNumDp(String expression, boolean aim) {
		if (expression == null || expression.length() < 1) {
			throw new RuntimeException("参数错误!");
		}
		
		if (expression.length() == 1) {
			return aim ? ("1".equals(expression) ? 1 : 0) : ("0".equals(expression) ? 1 : 0);
		}
		
		final char[] chars = expression.toCharArray();
		if (isValid(chars)) {
			return 0;
		}
		final int len = chars.length;
		int[][] trueDp = new int[len][len];
		int[][] falseDp = new int[len][len];
		
		//完成对角线
		for (int i = 0; i < len; i++) {
			trueDp[i][i] = chars[i] == '1' ? 1 : 0;
			falseDp[i][i] = chars[i] == '0' ? 0 : 1;
		}
		
		for (int row = len - 3; row >= 0; row = row - 2) {
			for (int col = row + 2; col < len; col = col + 2) {
				int trueNums = 0;
				int falseNums = 0;
				for (int split = row + 1; split < col; split += 2) {
					int a = trueDp[row][split - 1];
					int b = falseDp[row][split - 1];
					int c = trueDp[split + 1][col];
					int d = falseDp[split + 1][col];
					
					if (chars[split] == '&') {
						trueNums += a * c;
						falseNums += b * c + b * d + a * d;
					} else if (chars[split] == '|') {
						trueNums += a * c + a * d + b * c;
						falseNums += b * d;
					} else {
						// ^ 异或
						trueNums += a * d + b * c;
						falseNums += a * c + b * d;
					}
				}
				trueDp[row][col] = trueNums;
				falseDp[row][col] = falseNums;
			}
		}
		
		
		return aim ? trueDp[0][len - 1] : falseDp[0][len - 1];
	}
}

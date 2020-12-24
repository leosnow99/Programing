package dp;

/**
 * <b>最长公共子串问题</b>
 * <p>给定两个字符串 str1 和 str2，返回两个字符串的最长公共子串。</p>
 *
 * <p>str1="1AB2345CD"，str2="12345EF"，返回"2345"。</p>
 * <p/>
 *
 * @author leosnow
 */
public class MaxChildrenString {

    /**
     * 生成动态规划表
     *
     * @param strFirst  第一个字符数组
     * @param strSecond 第二个字符数组
     * @return 动态规划表
     */
    public static int[][] getDp(char[] strFirst, char[] strSecond) {
        int[][] dp = new int[strFirst.length][strSecond.length];
        for (int i = 0; i < strFirst.length; i++) {
            if (strFirst[i] == strSecond[0]) {
                dp[i][0] = 1;
            }
        }

        for (int j = 1; j < strSecond.length; j++) {
            if (strSecond[j] == strFirst[0]) {
                dp[0][j] = 1;
            }
        }

        for (int i = 1; i < strFirst.length; i++) {
            for (int j = 1; j < strSecond.length; j++) {
                if (strFirst[i] == strSecond[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }

        return dp;
    }

    /**
     * 经典动态规划的方法需要大小为 M×N 的 dp 矩阵
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 最长公共子串
     */
    public static String longChildSubString(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        final char[] charArrayFirst = str1.toCharArray();
        final char[] charArraySecond = str2.toCharArray();
        final int[][] dp = getDp(charArrayFirst, charArraySecond);
        int end = 0;
        int max = 0;
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (dp[i][j] > max) {
                    end = i;
                    max = dp[i][j];
                }
            }
        }

        return str1.substring(end - max + 1, end + 1);
    }

    public static String longChildSubStringHigh(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        // 斜线开始的位置的行
        int row = 0;
        // 斜线开始的列
        int col = chs2.length - 1;
        int max = 0;
        int end = 0;

        while (row < chs1.length) {
            int i = row;
            int j = col;
            int len = 0;
            // 从(i,j)开始向右下方遍历
            while (i < chs1.length && j < chs2.length) {
                if (chs1[i] != chs2[j]) {
                    len = 0;
                } else {
                    len++;
                }
                // 记录最大值，以及结束字符的位置
                if (len > max) {
                    end = i;
                    max = len;
                }
                i++;
                j++;
            }

            if (col > 0) {
                // 斜线开始位置的列先向左移动
                col--;
            } else {
                // 斜线开始位置的列先向左移动
                row++;
            }
        }
        return str1.substring(end - max + 1, max + 1);

    }
}

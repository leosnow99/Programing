package dp;

import java.util.Arrays;

/**
 * <p>
 * 最长公共子序列问题
 * 给定两个字符串 str1 和 str2，返回两个字符串的最长公共子序列。
 * </p>
 * <p>
 * str1="1A2C3D4B56"，str2="B1D23CA45B6A"。
 * "123456"或者"12C4B6"都是最长公共子序列，返回哪一个都行。
 * </p>
 *
 * @author leosnow
 */
public class MaxSubArrayString {

    /**
     * 获得动态规划矩阵
     * <p>如果 str1 的长度为 M， str2 的长度为 N，生成大小为 M×N 的矩阵 dp，行数为 M，列数为 N。
     * dp[i][j]的含义是 str1[0..i] 与 str2[0..j]的最长公共子序列的长度。从左到右，再从上到下计算矩阵 dp。</p>
     *
     * @param strFirst  第一个字符数组
     * @param strSecond 第二个字符数组
     * @return 状态变化数组
     */
    public static int[][] getDp(char[] strFirst, char[] strSecond) {
        int[][] dp = new int[strFirst.length][strSecond.length];
        dp[0][0] = strFirst[0] == strSecond[0] ? 1 : 0;

        //矩阵 dp 第一列即 dp[0..M-1][0]，dp[i][0]的含义是 str1[0..i]与 str2[0]的最长公共子序列长 度。str2[0]只有一个字符，
        //所以 dp[i][0]最大为 1。如果 str1[i]==str2[0]，令 dp[i][0]=1，一旦 dp[i][0] 被设置为 1，之后的 dp[i+1..M-1][0]也都为 1。
        //比如，str1[0..M-1]="ABCDE"，str2[0]="B"。str1[0] 为"A"，与 str2[0]不相等，所以 dp[0][0]=0。str1[1]为"B"，与 str2[0]相等，
        //所以 str1[0..1]与 str2[0] 的最长公共子序列为"B"，令 dp[1][0]=1。之后的 dp[2..4][0]肯定都是 1，
        //因为 str[0..2]、str[0..3] 和 str[0..4]与 str2[0]的最长公共子序列肯定有"B"。
        for (int i = 1; i < strFirst.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], strFirst[i] == strSecond[0] ? 1 : 0);
        }

        //矩阵 dp 第一行即 dp[0][0..N-1]与步骤 1 同理，如果 str1[0]==str2[j]，则令 dp[0][j]=1，一旦 dp[0][j]被设置为 1，
        //之后的 dp[0][j+1..N-1]也都为 1。
        for (int i = 1; i < strSecond.length; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], strSecond[i] == strFirst[0] ? 1 : 0);
        }

        /*
         * 对其他位置(i,j)，dp[i][j]的值只可能来自以下三种情况:
         * 可能是 dp[i-1][j]，代表 str1[0..i-1]与 str2[0..j]的最长公共子序列长度
         *
         * 可能 是 dp[i][j-1] ， 代 表 str1[0..i] 与 str2[0..j-1] 的 最长公共子序列长度。
         *
         * 如果 str1[i]==str2[j]，还可能是 dp[i-1][j-1]+1。
         *
         */
        for (int i = 1; i < strFirst.length; i++) {
            for (int j = 1; j < strSecond.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (strFirst[i] == strSecond[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }

        return dp;
    }


    /**
     * dp 矩阵中最右下角的值代表 str1 整体和 str2 整体的最长公共子序列的长度。通过整个 dp 矩阵的状态，可以得到最长公共子序列。
     *
     * @param strFirst 字符串1
     * @param strSecond 字符串2
     * @return 最长公共子序列
     */
    public static String lcse(String strFirst, String strSecond) {
        if (strFirst == null || strSecond == null || "".equals(strFirst) || "".equals(strSecond)) {
            return "";
        }

        char[] charFirst = strFirst.toCharArray();
        char[] charSecond = strSecond.toCharArray();

        final int[][] dp = getDp(charFirst, charSecond);
        int m = charFirst.length - 1;
        int n = charSecond.length - 1;
        char[] res = new char[dp[m][n]];
        int index = dp[m][n] - 1;

        while (index >= 0) {
            if (n > 0 && dp[m][n] == dp[m][n - 1]) {
                //如果 dp[i][j]等于 dp[i-1][j]，说明之前在计算 dp[i][j]的时候，dp[i-1][j-1]+1 这个决策不是 必须选择的决策，向上方移动即可
                n--;
            } else if (m > 0 && dp[m][n] == dp[m - 1][n]) {
                //如果 dp[i][j]等于 dp[i][j-1]，与步骤 3 同理，向左方移动。
                m--;
            } else {
                //如果 dp[i][j]大于 dp[i-1][j]和 dp[i][j-1]，说明之前在计算 dp[i][j]的时候，一定是选择了决 策 dp[i-1][j-1]+1，
                //可以确定 str1[i]等于 str2[j]，并且这个字符一定属于最长公共子序列，把这个 字符放进 res，然后向左上方移动。
                res[index--] = charFirst[n];
                n--;
                m--;
            }
        }

        return Arrays.toString(res);
    }

}

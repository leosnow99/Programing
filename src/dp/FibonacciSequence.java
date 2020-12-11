package dp;

public class FibonacciSequence {
    /**
     * 给定整数 N，代表台阶数，一次可以跨 2 个或者 1 个台阶，返回有多少种走 法。
     * 给定整数 N，返回斐波那契数列的第 N 项。
     */
    public static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    /**
     * O(N)的方法。斐波那契数列可以从左到右依次求出每一项的值，那么通过顺序计算求到第 N 项即可。
     */
    public static int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int res = 1;
        int pre = 1;
        int temp;
        for (int i = 3; i < n; i++) {
            temp = res;
            res = res + pre;
            pre = temp;
        }
        return res;

    }
}


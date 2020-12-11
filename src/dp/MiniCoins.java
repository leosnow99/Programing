package dp;

/**
 * 给定数组 arr，arr 中所有的值都为正数且不重复。每个值代表一种面值的货币，每种面值 的货币可以使用任意张，
 * 再给定一个整数 aim，代表要找的钱数，求组成 aim 的最少货币数。
 */
public class MiniCoins {
    public static int miniCoins(int[] arr, int num) {
        if (num <= 0 || arr == null || arr.length == 0 || arr[0] > num) {
            return -1;
        }
        return 0;
    }

    public static int process(int[] arr, int index, int rest) {
        // base case：
        //已经没有面值能够考虑了
        //如果此时剩余的钱为 0，返回 0 张
        //如果此时剩余的钱不是 0，返回-1
        if (index == arr.length) {
            return rest == 0 ? 0 : -1;
        }
        // 最少张数，初始时为-1，因为还没找到有效解
        int result = -1;
        // 依次尝试使用当前面值(arr[i])0 张、1 张、k 张，但不能超过 rest
        for (int k = 0; k * arr[index] <= rest; k++) {
            // 使用了 k 张 arr[i]，剩下的钱为 rest - k * arr[i]
            //交给剩下的面值去搞定(arr[i+1..N-1])
            int next = process(arr, index + 1, rest - k * arr[index]);
            if (next != -1) { // 说明这个后续过程有效
                result = result == -1 ? next : Math.min(result, next + k);
            }
        }
        return result;
    }
}

package StackAndQueue;

import java.util.Stack;

public class VisibleMountNum {
    public static int getVisibleNum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int size = arr.length;
        int maxIndex = 0;

        // 先在环中找到其中一个最大值的位置，哪一个都行
        for (int i = 0; i < size; i++) {
            maxIndex = arr[i] > arr[maxIndex] ? i : maxIndex;
        }
        Stack<Record> stack = new Stack<>();
        // 先把(最大值,1)这个记录放入 stack 中
        stack.add(new Record(arr[maxIndex]));
        // 从最大值位置的下一个位置开始沿 next 方向遍历
        int nextIndex = nextIndex(maxIndex, size);
        // 用“小找大”的方式统计所有可见山峰对
        int res = 0;
        // 遍历阶段开始，当 index 再次回到 maxIndex 的时候，说明转了一圈，遍历阶段就结束
        while (nextIndex != maxIndex) {
            // 当前数字 arr[index]要进栈，判断会不会破坏第一维的数字从顶到底依次变大
            //如果破坏了，就依次弹出栈顶记录，并计算山峰对数量
            while (stack.peek().value < arr[nextIndex]) {
                int k = stack.pop().times;
                // 弹出记录为(X,K)，如果 K==1，产生 2 对
                //如果 K>1，产生 2*K + C(2,K)对
                res += getInternalSum(k) + 2*k;
                // 当前数字 arr[index]要进入栈了，如果和当前栈顶数字一样就合并
                //不一样就把记录(arr[index],1)放入栈中
                if (stack.peek().value == arr[nextIndex]) {
                    stack.peek().times = stack.peek().times+1;
                } else {
                    stack.push(new Record(arr[nextIndex]));
                }
            }
            nextIndex = nextIndex(nextIndex, size);
        }
        //清算阶段开始
        //清算阶段的第 1 小阶段
        while (stack.size() > 2) {
            int k = stack.pop().times;
            res += getInternalSum(k) + 2 * k;
        }
        // 清算阶段的第 2 小阶段
        if (stack.size() == 2) {
            int k = stack.pop().times;
            res += getInternalSum(k) + (stack.peek().times == 1 ? k : 2 * k);
        }
        // 清算阶段的第 3 小阶段
        res += getInternalSum(stack.pop().times);
        return res;
    }

    // 如果 k==1，返回 0；如果 k>1，返回 C(2,k)
    public static int getInternalSum(int k) {
        return k == 1 ? 0 : (k * (k - 1) / 2);
    }

    // 环形数组中当前位置为 i，数组长度为 size，返回 i 的下一个位置
    public static int nextIndex(int index, int size) {
        return index < (size - 1) ? index+1 : 0;
    }


}
class Record{
    public int value;
    public int times;
    public Record(int value) { this.value = value; this.times = 1; }
}
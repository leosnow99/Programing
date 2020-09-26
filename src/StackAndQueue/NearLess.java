package StackAndQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//给定一个不含有重复值的数组 arr，找到每一个 i 位置左边和右边离 i 位置最近且值比 arr[i] 小的位置。返回所有位置相应的信息。
//使用单调栈结构实现
public class NearLess {
    //没有重复元素
    public static int[][] getNearLess(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        //栈中还有元素
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }

    //有重复元素
    public static int[][] getNearLessHasSame(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popList = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer index : popList) {
                    res[index][0] = leftLessIndex;
                    res[index][1] = i;
                }
            }
            if (!stack.isEmpty() && stack.peek().get(0) == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }

        //栈中还有元素
        while (!stack.isEmpty()) {
            List<Integer> popList = stack.pop();
            // 取位于下面位置的列表中，最晚加入的那个
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer index : popList) {
                res[index][0] = leftLessIndex;
                res[index][1] = -1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 6, 1, 9};
        int[][] nearLess = getNearLess(arr);
        System.out.println(Arrays.deepToString(nearLess));

        int[] arrSame = new int[]{3, 5, 3, 6, 1, 3, 9};
        int[][] res = getNearLess(arrSame);
        System.out.println(Arrays.deepToString(res));
    }
}

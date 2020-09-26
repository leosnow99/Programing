package StackAndQueue;

import java.util.LinkedList;

//最大值减去最小值小于或等于 num 的子数组数量
//给定数组 arr 和整数 num，共返回有多少个子数组满足如下情况：
//max(arr[i..j]) - min(arr[i..j]) <= num max(arr[i..j])表示子数组 arr[i..j]中的最大值，
//min(arr[i..j])表示子数组 arr[i..j]中的最小值。
public class SmallArrayNum {
    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) {
            return 0;
        }
        LinkedList<Integer> qMin = new LinkedList<>();
        LinkedList<Integer> qMax = new LinkedList<>();
        int i = 0;
        int j = 0;
        int res = 0;
        while (i < arr.length) {
            while (j < arr.length) {
                if (qMin.isEmpty() || qMin.peekLast() != j) {
                    while (!qMin.isEmpty() && arr[qMin.peekFirst()] > arr[j]) {
                        qMin.pollLast();
                    }
                    qMin.addLast(j);
                    while (!qMax.isEmpty() && arr[qMax.peekFirst()] < arr[j]) {
                        qMax.pollLast();
                    }
                    qMax.addLast(j);
                }
                if (arr[qMax.peekFirst()] - arr[qMin.peekFirst()] > num) {
                    break;
                }
                j++;
            }
            res += j - i;
            if (qMin.peekFirst() == i) {
                qMin.pollFirst();
            }
            if (qMax.peekFirst() == i) {
                qMax.pollFirst();
            }
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 10, 7};
        int res = getNum(arr, 5);
        System.out.println("Result is: " + res);
    }
}

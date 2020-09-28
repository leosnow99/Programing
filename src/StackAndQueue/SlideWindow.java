package StackAndQueue;

import java.util.LinkedList;

public class SlideWindow {
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        LinkedList<Integer> qMax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]) {
                qMax.pollLast();
            }
            qMax.addLast(i);
            if (qMax.peekFirst() == i - w) {
                //头过期
                qMax.pollFirst();
            }
            //窗口越界
            if (i >= w - 1) {
                res[index++] = arr[qMax.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 5, 4, 3, 3, 6, 7};
        int[] res = getMaxWindow(arr, 3);
        for (int re : res) {
            System.out.println(re);
        }
    }

}

//如果子数组 arr[i..j]满足条件，即 max(arr[i..j])-min(arr[i..j])<=num，那么 arr[i..j]中的每一
//个子数组，即 arr[k..l]（i≤k≤l≤j）都满足条件。我们以子数组 arr[i..j-1]为例说明，arr[i..j-1]
//最大值只可能小于或等于 arr[i..j]的最大值，arr[i..j-1]最小值只可能大于或等于 arr[i..j]
//的最小值，所以 arr[i..j-1]必然满足条件。同理，arr[i..j]中的每一个子数组都满足条件。

// 如果子数组 arr[i..j]不满足条件，那么所有包含 arr[i..j]的子数组，即 arr[k..l]（k≤i≤j≤l）
//都不满足条件。证明过程同第一个结论。
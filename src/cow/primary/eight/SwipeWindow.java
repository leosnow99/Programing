package cow.primary.eight;

import java.util.LinkedList;

public class SwipeWindow {
    /***
     *
     * @param arr 数组
     * @param w 窗口长度
     * @return 窗口滑动过程中每个最大值
     */
    public static int[] swipeWindow(int[] arr, int w) {
        if (arr == null || arr.length < w || w < 1) {
            return null;
        }
        //存放数组下标队列
        final LinkedList<Integer> list = new LinkedList<>();
        int[] result = new int[arr.length - w + 1];
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            while (!list.isEmpty() && arr[list.peek()] <= arr[i]) {
                list.pollLast();
            }
            list.add(i);
            if (list.peekFirst() == i - w) {
                list.pollFirst();
            }
            if (i >= w - 1) {
                result[index++] = arr[list.peekFirst()];
            }
        }

        return result;
    }
}

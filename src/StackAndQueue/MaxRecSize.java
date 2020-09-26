package StackAndQueue;

import java.util.Stack;

//求最大子矩阵的大小
//给定一个整型矩阵 map，其中的值只有 0 和 1 两种，求其中全是 1 的所有矩形区域中，最 大的矩形区域为 1 的数量。
public class MaxRecSize {
    public static int maxRecSize(int[][] map){
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int[] ints : map) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = ints[j] == 0 ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }

        return maxArea;
    }

    public static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] > height[i]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = ( i - k - 1 ) * height[j];
                maxArea = Math.max(curArea, maxArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(curArea, maxArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] map = {{1,0,1,1},{1,1,1,1},{1,1,1,0}};
        int result = maxRecSize(map);
        System.out.println("max rec size: " + result);

    }
}

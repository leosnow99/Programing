package StackAndQueue;

import java.util.Stack;

public class TowerHanoiByStack {
    public static int hanoiProblemRecursion(int num, String left, String mid, String right) {
        if (num < 1) {
            return 0;
        }
        return process(num, left, mid, right, left, right);
    }

    public static int process(int num, String left, String mid, String right, String from, String to) {
        boolean fromOrToMid = from.equals(mid) || to.equals(mid);
        if (num == 1) {
            if (fromOrToMid) {
                System.out.println("Move 1 from " + from + " to " + to);
                return 1;
            } else {
                System.out.println("Move 1 from " + from + " to " + mid);
                System.out.println("Move 1 from " + mid + " to " + to);
                return 2;
            }
        }

        if (fromOrToMid) {
            String another = (from.equals(right) || to.equals(left)) ? right : left;
            int part1 = process(num - 1, left, mid, right, from, another);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + to);
            int part3 = process(num - 1, left, mid, right, another, to);
            return part1 + part2 + part3;
        } else {
            int part1 = process(num - 1, left, mid, right, from, to);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + mid);
            int part3 = process(num - 1, left, mid, right, to, from);
            int part4 = 1;
            System.out.println("Move " + num + " from " + mid + " to " + to);
            int part5 = process(num - 1, left, mid, right, from, to);
            return part1 + part2 + part3 + part4 + part5;
        }
    }

    /**
     * 用栈非递归结局汉诺塔
     */
    public enum Action {
        No, LToM, MToL, MToR, RToM
    }

    public static int hanoiProblemByStack(int num, String left, String mid, String right) {
        Stack<Integer> ls = new Stack<>();
        Stack<Integer> ms = new Stack<>();
        Stack<Integer> rs = new Stack<>();

        ls.push(Integer.MAX_VALUE);
        ms.push(Integer.MAX_VALUE);
        rs.push(Integer.MAX_VALUE);

        for (int i = num; i > 0; i--) {
            ls.push(i);
        }

        Action[] record = {Action.No};
        int step = 0;
        while (rs.size() != num + 1) {
            step += fStackToStack(record, Action.MToL, Action.LToM, ls, ms, left, mid);
            step += fStackToStack(record, Action.LToM, Action.MToL, ms, ls, mid, left);
            step += fStackToStack(record, Action.RToM, Action.MToR, ms, rs, mid, right);
            step += fStackToStack(record, Action.MToR, Action.RToM, rs, ms, right, mid);
        }
        return step;
    }

    public static int fStackToStack(Action[] record, Action preAction, Action nowAction,
                                    Stack<Integer> fStack, Stack<Integer> tStack, String from, String to) {
        if (record[0] != preAction && fStack.peek() < tStack.peek()) {
            tStack.push(fStack.pop());
            System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);
            record[0] = nowAction;
            return 1;
        }
        return 0;
    }
}

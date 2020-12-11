package dp;

/**
 * @author LEOSNOW
 */
public class DanceSteps {

    public static int jumpFloor(int target) {
        if (target < 1) {
            return 0;
        }
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        return jumpFloor(target - 1) + jumpFloor(target - 2);
    }

    public static int jumpFloorHigh(int target) {
        if (target < 1) {
            return 0;
        }
        int a = 1;
        int b = 1;
        for (int i = 0; i < target; i++) {
            a = a + b;
            b = a - b;
        }
        return a;
    }
}

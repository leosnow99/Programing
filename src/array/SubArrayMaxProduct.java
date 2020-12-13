package array;

/**
 * 数组中子数组最大累积
 *
 * @author leosnow
 */
public class SubArrayMaxProduct {

    /**
     * 以i为尾节点考虑
     *
     * @param arr 字符数组
     * @return 积最大的子数组
     */
    public static double getMaxProduct(double[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        double max = arr[0];
        double min = arr[0];
        double endMax;
        double endMin;

        for (int i = 1; i < arr.length; i++) {
            endMax = max * arr[i];
            endMin = min * arr[i];
            max = Math.max(Math.max(endMax, endMin), arr[i]);
            min = Math.min(Math.min(endMax, endMin), arr[i]);
        }

        return max;
    }
}

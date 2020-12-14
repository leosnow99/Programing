package dp;

/**
 * 最长递增子序列
 * 给定数组 arr，返回 arr 的最长递增子序列。
 * arr=[2,1,5,3,6,4,8,9,7]，返回的最长递增子序列为[1,3,4,8,9]
 *
 * @author leosnow
 */
public class LongestIncreasingSubsequence {
    /**
     * 时间复杂度为 O(N2)
     * <ul>
     *     <li>生成长度为 N 的数组 dp，dp[i]表示在以 arr[i]这个数结尾的情况下，arr[0..i]中的最大递 增子序列长度。</li>
     *     <li>对第一个数 arr[0]来说，令 dp[0]=1，接下来从左到右依次算出以每个位置的数结尾的情 况下，最长递增子序列长度。</li>
     *     <li>假设计算到位置 i，求以 arr[i]结尾情况下的最长递增子序列长度，即 dp[i]。如果最长递 增子序列以 arr[i]结尾，那么在 arr[0..i-1]中所有比 arr[i]小的数都可以作为倒数第二个数。在这么 多倒数第二个数的选择中，以哪个数结尾的最大递增子序列更大，就选哪个数作为倒数第二个 数，所以 dp[i]=max{dp[j]+1(0<=j<i，arr[j]<arr[i])}。如果 arr[0..i-1]中所有的数都不比 arr[i]小，令 dp[i]=1 即可，说明以 arr[i]结尾情况下的最长递增子序列只包含 arr[i]。</li>
     * </ul>
     *
     * @param arr 字符数组
     * @return dp数组
     */
    public static int[] getDp(int[] arr) {

        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

        }
        return dp;
    }

    /**
     * 根据求出的 dp 数组得到最长递增子序列
     * 例子： arr=[2,1,5,3,6,4,8,9,7]，求出的数组 dp=[1,1,2,2,3,3,4,5,4]
     * <ul>
     *     <li>遍历 dp 数组，找到最大值以及位置。在本例中，最大值为 5，位置为 7，说明最终的最 长递增子序列的长度为 5，并且应该以 arr[7]这个数（arr[7]==9）结尾</li>
     *     <li>从 arr 数组的位置 7 开始从右向左遍历。如果对某一个位置 i，既有 arr[i]<arr[7]，又有 dp[i]==dp[7]-1，说明 arr[i]可以作为最长递增子序列的倒数第二个数。在本例中，arr[6]<arr[7]， 并且 dp[6]==dp[7]-1，所以 8 应该作为最长递增子序列的倒数第二个数</li>
     *     <li>从 arr 数组的位置 6 开始继续向左遍历，按照同样的过程找到倒数第三个数。在本例中， 位置 5 满足 arr[5]<arr[6]，并且 dp[5]==dp[6]-1，同时位置 4 也满足。选 arr[5]或者 arr[4]作为倒 数第三个数都可以</li>
     * </ul>
     *
     * @param arr 字符数组
     * @param dp  dp数组
     * @return 最长递增子序列
     */
    public static int[] generateLIS(int[] arr, int[] dp) {
        int len = 0;
        int cur = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > len) {
                dp[i] = len;
                cur = i;
            }
        }

        int[] lis = new int[len];
        lis[--len] = arr[cur];
        for (int i = cur; i >= 0; i--) {
            if (arr[i] < arr[cur] && dp[i] == dp[cur] - 1) {
                lis[--len] = arr[i];
                cur = i;
            }
        }

        return lis;
    }

    public static int[] getLongestIncreasingSub(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        if (arr.length == 1) {
            return arr;
        }
        final int[] dp = getDp(arr);
        return generateLIS(arr, dp);
    }

    /**
     * <hr>
     * dp 数组过程的时间复杂度为 O(N2)
     * <p>计算 dp 数组过程的时间复杂度为 O(N2)，根据 dp 数组得到最长递增子序列过程的 时间复杂度为 O(N)，所以整个过程的时间复杂度为 O(N2 )。如果让时间复杂度达到 O(NlogN)，只 要让计算 dp 数组的过程达到时间复杂度 O(NlogN)即可，之后根据 dp 数组生成最长递增子序列 的过程是一样的。</p>
     * <p>时间复杂度 O(NlogN)生成 dp 数组的过程是利用二分查找来进行的优化。先生成一个长度为 N 的数组 ends，初始时 ends[0]=arr[0]，其他位置上的值为 0。生成整型变量 right，初始时 right=0。 在从左到右遍历 arr 数组的过程中，求解 dp[i]的过程需要使用 ends 数组和 right 变量，所以这里 解释一下其含义。遍历的过程中，ends[0..right]为有效区，ends[right+1..N-1]为无效区。对有效 区上的位置 b，如果有 ends[b]==c，则表示遍历到目前为止，在所有长度为 b+1 的递增序列中， 最小的结尾数是 c。无效区的位置则没有意义。</p>
     *
     * @param arr 字符数组
     * @return dp数组
     */
    public static int[] getDpHigh(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        dp[0] = 1;
        ends[0] = arr[0];
        int right = 0;
        int leftIndex;
        int rightIndex;
        int mid;

        for (int i = 1; i < arr.length; i++) {
            leftIndex = 0;
            rightIndex = right;
            while (leftIndex < rightIndex) {
                mid = leftIndex + (rightIndex - leftIndex) >> 2;
                if (arr[i] > ends[mid]) {
                    leftIndex = mid + 1;
                } else {
                    rightIndex = mid - 1;
                }
            }
            right = Math.max(right, leftIndex);
            ends[leftIndex] = arr[i];
            dp[i] = leftIndex + 1;
        }
        return dp;
    }

    public static int[] getLongestIncreasingSubHigh(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        if (arr.length == 1) {
            return arr;
        }
        final int[] dp = getDpHigh(arr);
        return generateLIS(arr, dp);
    }
}

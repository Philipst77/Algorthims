
public class ShortestSubarraySlidingWindow {

    public static int ShortestSubArray(int[] nums, int target) {
        int L = 0;
        int total = 0;
        int length = Integer.MAX_VALUE;

        for (int R = 0; R < nums.length; R++) {
            total += nums[R];
            while (total >= target) {
                length = Math.min(R - L + 1);
                total -= nums[L];
                L++;
            }
        }

        if (length == Integer.MAX_VALUE) {
            return 0;
        }

        return length;
    }
}
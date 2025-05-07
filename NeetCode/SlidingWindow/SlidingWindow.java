package SlidingWindow;

public class SlidingWindow {

    public static int longestSubarray(int[] nums) {
        int length = 0;
        int L = 0;

        for (int R = 0; R < nums.length; R++) {
            if (nums[L] != nums[R]) {
                L = R;
            }
            length = Math.max(length, R - L + 1);
        }
        System.out.print(length);
        return length;

    }

    public static void main(String[] args) {

        int[] nums = { 1, 2, 3, 3, 5, 6 };

        longestSubarray(nums);

    }
}

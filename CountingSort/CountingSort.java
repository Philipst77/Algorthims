package CountingSort;
public class CountingSort {
    public static void countingSort(int[] arr) {
        int max = arr[0];
        int min = arr[0];

        // Find the maximum and minimum values
        for (int num : arr) {
            if (num > max) max = num;
            if (num < min) min = num;
        }

        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[arr.length];

        // Count each element
        for (int num : arr) {
            count[num - min]++;
        }

        // Cumulative count
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Place the elements in sorted order
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        // Copy to original array
        System.arraycopy(output, 0, arr, 0, arr.length);
    }
}
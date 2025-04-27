package RadixSort;

import java.util.ArrayList;
import java.util.Collections;

public class RadixSortArrayList {
    public static void radixSort(ArrayList<Integer> arr) {
        int max = getMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private static int getMax(ArrayList<Integer> arr) {
        int max = arr.get(0);
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }

    private static void countingSortByDigit(ArrayList<Integer> arr, int exp) {
        ArrayList<Integer> output = new ArrayList<>(Collections.nCopies(arr.size(), 0));
        int[] count = new int[10];

        for (int num : arr) {
            count[(num / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.size() - 1; i >= 0; i--) {
            int index = count[(arr.get(i) / exp) % 10] - 1;
            output.set(index, arr.get(i));
            count[(arr.get(i) / exp) % 10]--;
        }

        for (int i = 0; i < arr.size(); i++) {
            arr.set(i, output.get(i));
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Collections.addAll(arr, 170, 45, 75, 90, 802, 24, 2, 66);

        radixSort(arr);

        System.out.println("After Sorting: " + arr);
    }
}

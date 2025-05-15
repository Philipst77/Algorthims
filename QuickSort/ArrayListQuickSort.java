package QuickSort;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListQuickSort {

    public static void quickSort(ArrayList<Integer> arr, int s, int e) {
        if (s >= e) { // ✅ Base case: Stop when there's 1 or 0 elements
            return;
        }

        int pivot = arr.get(e); // ✅ Pick last element as pivot
        int left = s;           // ✅ Pointer for left side

        // ✅ Partition: Move elements smaller than pivot to the left
        for (int i = s; i < e; i++) {
            if (arr.get(i) < pivot) {
                int tmp = arr.get(left);
                arr.set(left, arr.get(i));
                arr.set(i, tmp);
                left++;
            }
        }

        // ✅ Move pivot to correct position
        arr.set(e, arr.get(left));
        arr.set(left, pivot);

        // ✅ Quick sort left half
        quickSort(arr, s, left - 1);

        // ✅ Quick sort right half
        quickSort(arr, left + 1, e);
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1));

        quickSort(arr, 0, arr.size() - 1); // ✅ Call QuickSort

        System.out.println("After Sorting: " + arr); // ✅ Print sorted ArrayList
    }
}

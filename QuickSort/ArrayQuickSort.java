package QuickSort;

import java.util.Arrays;  // ✅ Required import for Arrays.toString()

public class ArrayQuickSort {

    public static void quickSort(int[] arr, int s, int e) {
        if (s >= e) { // ✅ Base case: Stop when there's 1 or 0 elements
            return;
        }

        int pivot = arr[e];  // ✅ Pick last element as pivot
        int left = s;        // ✅ Pointer for left side

        // ✅ Partition: Move elements smaller than pivot to the left
        for (int i = s; i < e; i++) {
            if (arr[i] < pivot) {
                int tmp = arr[left];
                arr[left] = arr[i];
                arr[i] = tmp;
                left++;
            }
        }

        // ✅ Move pivot to correct position
        arr[e] = arr[left];
        arr[left] = pivot;

        // ✅ Quick sort left half
        quickSort(arr, s, left - 1);

        // ✅ Quick sort right half
        quickSort(arr, left + 1, e);
    }

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};


        quickSort(arr, 0, arr.length - 1); // ✅ Call QuickSort

        System.out.println("After Sorting: " + Arrays.toString(arr)); // ✅ Print sorted array
    }
}

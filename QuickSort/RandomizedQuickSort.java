package QuickSort;
import java.util.Random;
import java.util.Arrays;
public class RandomizedQuickSort {

    public static void quickSort(int[] arr, int s, int e) {
        if (s >= e) {
            return; // Base case: stop when there's 1 or 0 elements
        }

        int pivotIndex = new Random().nextInt(e - s + 1) + s; // Pick a random pivot
        int pivot = arr[pivotIndex];

        // Swap pivot with last element
        int temp = arr[pivotIndex];
        arr[pivotIndex] = arr[e];
        arr[e] = temp;

        int left = s;

        // Partitioning step
        for (int i = s; i < e; i++) {
            if (arr[i] < pivot) {
                temp = arr[left];
                arr[left] = arr[i];
                arr[i] = temp;
                left++;
            }
        }

        // Move pivot to correct position
        arr[e] = arr[left];
        arr[left] = pivot;

        // Recursive calls for left and right partitions
        quickSort(arr, s, left - 1);
        quickSort(arr, left + 1, e);
    }

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};

        quickSort(arr, 0, arr.length - 1); // Call Randomized QuickSort

        System.out.println("After Sorting: " + Arrays.toString(arr)); // Print sorted array
    }
}

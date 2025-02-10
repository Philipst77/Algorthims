package InsertionSort;

import java.util.ArrayList;

public class ArrayListInsertionSort {

    public static void sorter(ArrayList<Integer> arr) {
        for (int i = 1; i < arr.size(); i++) {
            int key = arr.get(i); // ✅ Store the current element
            int j = i - 1;

            // ✅ Move elements that are greater than `key` to the right
            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j)); // Shift element to the right
                j--;
            }
            arr.set(j + 1, key); // ✅ Place `key` at its correct position
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(9);
        arr.add(8);
        arr.add(7);
        arr.add(6);
        arr.add(5);
        arr.add(3);
        arr.add(2);
        arr.add(1);

        System.out.println("Before Sorting: " + arr);

        sorter(arr); // ✅ Call sorter method

        System.out.println("After Sorting: " + arr); // ✅ Print sorted array
    }
}

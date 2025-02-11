package BucketSort;

public class BucketSort {

    public static int[] bucketSort(int[] arr) {
        int[] counts = { 0, 0, 0 }; // array that holds frequency of occurences of each num in array where tyring to
                                    // sort

        for (int n : arr) { // going thorugh each number in array
            counts[n] += 1; // based on that number value well get that value and go that index in count and
                            // increment it by 1 so where
            // keep track of frequency basically
        }

        int i = 0;
        for (int n = 0; n < counts.length; n++) { // going over counts array
            for (int j = 0; j < counts[n]; j++) { // goign onver occurences of numbers
                arr[i] = n; // setting occurences to array indexes
                i++;

            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 0, 2, 1, 1, 0 };

        System.out.println(bucketSort(arr));

    }
}

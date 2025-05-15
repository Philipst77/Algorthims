package MergeSort;
public class ArrayMergeSort {




    public static void recursiveMerge(int []arr,int l, int r){

        if ( l < r){ // ensures that we keep spliting as long as there two elements
            int m = l + ( r - l )  / 2;  // get middle index to keep spliting array into sub array left and right the + l in front of everything avoids 
            //integer overflow
            
            recursiveMerge(arr, l, m);  // recursivley sort left subarray
            recursiveMerge(arr, m+1, r);  // recursivley sort right subarray

            merge(arr, l, m, r);  // merge sorted halfs

        }
    }


    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    public static int[] merge(int[] arr, int l, int m, int r) {

        // Find lengths of two subarrays to be merged
        int length1 = m - l + 1; // middle index - left subarray starting index +1 because the +1 takes into
                                 // account the 0 index because were taking lenght here
        int length2 = r - m; // middle index - right index the right index is the ending index of the right
                             // subarray middle -r gives you size of right subarray

        // Create temp arrays
        int L[] = new int[length1]; // make left subarray to be the length of lenght 1 which we caculated
        int R[] = new int[length2]; // make right subarray to be the lenght of length2 which we calculated

        for (int i = 0; i < length1; i++) { // copying left half of main array into the the right subarray and were
                                            // doing this of lenght1
            // so this means that we are going from 0 index to the middle index which is the
            // left subarray length which is why
            // we are looping as long as i < length 1
            L[i] = arr[l + i]; // left subarray should be from position l to m thats why its l +i
        }

        for (int j = 0; j < length2; j++) {
            R[j] = arr[m + 1 + j]; // from middle index +1 to j the current counter of right subarray to correctl
                                   // put in the right half
            // of orignial array into the rigth subarray basically we start from the middle
            // and keep going to the rigth boundary of main
            // array until we fit all that into the rigth subarray
        }

        // initial indexes of left and right sub-arrays
        int i = 0; // index for left
        int j = 0; // index for right
        int k = l; // Initial index of merged subarray array

        // Merge the two sorted halfs into the original array
        while (i < length1 && j < length2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        // One of the halfs will have elements remaining
        // these two while loops are here because they take care of the rather common
        // case where one subarray is longer or differnt size then
        // the other leading to one getting exhausted before the other leading to left
        // over elements that havent been compared
        // these two wihle loops take care of that case and put them in the array

        while (i < length1) { //  
            arr[k] = L[i];   // we use array k instead of i because remeber the k is keeping track of the merged subarray
            // so we use that counter to pick up off of where we where in teh merging process before we exhausted one of the two subarrays
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < length2) {
            arr[k] = R[j];
            j++;
            k++;
        }
        return arr;
    }





    public static void main(String[] args){

        int [] arr = {9,8,7,6,5,4,3,2,1};

        recursiveMerge(arr, 0, arr.length -1 );

        for(int n : arr){
            System.out.print(n + " ");
        }
    }
}
package InsertionSort;
public class ArrayInsertionSort {
    

    public static int[] insertmethod(int[] arr){

        for(int i =1; i<arr.length; i++){
            int j = i-1;
            while(j >= 0 && arr[j+1] < arr[j]){
                int temp = arr[j+1];
                arr[j+1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }

        return arr;
    }


public static void main(String[] args) {

    int [] arr = {3,4,6,7,4,9};

    arr = insertmethod(arr);

    for(int n : arr){
        System.out.print(n + " ");
    }
}

}



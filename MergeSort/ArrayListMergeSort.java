package MergeSort;
import java.util.ArrayList;

class Pair {
    public int key;
    public String value;

    public Pair(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + key + ", \"" + value + "\")";  // this overhere just overwrites the tostring method thats built it so that when we print the objects we 
        // can actaully get something readable
    }
}

public class ArrayListMergeSort {

    public void mergeHelper(ArrayList<Pair> arr) {
        mergerHelper(arr, 0, arr.size() - 1);
    }

    public void mergerHelper(ArrayList<Pair> arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            mergerHelper(arr, l, m);
            mergerHelper(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    public static void merge(ArrayList<Pair> arr, int l, int m, int r) {
        ArrayList<Pair> L = new ArrayList<>(arr.subList(l, m + 1));
        ArrayList<Pair> R = new ArrayList<>(arr.subList(m + 1, r + 1));

        int i = 0, j = 0, k = l;

        while (i < L.size() && j < R.size()) {
            if (L.get(i).key <= R.get(j).key) {
                arr.set(k, L.get(i));
                i++;
            } else {
                arr.set(k, R.get(j));
                j++;
            }
            k++;
        }

        while (i < L.size()) {
            arr.set(k, L.get(i));
            i++;
            k++;
        }

        while (j < R.size()) {
            arr.set(k, R.get(j)); 
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        ArrayList<Pair> pairs = new ArrayList<>();
        pairs.add(new Pair(9, "apple"));
        pairs.add(new Pair(8, "banana"));
        pairs.add(new Pair(7, "cherry"));
        pairs.add(new Pair(6, "date"));
        pairs.add(new Pair(5, "elderberry"));
        pairs.add(new Pair(4, "fig"));
        pairs.add(new Pair(3, "grape"));
        pairs.add(new Pair(2, "honeydew"));
        pairs.add(new Pair(1, "kiwi"));

        ArrayListMergeSort sorter = new ArrayListMergeSort();
        sorter.mergeHelper(pairs);

        for (Pair p : pairs) {
            System.out.print(p + " ");
        }
    }
}

import java.util.*;

public class QuickSortTreePrinter {
    static class Node {
        int[] subarray;
        int pivot;
        Node left, right;

        Node(int[] subarray, int pivot) {
            this.subarray = subarray;
            this.pivot = pivot;
        }

        String getLabel() {
            return Arrays.toString(subarray) + " (pivot: " + pivot + ")";
        }
    }

    public static void main(String[] args) {
        int[] arr = {9,-3,5,2,6,8,-6,1,3};
        Node root = quickSortTree(arr.clone(), 0, arr.length - 1);
        System.out.println("QuickSort Tree (pivot = rightmost each time):\n");
        List<StringBuilder> output = new ArrayList<>();
        buildVisualTree(root, 0, 0, output);
        for (StringBuilder line : output) {
            System.out.println(line);
        }
    }

    static Node quickSortTree(int[] arr, int s, int e) {
        if (e < s) return null;

        int[] current = Arrays.copyOfRange(arr, s, e + 1);
        int pivot = arr[e];

        int left = s;
        for (int i = s; i < e; i++) {
            if (arr[i] < pivot) {
                int temp = arr[i];
                arr[i] = arr[left];
                arr[left] = temp;
                left++;
            }
        }

        arr[e] = arr[left];
        arr[left] = pivot;

        Node node = new Node(current, pivot);
        node.left = quickSortTree(arr, s, left - 1);
        node.right = quickSortTree(arr, left + 1, e);
        return node;
    }

    static int buildVisualTree(Node node, int depth, int offset, List<StringBuilder> output) {
        if (node == null) return 0;

        String label = node.getLabel();
        int labelWidth = label.length();

        while (output.size() <= depth * 2) {
            output.add(new StringBuilder());
            output.add(new StringBuilder());
        }

        int leftWidth = buildVisualTree(node.left, depth + 1, offset, output);
int spacingAdjustment = (depth == 1) ? -1 : 0;
int rightWidth = buildVisualTree(node.right, depth + 1, offset + leftWidth + labelWidth + spacingAdjustment, output);


        int currPos = offset + leftWidth;
        StringBuilder labelLine = output.get(depth * 2);
        padTo(labelLine, currPos);
        labelLine.append(label);

        StringBuilder arrowLine = output.get(depth * 2 + 1);
        padTo(arrowLine, currPos);
        if (node.left != null) arrowLine.append("↙");
        padTo(arrowLine, currPos + labelWidth - 1);
        if (node.right != null) arrowLine.append("↘");

        return leftWidth + labelWidth + rightWidth + spacingAdjustment;
    }

    static void padTo(StringBuilder sb, int length) {
        while (sb.length() < length) {
            sb.append(' ');
        }
    }
}

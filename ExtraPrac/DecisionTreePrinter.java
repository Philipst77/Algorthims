import java.util.*;

public class DecisionTreePrinter {
    static class TreeNode {
        String value;
        TreeNode left, right;

        TreeNode(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1};
        TreeNode root = buildInsertionSortTree(arr);
        printBinaryTree(root);
    }

    static TreeNode buildInsertionSortTree(int[] arr) {
        TreeNode root = new TreeNode("1:2 (" + arr[0] + " vs " + arr[1] + ")");

        // Case: arr[0] <= arr[1], compare with arr[2]
        TreeNode left = new TreeNode("2:3 (" + arr[1] + " vs " + arr[2] + ")");
        left.left = new TreeNode("⟨" + arr[0] + "," + arr[1] + "," + arr[2] + "⟩");
        left.right = new TreeNode("1:3 (" + arr[0] + " vs " + arr[2] + ")");
        left.right.left = new TreeNode("⟨" + arr[0] + "," + arr[2] + "," + arr[1] + "⟩");
        left.right.right = new TreeNode("⟨" + arr[2] + "," + arr[0] + "," + arr[1] + "⟩");

        // Case: arr[0] > arr[1], compare arr[0] with arr[2]
        TreeNode right = new TreeNode("1:3 (" + arr[0] + " vs " + arr[2] + ")");
        right.left = new TreeNode("⟨" + arr[1] + "," + arr[0] + "," + arr[2] + "⟩");
        right.right = new TreeNode("2:3 (" + arr[1] + " vs " + arr[2] + ")");
        right.right.left = new TreeNode("⟨" + arr[1] + "," + arr[2] + "," + arr[0] + "⟩");
        right.right.right = new TreeNode("⟨" + arr[2] + "," + arr[1] + "," + arr[0] + "⟩");

        root.left = left;
        root.right = right;
        return root;
    }

    static void printBinaryTree(TreeNode root) {
        List<StringBuilder> lines = new ArrayList<>();
        int width = display(root, lines, 0, 0, 0);
        for (StringBuilder line : lines) {
            System.out.println(line.toString());
        }
    }

    static int display(TreeNode node, List<StringBuilder> lines, int level, int leftMargin, int offset) {
        if (node == null) return 0;

        while (lines.size() <= level * 2) {
            lines.add(new StringBuilder());
        }

        String val = node.value;
        int nodeWidth = val.length();

        int left = display(node.left, lines, level + 1, leftMargin, offset);
        int right = display(node.right, lines, level + 1, leftMargin + left + nodeWidth, offset + left + nodeWidth);

        int pos = offset + left;
        StringBuilder line = lines.get(level * 2);
        padTo(line, pos);
        line.append(val);

        if (node.left != null) {
            StringBuilder branch = lines.get(level * 2 + 1);
            padTo(branch, pos - 1);
            branch.append("/");
        }
        if (node.right != null) {
            StringBuilder branch = lines.get(level * 2 + 1);
            padTo(branch, pos + nodeWidth);
            branch.append("\\");
        }

        return left + nodeWidth + right;
    }

    static void padTo(StringBuilder sb, int length) {
        while (sb.length() < length) sb.append(" ");
    }
}
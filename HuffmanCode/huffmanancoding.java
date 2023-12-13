package HuffmanCode;

import java.util.PriorityQueue;
import java.util.Comparator;

class huffmanNode {
    int item;
    char c;
    huffmanNode left;
    huffmanNode right;
}

class ImplementComparator implements Comparator<huffmanNode> {
    public int compare(huffmanNode x, huffmanNode y) {
        return x.item - y.item;
    }
}

public class huffmanancoding {
    public static void printCode(huffmanNode root, String s) {
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println("  " + root.c + "   | " + s);

            return;
        }
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    public static void main(String[] args) {
        int n = 7;
        char[] charArray = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int[] charfreq = { 2, 10, 3, 20, 7, 11, 4 };

        PriorityQueue<huffmanNode> q = new PriorityQueue<huffmanNode>(n, new ImplementComparator());

        for (int i = 0; i < n; i++) {
            huffmanNode hn = new huffmanNode();
            hn.c = charArray[i];
            hn.item = charfreq[i];
            hn.left = null;
            hn.right = null;
            q.add(hn);
        }

        huffmanNode root = null;
        while (q.size() > 1) {
            huffmanNode x = q.peek();
            q.poll();

            huffmanNode y = q.peek();
            q.poll();

            huffmanNode f = new huffmanNode();

            f.item = x.item + y.item;
            f.c = '-';
            f.left = x;
            f.right = y;

            root = f;
            q.add(f);
        }
        System.out.println(" Char | Code ");
        printCode(root, "");

    }

}

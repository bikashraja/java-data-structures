package datastructures.redblacktree;

public class RBTreeDemo {

    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        int[] values = {10, 20, 30, 15, 25, 5, 1};

        for (int v : values) {
            tree.insert(v);
        }

        System.out.println("In-order: " + tree.inOrder());
        System.out.println("Contains 15? " + tree.contains(15));
        System.out.println("Contains 100? " + tree.contains(100));

        System.out.println("Size: " + tree.size());
        System.out.println("Valid RB Tree? " + tree.isValidRedBlackTree());

        System.out.println("Traversal using iterator:");
        for (int v : tree) {
            System.out.print(v + " ");
        }
    }
}

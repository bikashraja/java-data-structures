package datastructures.binarysearchtree;

public class BSTDemo {

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Insert elements
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) {
            bst.insert(v);
        }

        System.out.println("=== Initial Tree ===");
        System.out.println("In-order (sorted): " + bst.inOrder());
        System.out.println("Pre-order: " + bst.preOrder());
        System.out.println("Post-order: " + bst.postOrder());
        System.out.println("Level-order: " + bst.levelOrder());

        // Basic operations
        System.out.println("\n=== Basic Operations ===");
        System.out.println("Contains 40? " + bst.contains(40));
        System.out.println("Contains 100? " + bst.contains(100));

        System.out.println("Min: " + bst.findMin());
        System.out.println("Max: " + bst.findMax());
        System.out.println("Height: " + bst.height());
        System.out.println("Size: " + bst.size());

        // Deletion cases
        System.out.println("\n=== Deletions ===");

        // Case 1: Leaf node
        bst.delete(20);
        System.out.println("After deleting leaf (20): " + bst.inOrder());

        // Case 2: Node with one child
        bst.delete(30);
        System.out.println("After deleting node with one child (30): " + bst.inOrder());

        // Case 3: Node with two children
        bst.delete(50);
        System.out.println("After deleting node with two children (50): " + bst.inOrder());

        // Validation
        System.out.println("\n=== Validation ===");
        System.out.println("Is valid BST? " + bst.isValidBST());

        // Iterator demo
        System.out.println("\n=== Iterator (for-each loop) ===");
        for (Integer value : bst) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}

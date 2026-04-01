package datastructures.binarysearchtree;

import java.util.*;

/**
 * A generic Binary Search Tree (BST) implementation.
 *
 * <p>This data structure stores elements in a sorted tree structure such that:
 * for every node:
 * <ul>
 *     <li>All elements in the left subtree are smaller</li>
 *     <li>All elements in the right subtree are larger</li>
 * </ul>
 *
 * <p>This implementation does NOT self-balance. Worst-case time complexity is O(n).
 *
 * @param <T> the type of elements stored in the tree
 */
public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T> {

    /**
     * Tree node class.
     */
    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root;
    private int size;

    /**
     * Inserts a value into the BST.
     *
     * @param value the value to insert
     * @throws IllegalArgumentException if value is null
     */
    public void insert(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        root = insert(root, value);
    }

    private Node<T> insert(Node<T> node, T value) {
        if (node == null) {
            size++;
            return new Node<>(value);
        }

        int cmp = value.compareTo(node.value);

        if (cmp < 0) {
            node.left = insert(node.left, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, value);
        }
        // duplicates are ignored

        return node;
    }

    /**
     * Checks whether a value exists in the BST.
     *
     * @param value the value to search
     * @return true if found, false otherwise
     */
    public boolean contains(T value) {
        return contains(root, value);
    }

    private boolean contains(Node<T> node, T value) {
        if (node == null) return false;

        int cmp = value.compareTo(node.value);

        if (cmp < 0) return contains(node.left, value);
        if (cmp > 0) return contains(node.right, value);

        return true;
    }

    /**
     * Deletes a value from the BST.
     *
     * @param value the value to delete
     */
    public void delete(T value) {
        root = delete(root, value);
    }

    private Node<T> delete(Node<T> node, T value) {
        if (node == null) return null;

        int cmp = value.compareTo(node.value);

        if (cmp < 0) {
            node.left = delete(node.left, value);
        } else if (cmp > 0) {
            node.right = delete(node.right, value);
        } else {
            // node to delete found
            size--;

            // case 1: no child
            if (node.left == null && node.right == null) {
                return null;
            }

            // case 2: one child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // case 3: two children
            Node<T> successor = findMinNode(node.right);
            node.value = successor.value;
            node.right = delete(node.right, successor.value);
        }

        return node;
    }

    /**
     * Returns the minimum value in the BST.
     *
     * @return the smallest element
     * @throws NoSuchElementException if tree is empty
     */
    public T findMin() {
        if (root == null) throw new NoSuchElementException();
        return findMinNode(root).value;
    }

    private Node<T> findMinNode(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Returns the maximum value in the BST.
     *
     * @return the largest element
     */
    public T findMax() {
        if (root == null) throw new NoSuchElementException();
        Node<T> node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.value;
    }

    /**
     * Returns the height of the tree.
     *
     * @return height (-1 for empty tree)
     */
    public int height() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Validates that the tree satisfies BST properties.
     *
     * @return true if valid BST
     */
    public boolean isValidBST() {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(Node<T> node, T min, T max) {
        if (node == null) return true;

        if (min != null && node.value.compareTo(min) <= 0) return false;
        if (max != null && node.value.compareTo(max) >= 0) return false;

        return isValidBST(node.left, min, node.value)
                && isValidBST(node.right, node.value, max);
    }

    /**
     * In-order traversal (sorted order).
     *
     * @return list of elements in sorted order
     */
    public List<T> inOrder() {
        List<T> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node<T> node, List<T> result) {
        if (node == null) return;
        inOrder(node.left, result);
        result.add(node.value);
        inOrder(node.right, result);
    }

    /**
     * Pre-order traversal.
     */
    public List<T> preOrder() {
        List<T> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    private void preOrder(Node<T> node, List<T> result) {
        if (node == null) return;
        result.add(node.value);
        preOrder(node.left, result);
        preOrder(node.right, result);
    }

    /**
     * Post-order traversal.
     */
    public List<T> postOrder() {
        List<T> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    private void postOrder(Node<T> node, List<T> result) {
        if (node == null) return;
        postOrder(node.left, result);
        postOrder(node.right, result);
        result.add(node.value);
    }

    /**
     * Level-order traversal (Breadth-First Search).
     */
    public List<T> levelOrder() {
        List<T> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            result.add(current.value);

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }

        return result;
    }

    /**
     * Returns the number of elements in the tree.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the tree is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns an iterator over the BST (in-order).
     */
    @Override
    public Iterator<T> iterator() {
        return inOrder().iterator();
    }
}

package datastructures.redblacktree;

import java.util.*;

/**
 * A generic Red-Black Tree implementation.
 *
 * <p>A Red-Black Tree is a self-balancing Binary Search Tree that guarantees
 * O(log n) time complexity for insertion and search.
 *
 * <p>Properties:
 * <ul>
 *     <li>Each node is either red or black</li>
 *     <li>The root is black</li>
 *     <li>No two consecutive red nodes</li>
 *     <li>Every path from a node to its leaves has the same number of black nodes</li>
 * </ul>
 *
 * @param <T> the type of elements stored in the tree
 */
public class RedBlackTree<T extends Comparable<T>> implements Iterable<T> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        T value;
        Node left, right, parent;
        boolean color;

        Node(T value, boolean color, Node parent) {
            this.value = value;
            this.color = color;
            this.parent = parent;
        }
    }

    private Node root;
    private int size;
    private boolean inserted;

    /**
     * Inserts a value into the tree.
     *
     * @param value value to insert
     */
    public void insert(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        inserted = false;
        Node node = new Node(value, RED, null);
        root = bstInsert(root, node);
        if (inserted) {
            fixInsert(node);
            size++;
        }
    }

    private Node bstInsert(Node root, Node node) {
        if (root == null) {
            inserted = true;
            return node;
        }

        int cmp = node.value.compareTo(root.value);

        if (cmp < 0) {
            root.left = bstInsert(root.left, node);
            root.left.parent = root;
        } else if (cmp > 0) {
            root.right = bstInsert(root.right, node);
            root.right.parent = root;
        }

        return root;
    }

    /**
     * Fixes Red-Black Tree violations after insertion.
     */
    private void fixInsert(Node node) {
        while (node != root && colorOf(parentOf(node)) == RED) {

            Node parent = parentOf(node);
            Node grandparent = parentOf(parent);

            if (parent == grandparent.left) {
                Node uncle = grandparent.right;

                // Case 1: uncle is RED
                if (colorOf(uncle) == RED) {
                    setColor(parent, BLACK);
                    setColor(uncle, BLACK);
                    setColor(grandparent, RED);
                    node = grandparent;
                } else {
                    // Case 2: triangle
                    if (node == parent.right) {
                        node = parent;
                        rotateLeft(node);
                    }
                    // Case 3: line
                    setColor(parentOf(node), BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    rotateRight(parentOf(parentOf(node)));
                }
            } else {
                Node uncle = grandparent.left;

                if (colorOf(uncle) == RED) {
                    setColor(parent, BLACK);
                    setColor(uncle, BLACK);
                    setColor(grandparent, RED);
                    node = grandparent;
                } else {
                    if (node == parent.left) {
                        node = parent;
                        rotateRight(node);
                    }
                    setColor(parentOf(node), BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    rotateLeft(parentOf(parentOf(node)));
                }
            }
        }
        root.color = BLACK;
    }

    /**
     * Left rotation.
     */
    private void rotateLeft(Node x) {
        Node y = x.right;

        x.right = y.left;
        if (y.left != null) y.left.parent = x;

        y.parent = x.parent;

        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;

        y.left = x;
        x.parent = y;
    }

    /**
     * Right rotation.
     */
    private void rotateRight(Node y) {
        Node x = y.left;

        y.left = x.right;
        if (x.right != null) x.right.parent = y;

        x.parent = y.parent;

        if (y.parent == null) root = x;
        else if (y == y.parent.left) y.parent.left = x;
        else y.parent.right = x;

        x.right = y;
        y.parent = x;
    }

    /**
     * Checks if the tree contains a value.
     */
    public boolean contains(T value) {
        Node current = root;

        while (current != null) {
            int cmp = value.compareTo(current.value);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else return true;
        }
        return false;
    }

    /**
     * In-order traversal (sorted).
     */
    public List<T> inOrder() {
        List<T> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node node, List<T> result) {
        if (node == null) return;
        inOrder(node.left, result);
        result.add(node.value);
        inOrder(node.right, result);
    }

    /**
     * Returns number of elements.
     */
    public int size() {
        return size;
    }

    /**
     * Validates Red-Black Tree properties.
     */
    public boolean isValidRedBlackTree() {
        if (root == null) return true;
        if (root.color != BLACK) return false;
        return validate(root) != -1;
    }

    private int validate(Node node) {
        if (node == null) return 1;

        // red violation
        if (node.color == RED) {
            if (colorOf(node.left) == RED || colorOf(node.right) == RED) {
                return -1;
            }
        }

        int leftBlackHeight = validate(node.left);
        int rightBlackHeight = validate(node.right);

        if (leftBlackHeight == -1 || rightBlackHeight == -1) return -1;
        if (leftBlackHeight != rightBlackHeight) return -1;

        return leftBlackHeight + (node.color == BLACK ? 1 : 0);
    }

    private Node parentOf(Node node) {
        return node == null ? null : node.parent;
    }

    private boolean colorOf(Node node) {
        return node == null ? BLACK : node.color;
    }

    private void setColor(Node node, boolean color) {
        if (node != null) node.color = color;
    }

    /**
     * Iterator (in-order).
     */
    @Override
    public Iterator<T> iterator() {
        return inOrder().iterator();
    }
}

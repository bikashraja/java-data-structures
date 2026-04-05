package datastructures.redblacktree;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RedBlackTreeTest {

    @Test
    void testInsertAndContains() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        assertTrue(tree.contains(10));
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(15));
        assertFalse(tree.contains(20));
    }

    @Test
    void testInOrderTraversalSorted() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(15);

        List<Integer> expected = Arrays.asList(5, 10, 15, 20);
        assertEquals(expected, tree.inOrder());
    }

    @Test
    void testSize() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        tree.insert(1);
        tree.insert(2);
        tree.insert(3);

        assertEquals(3, tree.size());
    }

    @Test
    void testInsertNullThrowsException() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        assertThrows(IllegalArgumentException.class, () -> tree.insert(null));
    }

    @Test
    void testRedBlackPropertiesAfterInsertions() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        List<Integer> values = Arrays.asList(10, 20, 30, 15, 25, 5, 1);

        for (int v : values) {
            tree.insert(v);
            assertTrue(tree.isValidRedBlackTree(), "Tree violated RB properties after inserting " + v);
        }
    }

    @Test
    void testLargeRandomInsertions() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        Random rand = new Random();

        for (int i = 0; i < 1000; i++) {
            tree.insert(rand.nextInt(10000));
        }

        assertTrue(tree.isValidRedBlackTree());
    }

    @Test
    void testIteratorMatchesInOrder() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        tree.insert(10);
        tree.insert(5);
        tree.insert(20);

        List<Integer> fromIterator = new ArrayList<>();
        for (int val : tree) {
            fromIterator.add(val);
        }

        assertEquals(tree.inOrder(), fromIterator);
    }

    @Test
    void testDuplicateInsertDoesNotIncreaseSize() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        tree.insert(10);
        tree.insert(10); // duplicate

        assertEquals(1, tree.size());
    }
}
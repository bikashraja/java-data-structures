package datastructures.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A generic implementation of a doubly linked list.
 *
 * <p>This list maintains references to both the head and tail nodes,
 * allowing constant time insertion and removal at the beginning and
 * end of the list.</p>
 *
 * <p>The list supports indexed access, bidirectional node linking,
 * and provides a fail-fast iterator that detects structural
 * modifications during iteration.</p>
 *
 * <p>Index-based operations run in O(n) time, while insertions and
 * removals at the ends run in O(1) time.</p>
 *
 * @param <T> the type of elements stored in the list
 */
public class DoublyLinkedList<T> implements Iterable<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;
    private int modCount;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.modCount = 0;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);

        if (!isEmpty()) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else {
            head = newNode;
            tail = head;
        }

        modCount++;
        size++;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);

        if (isEmpty()) {
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        modCount++;
        size++;
    }

    public void add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        if (index == size) {
            addLast(data);
            return;
        }

        Node<T> newNode = new Node<>(data);
        Node<T> current = getNode(index);

        newNode.prev = current.prev;
        newNode.next = current;
        current.prev.next = newNode;
        current.prev = newNode;

        modCount++;
        size++;
    }

    public void removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (head == tail) {
            head = null;
            tail = null;
            size--;
            modCount++;
            return;
        }

        head = head.next;
        head.prev = null;

        modCount++;
        size--;
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (head == tail) {
            head = null;
            tail = null;
            size--;
            modCount++;
            return;
        }

        tail = tail.prev;
        tail.next = null;

        modCount++;
        size--;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        if (index == 0) {
            removeFirst();
            return;
        }

        if (index == size - 1) {
            removeLast();
            return;
        }

        Node<T> current = getNode(index);

        current.prev.next = current.next;
        current.next.prev = current.prev;
        current.next = current.prev = null;

        modCount++;
        size--;
    }

    public boolean remove(T value) {
        Node<T> current = head;

        while (current != null) {
            if (Objects.equals(value, current.data)) {

                if (current == head) {
                    removeFirst();
                    return true;
                }

                if (current == tail) {
                    removeLast();
                    return true;
                }

                current.prev.next = current.next;
                current.next.prev = current.prev;

                size--;
                modCount++;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public T get(int index) {
        return getNode(index).data;
    }

    public void set(int index, T data) {
        getNode(index).data = data;
    }

    public T peekFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.data;
    }

    public T peekLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.data;
    }

    public boolean contains(T data) {
        Node<T> current = head;

        while (current != null) {
            if (Objects.equals(data, current.data)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public int indexOf(T data) {
        Node<T> current = head;

        for (int i = 0; i < size; i++) {
            if (Objects.equals(data, current.data)) {
                return i;
            }
            current = current.next;
        }

        return -1;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
        modCount++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void reverse() {
        if (isEmpty() || head == tail) return;

        Node<T> current = head;

        while (current != null) {
            Node<T> next = current.next;
            current.next = current.prev;
            current.prev = next;
            current = next;
        }

        Node<T> node = head;
        head = tail;
        tail = node;

        modCount++;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] array = (T[]) new Object[size];
        Node<T> current = head;

        for (int i = 0; i < size; i++) {
            array[i] = current.data;
            current = current.next;
        }

        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;

        while (current != null) {
            sb.append(current.data);
            if (current.next != null)
                sb.append(", ");
            current = current.next;
        }

        sb.append("]");
        return sb.toString();
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        Node<T> current;

        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }

        return current;
    }

    @Override
    public Iterator<T> iterator() {
        return new DoublyLinkedListIterator();
    }

    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private class DoublyLinkedListIterator implements Iterator<T> {
        Node<T> current = head;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> temp = current;
            current = current.next;
            return temp.data;
        }

        private void checkForComodification() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}

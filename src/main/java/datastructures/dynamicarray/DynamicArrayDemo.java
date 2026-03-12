package datastructures.dynamicarray;

import java.util.ConcurrentModificationException;

/**
 * Demonstrates the usage of the DynamicArray class.
 */
public class DynamicArrayDemo {

    public static void main(String[] args) {
        basicOperationsDemo();
        insertAtIndexDemo();
        resizingDemo();
        searchDemo();
        iteratorDemo();
        failFastIteratorDemo();
        nullHandlingDemo();
    }

    /**
     * Demonstrates add, get, set, remove, size, isEmpty, clear.
     */
    private static void basicOperationsDemo() {
        System.out.println("=== Basic Operations ===");

        DynamicArray<String> arr = new DynamicArray<>();
        System.out.println("Empty: " + arr.isEmpty());           // true

        arr.add("Alice");
        arr.add("Bob");
        arr.add("Charlie");
        System.out.println("Size after 3 adds: " + arr.size()); // 3
        System.out.println("Get index 1: " + arr.get(1));       // Bob

        arr.set(1, "Barbara");
        System.out.println("After set(1, Barbara): " + arr.get(1)); // Barbara

        arr.remove(0);
        System.out.println("After remove(0), index 0: " + arr.get(0)); // Barbara
        System.out.println("Size: " + arr.size());              // 2

        arr.clear();
        System.out.println("After clear, isEmpty: " + arr.isEmpty()); // true
        System.out.println();
    }

    /**
     * Demonstrates insert at a specific index.
     */
    private static void insertAtIndexDemo() {
        System.out.println("=== Insert at Index ===");

        DynamicArray<Integer> arr = new DynamicArray<>();
        arr.add(10);
        arr.add(20);
        arr.add(40);
        arr.add(50);
        printArray("Before insert:", arr); // [10, 20, 40, 50]

        arr.add(2, 30); // insert 30 at index 2
        printArray("After add(2, 30):", arr); // [10, 20, 30, 40, 50]

        arr.add(0, 5); // insert at beginning
        printArray("After add(0, 5):", arr); // [5, 10, 20, 30, 40, 50]

        arr.add(arr.size(), 60); // insert at end
        printArray("After add at end:", arr); // [5, 10, 20, 30, 40, 50, 60]
        System.out.println();
    }

    /**
     * Demonstrates automatic resizing behavior.
     */
    private static void resizingDemo() {
        System.out.println("=== Resizing ===");

        DynamicArray<Integer> arr = new DynamicArray<>(2);
        System.out.println("Initial capacity: " + arr.capacity()); // 2

        arr.add(1);
        arr.add(2);
        System.out.println("Capacity after filling (size=2): " + arr.capacity()); // 2

        arr.add(3); // triggers resize → capacity becomes 4
        System.out.println("Capacity after exceeding (size=3): " + arr.capacity()); // 4

        arr.add(4);
        arr.add(5); // triggers resize → capacity becomes 8
        System.out.println("Capacity after exceeding again (size=5): " + arr.capacity()); // 8

        // Shrinking: remove until size <= capacity/4
        arr.remove(0);
        arr.remove(0); // size=3, capacity=8 → no shrink yet
        System.out.println("Capacity after 2 removes (size=3): " + arr.capacity()); // 8

        arr.remove(0); // size=2, capacity=8 → 2 <= 8/4=2 → shrinks to 4
        System.out.println("Capacity after shrink triggered (size=2): " + arr.capacity()); // 4
        System.out.println();
    }

    /**
     * Demonstrates contains and indexOf.
     */
    private static void searchDemo() {
        System.out.println("=== Search ===");

        DynamicArray<String> arr = new DynamicArray<>();
        arr.add("Dog");
        arr.add("Cat");
        arr.add("Bird");

        System.out.println("Contains 'Cat': " + arr.contains("Cat"));      // true
        System.out.println("Contains 'Fish': " + arr.contains("Fish"));    // false
        System.out.println("indexOf 'Bird': " + arr.indexOf("Bird"));      // 2
        System.out.println("indexOf 'Fish': " + arr.indexOf("Fish"));      // -1
        System.out.println();
    }

    /**
     * Demonstrates for-each iteration.
     */
    private static void iteratorDemo() {
        System.out.println("=== Iterator (for-each) ===");

        DynamicArray<Integer> arr = new DynamicArray<>();
        for (int i = 1; i <= 5; i++) arr.add(i * 10);

        System.out.print("Elements: ");
        for (int val : arr) {
            System.out.print(val + " "); // 10 20 30 40 50
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Demonstrates that the iterator throws ConcurrentModificationException
     * when the array is modified during iteration.
     */
    private static void failFastIteratorDemo() {
        System.out.println("=== Fail-Fast Iterator ===");

        DynamicArray<Integer> arr = new DynamicArray<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);

        try {
            for (int val : arr) {
                System.out.println("Visiting: " + val);
                arr.add(99); // modifies array mid-iteration
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException caught as expected!");
        }
        System.out.println();
    }

    /**
     * Demonstrates null element handling.
     */
    private static void nullHandlingDemo() {
        System.out.println("=== Null Handling ===");

        DynamicArray<String> arr = new DynamicArray<>();
        arr.add("Hello");
        arr.add(null);
        arr.add("World");

        System.out.println("Contains null: " + arr.contains(null));   // true
        System.out.println("indexOf null: " + arr.indexOf(null));     // 1
        System.out.println("get(1): " + arr.get(1));                  // null

        arr.remove(1);
        System.out.println("After removing null, contains null: " + arr.contains(null)); // false
        System.out.println();
    }

    /**
     * Helper to print all elements of a DynamicArray.
     */
    private static <T> void printArray(String label, DynamicArray<T> arr) {
        System.out.print(label + " [");
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i));
            if (i < arr.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
